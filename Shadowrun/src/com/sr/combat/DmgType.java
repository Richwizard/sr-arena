package com.sr.combat;

public enum DmgType {
	PHYSICAL("Physical damage"), 
	STUN("Stun damage");
	
	private final String dmgType;

	private DmgType(String dmgType)	{
		this.dmgType = dmgType;
	}
	
	@Override
	public String toString()	{
		return dmgType;
	}
}