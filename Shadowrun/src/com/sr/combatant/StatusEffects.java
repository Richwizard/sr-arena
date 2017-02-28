package com.sr.combatant;

import com.sr.utils.Message;

import java.util.Arrays;

import com.sr.gear.MeleeWeapon;
import com.sr.person.MetaType;
import com.sr.person.Person;
import com.sr.person.Runner;
import com.sr.attribute.Attribute;
import com.sr.attribute.Attributes;
import com.sr.combatant.Combatant;

public class StatusEffects {
	private CombatantType cmbtntType;
	private Combatant cmbtnt;
	private DthStat dthStat;
	private int phyDmg, stunDmg, dthTmr, phyDmgTrk, stunDmgTrk, bod;
	private boolean isExhstd;
	private boolean hasRanged, hasMelee, hasArmorMix;
	private int [] encmbrnceMod = new int[Attributes.values().length];
	
	{
		dthStat = DthStat.ALIVE;
		dthTmr = 0;
	}
	
	public StatusEffects(Combatant cmbtnt)	{
		this.cmbtnt = cmbtnt;
		this.cmbtntType = cmbtnt.getCmbtntType();
		this.phyDmgTrk = cmbtnt.getDmgTrks()[0];
		this.stunDmgTrk = cmbtnt.getDmgTrks()[1];
		this.bod = cmbtnt.getBod();
	}
	
	public void setHasRanged(boolean hasRanged)	{
		this.hasRanged = hasRanged;
	}
	
	public void setHasMelee(boolean hasMelee)	{
		this.hasMelee = hasMelee;
	}
	
	public void resetHasWpn()	{
		hasRanged = false;
		hasMelee = false;
	}
	
	private int woundMod()	{
		switch(cmbtntType)	{
		case PERSON:
			return (- phyDmg / 3 - stunDmg / 3);
		case DRONE:
		default:
			return 0;
		}
	}
	
	public DthStat dthStat()	{
		switch(cmbtntType)	{
		case PERSON:
			return getDthStatPers();
		case DRONE:
			return getDthStatDrone();
		default:
			return getDthStat();
		}
	}
	
	private DthStat getDthStatPers()	{
		int dthThrshld = bod + phyDmgTrk;
		if (dthThrshld < phyDmg)	{
			dthStat = DthStat.DEAD;
			Message.dead(cdmToString());
		} else if (phyDmgTrk < phyDmg)	{
			dthStat = DthStat.DYING;
			Message.dying(cdmToString());
		} else	if (phyDmgTrk == phyDmg)	{
			dthStat = DthStat.KNOCKED_OUT;
			Message.unconsciousPhysical(cdmToString());
		} else if (phyDmg > 0)	{
			dthStat = DthStat.WOUNDED;
		} else if (phyDmg == 0)	{
			dthStat = DthStat.ALIVE;
		}
		return dthStat;
	}
		
	private DthStat getDthStatDrone()	{		
		if (phyDmgTrk <= phyDmg)	{
			dthStat = DthStat.DESTROYED;
		} else if (phyDmg > 0)	{
			dthStat = DthStat.DAMAGED;
		}
		return dthStat;
	}
	
	public void setDthStat(DthStat dthStat)	{
		this.dthStat = dthStat;
	}
	
	public DthStat getDthStat()	{
		return dthStat;
	}
	
	public int getPhyDmg() {
		return phyDmg;
	}
	
	private void setPhyDmg(int addPhyDmg) {
		phyDmg += addPhyDmg;
		switch (cmbtntType)	{
		case PERSON:
			getDthStatPers();
			break;
		case DRONE:
			getDthStatDrone();
			break;
		default:
			break;
		}
	}
	
	public void addPhyDmg(int addPhyDmg)	{
		setPhyDmg(addPhyDmg);	
	}
	
	public int getStunDmg() {
		return stunDmg;
	}
	
	public void addStunDmg(int addStunDmg)	{
		setStunDmg(addStunDmg);
	}
	
	private void setStunDmg(int addStunDmg)	{
		int excessDmg = (stunDmg + addStunDmg - stunDmgTrk) / 2;
		if (excessDmg > 0)	{
			stunDmg = stunDmgTrk;
			addPhyDmg(excessDmg);
			dthStat = DthStat.KNOCKED_OUT;
			Message.unconsciousStunExcess(cdmToString());
		} else if (stunDmg + addStunDmg == stunDmgTrk)	{
			stunDmg = stunDmgTrk;
			dthStat = DthStat.KNOCKED_OUT;
			Message.unconsciousStun(cdmToString());
		} else	{
			stunDmg = stunDmg + addStunDmg;
		}
	}
	
