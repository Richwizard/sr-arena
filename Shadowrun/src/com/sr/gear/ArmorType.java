package com.sr.gear;

public enum ArmorType {
	UNARMORED("Unarmored"),
	CLOTHING("Clothing"),
	ARMOR("Armor"),
	HELMETS("Helmets"),
	SHIELDS("Shields"),
	CYBERWARE("Cyberware"),
	MIXED("Mixed"),
	DRONE_ARMOR("Drone Armor");
	
	private final String armorType;
	
	private ArmorType(String armorType)	{
		this.armorType = armorType; 
	}
	
	@Override
	public String toString()	{
		return armorType;
	}
}