package com.sr.utils;

import java.util.Comparator;

import com.sr.person.Faction;
import com.sr.combatant.Combatant;

public class CmbtntFctnComparator implements Comparator<Combatant>	{
	
	@Override
	public int compare(Combatant p1, Combatant p2)	{			
		Faction f1 = p1.getFctn();
		Faction f2 = p2.getFctn();
		
		if (f1.getIndex() != f2.getIndex())	{
			return f1.getIndex() - f2.getIndex();
		} else	{
			return 0;
		}
	}
}