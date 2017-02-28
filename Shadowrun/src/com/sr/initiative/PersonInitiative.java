package com.sr.initiative;

import com.sr.utils.Dice;
import com.sr.combatant.Combatant;
import com.sr.person.Person;

public class PersonInitiative extends Initiative {
	private Person pers;
	
	public PersonInitiative(Person pers)	{
		super((Combatant) pers);
		this.pers = pers;
	}
	
	@Override
	public int getIni()	{
		return ini + cmbtnt.getStat().getWoundMod();
	}
	
	@Override
	public int rollIni()	{
		if (cmbtnt.getStat().isTargetable())	{
			int dice = getIniDice();
			this.ini = pers.getAttr().getRea() + Dice.rollD6(1 + dice, cmbtnt);
			if(dice > 0 || pers.getAttr().getRea() > pers.getAttr().getReaBase())	{
				iniDesc = pers.getCyberware().getIniDesc();
			}
		} else	{
			this.ini = 0;
		}
		return ini;
	}
	
	private int getIniDice()	{
		return pers.getCyberware().getIniDiceMod();
	}
}
