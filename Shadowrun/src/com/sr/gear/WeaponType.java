package com.sr.gear;

import com.sr.skill.Skills;

public enum WeaponType {
	UNARMED("Unarmed", Skills.UNARMED_COMBAT, false),
	BLADE("Blade", Skills.BLADES, false), 
	CLUB("Club", Skills.CLUBS, false), 
	OTHER("Other", Skills.EXOTIC_MELEE_WEAPON, false), 
	BOW("Bow", Skills.ARCHERY, true), 
	CROSSBOW("Crossbow", Skills.ARCHERY, true), 
	THROWING_WEAPON("Throwing Weapon", Skills.THROWING_WEAPONS, true), 
	TASER("Taser", Skills.PISTOLS, true), 
	HOLD_OUT("Hold-Out", Skills.PISTOLS, true),
	LIGHT_PISTOL("Light Pistol", Skills.PISTOLS, true),
	HEAVY_PISTOL("Heavy Pistol", Skills.PISTOLS, true),
	MACHINE_PISTOL("Machine Pistol", Skills.AUTOMATICS, true),
	SUBMACHINE_GUN("Submachine Gun", Skills.AUTOMATICS, true),
	ASSAULT_RIFLE("Assault Rifle", Skills.AUTOMATICS, true),
	SNIPER_RIFLE("Sniper Rifle", Skills.LONGARMS, true),
	SHOTGUN("Shotgun", Skills.LONGARMS, true),
	SPECIAL_WEAPON("Special Weapon", Skills.EXOTIC_RANGED_WEAPON, true),
	MACHINE_GUN("Machine Gun", Skills.HEAVY_WEAPONS, true),
	CANNONS_LAUNCHER ("Cannons & Launcher", Skills.HEAVY_WEAPONS, true);
	
	private final String weaponType;
	private final Skills skill;
	private final boolean isRanged;
	
	private WeaponType(String weaponType, Skills skill, boolean isRanged)	{
		this.weaponType = weaponType;
		this.skill = skill;
		this.isRanged = isRanged;
	}
	
	public String getWeaponTypeFormatted()	{
		return weaponType;
	}
	
	public boolean isRanged()	{
		return isRanged;
	}
	
	public Skills getSkill()	{
		return skill;
	}
}