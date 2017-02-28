package com.sr.utils;

import java.util.Comparator;
import com.sr.combatant.Combatant;
import com.sr.combatant.DthStat;
import com.sr.person.Faction;

public class FctnDthStatNameComparator implements Comparator<Combatant>	{
	
	@Override
	public int compare(Combatant p1, Combatant p2)	{
		Faction p1fctn = p1.getFctn();
		Faction p2fctn = p2.getFctn();
		DthStat p1dthStat = p1.getStat().getDthStat();
		DthStat p2dthStat = p2.getStat().getDthStat();
		String p1name = p1.getName();
		String p2name = p2.getName();
				
		if (p2fctn.getIndex() != p1fctn.getIndex())	{
			return -(p2fctn.getIndex() - p1fctn.getIndex());
		} else if (p2dthStat.getIndex() != p1dthStat.getIndex())	{
			return -(p2dthStat.getIndex() - p1dthStat.getIndex());
		} else if (p2dthStat == DthStat.DYING && p1dthStat == DthStat.DYING)	{
			return -(p2.getStat().getDthTmr() - p1.getStat().getDthTmr());
		} else if (p2dthStat == p1dthStat)	{
			return p1name.compareTo(p2name);
		}
		return 0;
	}
}