package com.sr.combatant;

public enum DthStat {
	ALIVE(0, "Alive"),
	WOUNDED(1, "Wounded"),
	DAMAGED(2, "Damaged"),
	KNOCKED_OUT(3, "Knocked-out"),
	DYING(4, "Dying"),
	DEAD(5, "Dead"),
	DESTROYED(6, "Destroyed");
	
	private final int index;
	private final String name;
	
	private DthStat(int index, String name)	{
		this.index = index;
		this.name = name;
	}
	
	@Override
	public String toString()	{
		return name;
	}
	
	public int getIndex()	{
		return index;
	}
}
