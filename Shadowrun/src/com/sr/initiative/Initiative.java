package com.sr.initiative;

import com.sr.combatant.Combatant;
import com.sr.utils.Dice;

public abstract class Initiative implements HasInitiative {
	
	protected int ini;
	protected String iniDesc = new String();
	protected Combatant cmbtnt;

	public Initiative(Combatant cmbtnt)	{
		this.cmbtnt = cmbtnt;
	}
	
	// Returns initiative value rolled last.
	public int getIni()	{
		return ini;
	}
	
	// Rolls and returns new initiative value.
	public int rollIni()	{
		ini = Dice.rollD6(1, cmbtnt);
		return ini;
	}
	
	public void reduceIni()	{
		this.ini -= 10;
	}
	
	public String getIniDesc()	{
		return iniDesc;
	}
}
