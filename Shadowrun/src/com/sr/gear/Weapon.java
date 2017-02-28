package com.sr.gear;

public interface Weapon {
	public abstract String getName();
	public abstract String toString();
	public abstract String toStringShort();
	public abstract WeaponType getWeaponType();
	public abstract int getDmg();
	public abstract int getAP();
	public abstract int getAcc();
	public abstract int getVarDmg(int base);
	public abstract boolean isStun();
	public abstract VarDmg varDmg();
	public abstract void setDmg(int dmg);
	public abstract void setVarDmg(int str);
	public abstract void setAcc(int acc);
	public abstract void setIsStun(boolean isStun);
}