	// Applicable to Persons
	public String cdmToString()	{
		int[] cdm = { stunDmg, stunDmgTrk, phyDmg, phyDmgTrk, (phyDmgTrk + bod), dthTmr };
		String stunMonitor = String.format("STU (%02d/%02d)", cdm[0], cdm[1]);
		String phyMonitor = String.format("PHY(%02d/%02d)", cdm[2], cdm[3]);
		String dthMonitor = String.format("DTH: %02d %s", cdm[4], dthStat);
		String sCdm = String.format("CDM %s %s %s", stunMonitor, phyMonitor, dthMonitor);
		if (dthStat == DthStat.DYING)	{
			sCdm = String.format("%s\t(Bleed Timer: (%d/20))", sCdm, cdm[5]);
		}
		return sCdm;
	}
	
	// Applicable to Drones
	public String cdmToString(int phyDmgTrk)	{
		String stunMonitor = String.format("STU (%02d/%02d)", 0, 0);
		String phyMonitor = String.format("PHY(%02d/%02d)", phyDmg, phyDmgTrk);
		String dthMonitor = String.format("DTH: %02d %s", phyDmgTrk, dthStat);
		String sCdm = String.format("CDM %s %s %s", stunMonitor, phyMonitor, dthMonitor);
		return sCdm;
	}
	
	public int getWoundMod()	{
		return woundMod();
	}
	
	public boolean hasRanged()	{
		return hasRanged;
	}
	
	public boolean hasMelee()	{
		return hasMelee;
	}
	
	public void tickBleedTmr()	{
		if(cmbtntType == CombatantType.PERSON)	{
			Person pers = (Person) cmbtnt;
			this.dthTmr++;
			if (dthTmr >= 20) {
				Message.dthTmr(pers);
				addPhyDmg(1);
				dthTmr -= 20;
			}
		}
	}
	
	public boolean isTargetable()	{
		if (dthStat == DthStat.DEAD || dthStat == DthStat.DYING || 
			dthStat == DthStat.KNOCKED_OUT || dthStat == DthStat.DESTROYED)	{
			return false;
		} else	{
			return true;
		}
	}
	
	public boolean isExhstd() {
		return isExhstd;
	}

	public void setExhstd(boolean isExhstd) {
		this.isExhstd = isExhstd;
	}
	
	public void setHasArmorMix(Runner runner, boolean hasArmorMix)	{
		this.hasArmorMix = hasArmorMix;
		if (hasArmorMix)	{
			setEncmbrnce(runner);
		}
	}
	
	/**
	 *	For every 2 full points by which the bonus
	 *	exceeds the character’s Strength, the character
	 *	suffers a –1 penalty to Agility and Reaction.
	 */
	public void setEncmbrnce(Runner runner)	{
			Attribute attr = runner.getAttr();
			int [] encmbrnceMod = new int[Attributes.values().length];
			int ar = runner.getArmor().getCmltveArmRating();
			int str = attr.getStr();	
			if ((str + 2) <= ar)	{
				int mod = - ((ar - str) / 2);
				encmbrnceMod[Attributes.AGILITY.getIndex()] = mod;
				encmbrnceMod[Attributes.REACTION.getIndex()] = mod;
				if (Arrays.equals(encmbrnceMod, this.encmbrnceMod) == false)	{
					resetEncmbrnce(attr);
					this.encmbrnceMod = encmbrnceMod;
					attr.setAttrMod(encmbrnceMod);
					Message.encmbrnce(runner, mod);
				}
			}
	}
	
	public int[] getEncmbrnceMod()	{
		return encmbrnceMod;
	}
	
	private void resetEncmbrnce(Attribute attr)	{
		this.encmbrnceMod[Attributes.AGILITY.getIndex()] *= -1;
		this.encmbrnceMod[Attributes.REACTION.getIndex()] *= -1;
		attr.setAttrMod(this.encmbrnceMod);
	}
	
	public int getReach()	{
		this.cmbtntType = cmbtnt.getCmbtntType();
		switch (cmbtntType)	{
		case PERSON:
			return getReachPers((Person) cmbtnt);
		case DRONE:
			int reach = 0;
			reach = (hasMelee) ? ((MeleeWeapon) cmbtnt.getWpn()).getReach() : 0;
			return reach;
		default:
			return 0;
		}
	}
		
	private int getReachPers(Person pers)	{
		int reach = (pers.getMetatype() == MetaType.TROLL) ? 1 : 0;
		if (hasMelee)	{
			reach += ((MeleeWeapon) ((Combatant)pers).getWpn()).getReach();
		}
		return reach;
	}
	
	public int getDthTmr()	{
		return dthTmr;
	}
	
	@Override
	public String toString()	{
		return String.format("Exhausted: %b, HasRanged: %b, HasMelee: %b, HasArmorMix: %b%nEncumbrance: %s", isExhstd, hasRanged, hasMelee, hasArmorMix, Arrays.toString(encmbrnceMod));
	}
}