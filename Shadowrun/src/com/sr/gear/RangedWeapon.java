package com.sr.gear;

public class RangedWeapon extends MetaWeapon implements Weapon {

	public RangedWeapon(WeaponType type, String name, int accuracy, int damage, int armorPenetration)	{
		super(type, name, accuracy, damage, armorPenetration);
	}
	
	public RangedWeapon(WeaponType type, String name, int accuracy, int damage, int armorPenetration, boolean isStun, VarDmg varDmg)	{
		super(type, name, accuracy, damage, armorPenetration, isStun, varDmg);
	}	
}
