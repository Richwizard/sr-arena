package com.sr.gear;

import com.sr.person.Person;
import com.sr.combatant.Combatant;

public abstract class MetaWeapon implements Weapon {
	
	private String name;
	private WeaponType type;
	private int acc, dmg, armPen;
	private boolean isStun;
	private VarDmg varDmg;
	
	public MetaWeapon(WeaponType type, String name, int acc, int dmg, int armPen)	{
		this.type = type;
		this.name = name;
		this.acc = acc;
		this.dmg = dmg;
		this.armPen = armPen;
		isStun = false;
		varDmg = VarDmg.NONE;
	}
	
	public MetaWeapon(WeaponType type, String name, int acc, int dmg, int armPen, boolean isStun, VarDmg varDmg)	{
		this(type, name, acc, dmg, armPen);
		this.isStun = isStun;
		this.varDmg = varDmg;
	}
	
	@Override
	public VarDmg varDmg()	{
		return varDmg;
	}
	
	public void setVarDmg(int base)	{
		dmg = getVarDmg(base);
	}
	
	public int getVarDmg(int base)	{
		switch (varDmg())	{
		case NONE:
		default:
			return dmg;
		case RAPLUS5:
			return VarDmg.RAPLUS5.getVarDmg(acc);
		case STR:
			return VarDmg.STR.getVarDmg(base);
		case STRPLUS1:
			return VarDmg.STRPLUS1.getVarDmg(base);
		case STRPLUS2:
			return VarDmg.STRPLUS2.getVarDmg(base);
		case STRPLUS3:
			return VarDmg.STRPLUS3.getVarDmg(base);
		case STRPLUS5:
			return VarDmg.STRPLUS5.getVarDmg(base);
	}
}
	
	@Override
	public String toString()	{
		char c = (isStun) ? c = 'S' : 'P';
		return  name + " (" + type.getWeaponTypeFormatted() + "), " + "ACC " + acc + ", DV " + dmg + c + ", AP" + armPen;
	}
	
	public String toStringShort()	{
		char c = (isStun) ? c = 'S' : 'P';
		return  name + " (ACC " + acc + ", DMG " + dmg + c + ", AP" + armPen + ")";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public WeaponType getWeaponType() {
		return type;
	}

	@Override
	public int getDmg() {
		return dmg;
	}
	
	@Override
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	@Override
	public int getAP() {
		return armPen;
	}

	@Override
	public int getAcc() {
		return acc;
	}
	
	@Override
	public void setAcc(int acc)	{
		this.acc = acc;
	}

	@Override
	public boolean isStun() {
		return isStun;
	}
	
	public void setIsStun(boolean isStun) {
		this.isStun = isStun;
	}
	
	public static int checkVarDmg(Person pers, Weapon wpn)	{
		if (wpn.varDmg() != VarDmg.NONE)	{
			if (wpn.getName() == "Unarmed")	{
				if(pers.getCyberware().getVarDmgMod() > 0)	{
					return wpn.getVarDmg(pers.getAttr().getStr() + pers.getCyberware().getVarDmgMod());
				}
			} else	{
				return wpn.getVarDmg(pers.getAttr().getStr());
			}
			return wpn.getVarDmg(pers.getAttr().getStr());
		}
		return wpn.getDmg();
	}
	
	public static boolean checkVarStun(Person pers, Weapon wpn)	{
		if (wpn.getName() == "Unarmed")	{
			if(pers.getCyberware().getVarDmgMod() > 0)	{
				return false;
				}
			}
		return wpn.isStun();
	}
	
	public static int checkVarLimit(Person pers, Weapon wpn)	{
		if (wpn.getAcc() == -1)	{
			return pers.getDrvdAttr().getPhyLmt();
		}
		return wpn.getAcc();
	}
	
	public static void setStat(Person pers, Weapon wpn)	{
		Combatant cmbtnt = (Combatant) pers;
		if (wpn.getWeaponType().isRanged())	{
			cmbtnt.getStat().setHasRanged(true);
		} else	{
			cmbtnt.getStat().setHasMelee(true);
		}
	}
}
