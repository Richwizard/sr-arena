package com.sr.gear;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.Collections;
import java.util.Iterator;

import com.sr.person.Runner;
import com.sr.utils.RandomInt;
import com.sr.attribute.Attributes;
import com.sr.gear.CyberwareTypes;

public class Cyberware {
	private Runner runner;
	private ArrayList<CyberwareGear> cyberware;
	private int iniDiceMod, varDmgMod;
	private String iniDesc;
	
	{
		cyberware = new ArrayList<>();
		iniDiceMod = 0;
		varDmgMod = 0;
	}
	
	public ArrayList<CyberwareGear> getCyberware()	{
		return cyberware;
	}
	
	public void addCyberware(Runner runner, CyberwareGear... cyberwareGear)	{
		this.runner = runner;
		for (CyberwareGear cyber : cyberwareGear)	{
			if(cyberware.contains(cyber))	{
				throw new IllegalArgumentException(cyber + " already installed. Cannot install twice.");
			}
			cyberware.add(cyber);
			setEffects(cyber.getCyberwareType(), cyber.getRating());
		}
	}
	
	private void setEffects(CyberwareTypes cyberwareType, int rating)	{
		runner.getAttr().changeEss(cyberwareType.getEssLoss(rating));
		switch (cyberwareType)	{
			case NONE:
			default:
				break;
			case BONELACING:
				setBoneLacing(rating);
				break;
			case CONTROL_RIG:
				setControlRig(rating);
				break;
			case DERMAL_PLATING:
				setDermalPlating(rating);
				break;
			case MUSCLE_REPLACEMENT:
				setMuscleReplacement(rating);
				break;
			case REACTION_ENHANCERS:
				setReactionEnhancers(rating);
				break;
			case WIRED_REFLEXES:
				setWiredReflexes(rating);
				break;
		}
	}
	
	private void setBoneLacing(int i)	{
		runner.getAttr().setAttrModByIndex(Attributes.BODY.getIndex(), i);
		this.varDmgMod = i;
		String name = "";
		if(i == 1)
			name = "Plastic Bone Lacing";
		if(i == 2)
			name = "Aluminium Bone Lacing";
		if (i == 3)
			name = "Titanium Bone Lacing";
		runner.getArmor().addArmor(runner, new Armor(ArmorType.CYBERWARE, name, i, true));
	}
	
	private void setControlRig(int i)	{
	}
	
	private void setDermalPlating(int i)	{
		runner.getArmor().addArmor(runner, new Armor(ArmorType.CYBERWARE, "Dermal Plating", i, true));
	}
	
	private void setMuscleReplacement(int i)	{
		runner.getAttr().setAttrModByIndex(Attributes.AGILITY.getIndex(), i * 1);
		runner.getAttr().setAttrModByIndex(Attributes.STRENGTH.getIndex(), i * 1);
	}
	
	private void setReactionEnhancers(int i)	{
		runner.getAttr().setAttrModByIndex(Attributes.REACTION.getIndex(), i);
		StringJoiner sj = new StringJoiner(", ");
		if (iniDesc != null)	{
			sj.add(iniDesc);
		}
		sj.add("Reaction Enhancers " + i);
		this.iniDesc = sj.toString();
	}
	
	private void setWiredReflexes(int i)	{
		runner.getAttr().setAttrModByIndex(Attributes.REACTION.getIndex(), i);
		this.iniDiceMod = i;
		StringJoiner sj = new StringJoiner(", ");
		if (iniDesc != null)	{
			sj.add(iniDesc);
		}
		sj.add("Wired Reflexes " + i);
		this.iniDesc = sj.toString();
	}
	
	public int getIniDiceMod()	{
		return iniDiceMod;
	}
	
	public int getVarDmgMod()	{
		return varDmgMod;
	}
	
	public String getIniDesc()	{
		if (iniDesc != null)	{
			return iniDesc;
		}
		return "";
	}
	
	@Override
	public String toString()	{
		StringJoiner sj = new StringJoiner(", ");
		for (CyberwareGear cyber : cyberware)	{
			sj.add(cyber.toString());
		}
		return sj.toString();
	}
	
	/**
	 * Fancy code magic based on 
	 * http://stackoverflow.com/questions/223918/iterating-through-a-collection-avoiding-concurrentmodificationexception-when-re
	 */
	public static ArrayList<CyberwareGear> getRndmCyberware(double minEss, CyberwareTypes... cyberwareSelection)	{
		ArrayList<CyberwareTypes> cyberwareTypes = new ArrayList<CyberwareTypes>();
		ArrayList<CyberwareGear> cyberware = new ArrayList<CyberwareGear>();
		double essLoss = 0;
		for(CyberwareTypes cyberwareType : cyberwareSelection)	{
			cyberwareTypes.add(cyberwareType);
		}
		Collections.shuffle(cyberwareTypes);
		for (Iterator<CyberwareTypes> iterator = cyberwareTypes.iterator(); iterator.hasNext();)	{
			CyberwareTypes cyberwareType = iterator.next();
			int rating = 0;
			int capacity = 0;
			if (cyberwareType.getMaxRating() > 0)	{
				rating = RandomInt.randInt(1, cyberwareType.getMaxRating());
			}
			essLoss += cyberwareType.getEssLoss(rating);
			if (- (essLoss) < (6 - minEss))	{
				cyberware.add(new CyberwareGear(cyberwareType, rating, capacity));
			} else {
				essLoss -= cyberwareType.getEssLoss(rating);
			}
			iterator.remove();
		}
		return cyberware;
	}
}
