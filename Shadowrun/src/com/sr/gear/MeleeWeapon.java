package com.sr.gear;

public class MeleeWeapon extends MetaWeapon implements Weapon {
	private int reach;
	
	public MeleeWeapon(WeaponType type, String name, int accuracy, int damage, int armorPenetration, int reach, boolean isStun, VarDmg varDmg)	{
		super(type, name, accuracy, damage, armorPenetration, isStun, varDmg);
		this.reach = reach;
	}
	
	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}
	
	@Override
	public String toString()	{
		return super.toString() + ", Reach: " + reach;
	}
}