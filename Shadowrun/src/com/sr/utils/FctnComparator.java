package com.sr.utils;

import java.util.Comparator;

import com.sr.person.Faction;

public class FctnComparator implements Comparator<Faction>	{
	
	@Override
	public int compare(Faction f1, Faction f2)	{			
		
		if (f1.getIndex() != f2.getIndex())	{
			return f1.getIndex() - f2.getIndex();
		} else	{
			return 0;
		}
	}
}