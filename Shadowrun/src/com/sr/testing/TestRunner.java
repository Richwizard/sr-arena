package com.sr.testing;

import com.sr.gear.RangedWeapon;
import com.sr.gear.WeaponType;
import com.sr.person.MetaType;
import com.sr.person.Rigger;
import com.sr.person.SpellCaster;
import com.sr.person.StreetSamurai;
import com.sr.skill.Skills;
import com.sr.person.Person;


import java.util.Arrays;

import com.sr.attribute.Attributes;
import com.sr.combat.Attack;

public class TestRunner {

	public static void main(String[] args) {		
		// Test Attributes
//		System.out.printf("Charisma limit: %d%n", Attributes.CHARISMA.getAttrLimit(MetaType.TROLL));
//		StreetSamurai joe = new StreetSamurai("Joe", MetaType.HUMAN);
//		Rigger rigi = new Rigger("Rigi", MetaType.DWARF);
//		System.out.printf("Joe BOD: %d%n", joe.getAttr().getBod());
//		System.out.println(rigi.getAttr().toString());
//		System.out.println(rigi.getAttr().toStringBase());
		
		// Test Print Info
		Rigger rigi = new Rigger("Rigi", MetaType.DWARF);
		rigi.printInfo();
	}
}