package com.sr.combat;

import java.util.ArrayList;

import com.sr.combatant.StatusEffects;
import com.sr.combatant.DthStat;
import com.sr.gear.Weapon;
import com.sr.gear.Armor;
import com.sr.utils.Test;
import com.sr.utils.Message;
import com.sr.combatant.Combatant;
import com.sr.combat.DmgType;

public class Attack {
	private DmgType dmgType;
	private Combatant atckr, opnt;
	private StatusEffects atckrStat, opntStat;
	private Weapon wpn;
	private Armor arm;
	private int netHits;
	private boolean isImmune, atckrIsActive, opntIsTargetable, isLimited;
	private ArrayList<Integer> rolls;

	/**
	 * Attack constructor for one attacker and one opponent.
	 * @throws IllegalArgumentException if the attacker is unable
	 * to perform attack or if the opponent is no longer a valid 
	 * target due to status effects.
	 */
	public Attack(Combatant atckr, Combatant opnt)	{
		this.atckr = atckr;
		this.atckrStat = atckr.getStat();
		this.atckrIsActive = atckrStat.isTargetable();
		if (this.atckrIsActive != true)	{
			throw new IllegalArgumentException("Atckr unable to attack due to being unconscious, knocked-out, dying, or dead.");
		}
		this.opnt = opnt;
		this.opntStat = opnt.getStat();
		this.opntIsTargetable = opntStat.isTargetable();
		if (this.opntIsTargetable != true)	{
			throw new IllegalArgumentException("Opnt not targetable due to being unconscious, knocked-out, dying, or dead.");
		}
		this.wpn = atckr.getWpn();
		this.arm = opnt.getArmor();
		atk();
	}
	
	private void atk()	{
		this.netHits = hitAtk();
		if (netHits < 0)	{
			Message.atkMiss(this);
		} else if (netHits == 0)	{
			Message.atkGraze(this);
		} else if (netHits > 0)	{
			Message.atkHit(this);
			int netDmg = calcDmg();
			rslveDmg(netDmg);
		}
	}
	
	private int hitAtk()	{	
		int hitsAtckr = getHitsAtckr();
		int hitsOpnt = getHitsOpnt();
		Message.opposed(atckr, opnt, hitsAtckr, hitsOpnt);
		return hitsAtckr - hitsOpnt;
	}
	
	private int getHitsAtckr()	{
		int atckrDicePool = atckr.getAtckDicePool();
		if (testAutoFail(atckr, atckrDicePool, "ATTACK"))	{
			return 0;
		}
		int limit = atckr.getAtckLimit();
		int hitsAtckr = getHits(atckrDicePool, limit);
		Message.newAtk(this, hitsAtckr, atckrDicePool, atckr.getAtckDicePoolString(), rolls.toString());
		if (isLimited)	{
			return limit;
		}
		return hitsAtckr;
	}
	
	private int getHitsOpnt()	{
		int opntMod = (atckrStat.hasRanged() == true) ? opntStat.getWoundMod() : melAtkOpntMod();
		int opntDicePool = opnt.getDefDicePool() + opntMod;
		if (testAutoFail(opnt, opntDicePool, "DEFENSE"))	{
			return 0;
		}
		int limit = opnt.getDefLimit();
		int opntHits = getHits(opntDicePool, limit);
		Message.newDef(this, opntHits, opntDicePool, opnt.getDefDicePoolString(), rolls.toString());
		return opntHits;
	}
	
	private int getHits(int dice, int limit)	{
		rolls = Test.success(dice);
		int hits = rolls.get(rolls.size() - 1);
		rolls.remove(rolls.size() - 1);
		checkLimit(hits, limit);
		return hits;
	}
	
	private void checkLimit(int hits, int limit)	{
		isLimited = false;
		if(limit != 0)	{
			isLimited = (hits > limit) ? true : false;
		}
	}
	
	private int melAtkOpntMod()	{
		return (getNetReach() + opntStat.getWoundMod());
	}
	
	public int getNetReach()	{
		return (opntStat.getReach() - atckrStat.getReach());
	}
	
	private int calcDmg()
	{
		rslveDmgType();
		if (opnt.isDmgImmune(dmgType))	{
			this.isImmune = true;
			return 0;
		} else	{
			int rawDmg = wpn.getDmg() + netHits;
			int dmgRes = opnt.getBod() + effArm() + opntStat.getWoundMod();
			int dmgResTst = getHits(dmgRes, 0);
			int netDmg = (rawDmg - dmgResTst > 0) ? (rawDmg - dmgResTst) : 0;
			Message.calcDmg(this, dmgRes, rawDmg, dmgResTst, netDmg);
			return netDmg;
		}
	}
	
	private void rslveDmgType()	{		
		if (wpn.isStun() || (wpn.getDmg() + netHits) < getEffArm())	{
			this.dmgType = DmgType.STUN;
		} else	{
			this.dmgType = DmgType.PHYSICAL;
		}
		if ((wpn.getDmg() + netHits) < getEffArm() && ! opnt.isDmgImmune(dmgType))	{
			Message.rslveDmgStun();
		}
	}
	
	private int effArm()	{
		return getEffArm();
	}
	
	public int getEffArm()	{
		return (arm.getArmRating() + wpn.getAP());
	}
	
	private void rslveDmg(int netDmg)	{		
		if(isImmune)	{
			Message.isDmgImmune(opnt, dmgType);
		} else if(dmgType == DmgType.STUN)	{
			rslveStunDmg(netDmg);
		} else	if(dmgType == DmgType.PHYSICAL)	{
			rslvePhysDmg(netDmg);
		}
	}
	
	private void rslveStunDmg(int netDmg)	{
		opntStat.addStunDmg(netDmg);
		if(opntStat.getDthStat() == DthStat.KNOCKED_OUT)	{
			Message.atkUnconscious(opnt);
		} else if (netDmg == 0)	{
			Message.atkScratch(opnt);
		} else	{
			Message.atkStunDmg(opnt);
		}
	}
	
	private void rslvePhysDmg(int netDmg)	{	
		opntStat.addPhyDmg(netDmg);
		DthStat dthStat = opntStat.getDthStat();
		switch(dthStat)	{
			case DESTROYED:
				Message.atkDestroyed(opnt);
				break;
			case DEAD:
				Message.atkDead(opnt);
				break;
			case DYING:
				Message.atkDying(opnt);
				break;
			case KNOCKED_OUT:
				Message.atkKnockOut(opnt);
				break;
			case DAMAGED:
			case WOUNDED:
				Message.atkDamaged(opnt);
				break;
			case ALIVE:
				Message.atkScratch(opnt);
				break;				
		}
	}
	
	public Combatant getAtckr() {
		return atckr;
	}

	public Combatant getOpnt() {
		return opnt;
	}

	public ArrayList<Integer> getRolls()	{
		return rolls;
	}
	
	public DmgType dmgType()	{
		return dmgType;
	}
	
	public int getNetHits()	{
		return netHits;
	}
	
	public DmgType getDmgType()	{
		return dmgType;
	}
	
	private boolean testAutoFail(Combatant cmbtnt, int dicePool, String test)	{
		if(dicePool < 0)	{
			Message.autoFail(cmbtnt, test);
			return true;
		}
		return false;
	}
}