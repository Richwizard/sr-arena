package com.sr.testing;

import com.sr.person.MetaType;
import com.sr.person.Rigger;
import com.sr.skill.Skills;
import com.sr.summons.Drone;
import com.sr.combat.Attack;
import com.sr.gear.*;

public class TestDrone {
	
	public static void main(String[] args)	{
		Rigger rigi = new Rigger("Rigi", MetaType.DWARF);
		rigi.getSkill().setSkillByIndex(Skills.PISTOLS.getIndex(), 6);
		RangedWeapon defiance = new RangedWeapon(WeaponType.TASER, "Defiance EX Shocker", 4, 6, -2, false, VarDmg.NONE);
		Drone doberman = new Drone(rigi, "Ares Duelist", 5, 3, 1, 4, 4, 3, 3, 0);
		System.out.println(doberman.getStat().isTargetable());
		System.out.println(doberman.getStat().isExhstd());
		rigi.setWpn(defiance);
		new Attack(rigi, doberman);
		new Attack(doberman, rigi);
	}

}
