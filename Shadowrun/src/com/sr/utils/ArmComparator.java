package com.sr.utils;

import java.util.Comparator;

import com.sr.gear.Armor;
import com.sr.gear.ArmorType;

public class ArmComparator implements Comparator<Armor>	{
	
	@Override
	public int compare(Armor a1, Armor a2)	{			
		
		if(a1.getType() == ArmorType.CYBERWARE && a2.getType() != ArmorType.CYBERWARE)	{
			return 1;
		} else if (a1.getType() != ArmorType.CYBERWARE && a2.getType() == ArmorType.CYBERWARE)	{
			return -1;
		} else if(a1.isCumulative() == true && a2.isCumulative() == false)	{
			return 1;
		} else if(a1.isCumulative() == false && a2.isCumulative() == true)	{
			return -1;
		}
	return 0;
	}
}