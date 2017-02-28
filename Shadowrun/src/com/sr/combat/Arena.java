package com.sr.combat;

import java.util.ArrayList;
import java.util.Collections;

import com.sr.combatant.Combatant;
import com.sr.utils.CmbtntExhstdIniComparator;
import com.sr.utils.RandomInt;
import com.sr.utils.FctnDthStatNameComparator;
import com.sr.utils.Message;
import com.sr.combatant.DthStat;
import com.sr.person.Faction;
import com.sr.summons.Summon;

public class Arena {
	ArrayList<Combatant> combtnts;
	ArrayList<Summon> summons = new ArrayList<Summon>();
	private int cmbttrn = 1;
	private int actvAgnt;
	private int iniPass;
	private boolean cmbtWon = false;
	
	public Arena(ArrayList<Combatant> combtnts)	{
		this.combtnts = combtnts;
		if (combtnts.size() < 2)	{
			throw new IllegalArgumentException("Arena must be initialized with at least two combatants.");
		}	
		startCmbt();
	}
	
	private void startCmbt()	{
		setFctn();
		Message.arenaWlcm(combtnts);
		while (cmbtWon == false  && cmbttrn < 250)	{
			takeTrn();
			if (cmbttrn == 250)	{
				Message.staleMate(cmbttrn);
			}
		};
	}
	
	private void setFctn()	{
		int combtntsSize = combtnts.size();
		ArrayList<Integer> indices = Faction.getIndices();
		indices.remove(0);
		Collections.shuffle(indices);
		int fctnAmount = getFctnAmount();
		int fctnSize = combtntsSize / fctnAmount;
		int modulo = combtntsSize % fctnAmount;
		
		int c = 0;
		for(int i = 0; i < fctnSize; i++)	{
			for(int f = 0; f < fctnAmount; f++)	{
				combtnts.get(c).setFctnByIndex(indices.get(f));
				c++;
			}
		}
		for(int i = 0; i < modulo; i++)	{
			combtnts.get(combtnts.size() - (i+1)).setFctnByIndex(indices.get(i));
		}
	}
	
	private int getFctnAmount()	{
		int size = combtnts.size();
		if (size == 2 || size == 3)	{
			return 2;
		} else	{
			if (size/2 < (Faction.values().length - 1))	{
				return RandomInt.randInt(2, (size/2));
			} else	{
				return RandomInt.randInt(2, (Faction.values().length - 1));
			}
		}
	}
	
	private void ini()	{
		for (Combatant cmbtnt : combtnts)	{
			cmbtnt.getInitiative().rollIni();
		}
		Collections.sort(combtnts, new CmbtntExhstdIniComparator());
	}
	
	private void takeTrn()	{
		Message.turnAnnouncer(cmbttrn);
		rslvSummons();
		ini();
		Message.ini(combtnts);
		this.iniPass = 1;
		while (combtnts.get(0).getInitiative().getIni() > 0 && cmbtWon != true)	{
			actvAgnt = 0;
			resetExhstd();
			takeActn();
			iniPass++;
		};
		Collections.sort(combtnts, new FctnDthStatNameComparator());
		tickBleedTmrs();
		Message.cdmArena(combtnts, cmbttrn);
		cmbttrn++;
	}
	
	private void resetExhstd()	{
		for (int i = 0; i < combtnts.size(); i++)	{
			Combatant cmbtnt = combtnts.get(i);
			cmbtnt.getStat().setExhstd(false);
		}
	}
	
	private void tickBleedTmrs()	{
		for (int i = 0; i < combtnts.size(); i++)	{
			Combatant cmbtnt = combtnts.get(i);
			if (cmbtnt.getStat().getDthStat() == DthStat.DYING)	{
				cmbtnt.getStat().tickDthTmr();
			}
		}
	}
	
	private void takeActn()	{
		for (int i = 0; i < combtnts.size(); i++)	{
			if(cmbtWon != true)	{
				Combatant agnt = combtnts.get(actvAgnt);
				agnt.getStat().setExhstd(true);
				if(agnt.getStat().isTargetable() == true && agnt.getInitiative().getIni() > 0)	{				
					Message.iniPass(agnt, iniPass);
					try	{
						new Action(this, agnt, getTrgtByFctn(agnt.getFctn()));
					} catch (NoViableTargetsException e)	{
						cmbtWon = true;
						Message.cmbtWon(agnt.getFctn(), cmbttrn);
					}
				}
				agnt.getInitiative().reduceIni();
				Collections.sort(combtnts, new CmbtntExhstdIniComparator());
				actvAgnt++;
			}
		}
	}
	
	private Combatant getTrgtByFctn(Faction agntFctn)	throws NoViableTargetsException {
		ArrayList<Combatant> trgts = new ArrayList<>();
		for(Combatant trgt : combtnts)
		{
			if(agntFctn != trgt.getFctn() && trgt.getStat().isTargetable() == true)
				trgts.add(trgt);				
		}
		if(trgts.isEmpty())	{
			throw new NoViableTargetsException(agntFctn);
		}
		Collections.shuffle(trgts);
		return trgts.get(0);
	}
	
	public void addSummon(Summon summon)	{
		summons.add(summon);
	}
	
	private void rslvSummons()	{
		if(summons.size() > 0)	{
			for(Summon spawn : summons)	{
				combtnts.add(spawn);
				Message.spawnAnnouncer(spawn);
			}
			resetExhstd();
		}
		summons.removeAll(summons);	
	}
}