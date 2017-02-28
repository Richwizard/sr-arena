package com.sr.gear;

public enum Range {
	SHORT(0), MEDIUM(-1), LONG(-3), EXTREME(-6);
	
	private final int diceMod;
	
	Range(int diceMod)	{
		this.diceMod = diceMod;
	}
	
	public int getDiceMod()	{
		return diceMod;
	}
}