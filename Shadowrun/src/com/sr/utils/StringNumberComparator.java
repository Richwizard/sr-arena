package com.sr.utils;

import java.util.Comparator;

public class StringNumberComparator implements Comparator<String>	{
	@Override
	public int compare(String s1, String s2)	{
		String[] s1parts = s1.split(" ");
		String[] s2parts = s2.split(" ");
		String s1number = s1parts[0];
		String s2number = s2parts[0];
		int c1 = Integer.parseInt(s1number);
		int c2 = Integer.parseInt(s2number);
		return c2 - c1;
	}
}

