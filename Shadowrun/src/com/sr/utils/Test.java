package com.sr.utils;

import java.util.ArrayList;

public class Test {
/*	
	public static int getRatio(int hits, int dice)	{
		String ratio;
		if (dice > 0)	{
			int rat = (int) (hits * 100.0) / dice;
			ratio = new String (rat + "%");
		} else	{
			ratio = new String ("");
		}
		return ratio;	
	}*/
	
	public static ArrayList<Integer> success(int dice)	{
		ArrayList<Integer> rolls = new ArrayList<>();
		if (dice < 0)	{
			rolls.add(0);
			return rolls;
		}
		int hits = 0;
		int roll = 0;
		
		for(int i = 0; i < dice; i++)	{
			roll = RandomInt.randInt(1, 6);
				if (roll > 4)	{
					hits++;
				}
			rolls.add(roll);
		}
		rolls.add(hits);
		return rolls;
	}
}