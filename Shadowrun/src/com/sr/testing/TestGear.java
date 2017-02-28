package com.sr.testing;

import com.sr.gear.*;
import com.sr.person.MetaType;
import com.sr.person.Person;
import com.sr.person.StreetSamurai;
import com.sr.person.SpellCaster;
import com.sr.person.Rigger;
import com.sr.attribute.Attributes;
import com.sr.combatant.StatusEffects;

import java.util.Arrays;
import java.util.Collections;
import com.sr.utils.ArmComparator;

public class TestGear {

	public static void main(String[] args) {
//		// Test Cyberware
//		StreetSamurai joe = new StreetSamurai("Joe", MetaType.HUMAN);
//		System.out.println(joe.getInitiative().rollIni());
//		joe.printInfo();
//		System.out.printf("%s's ESS %.1f %n", joe.getName(), joe.getAttr().getEss());
//		
//		Rigger rigi = new Rigger("Rigi", MetaType.ELF);
//		rigi.printInfo();
//		System.out.printf("%s's ESS %.1f %n", rigi.getName(), rigi.getAttr().getEss());
		
		// Test Armor
		StreetSamurai jack = new StreetSamurai("Jack", MetaType.HUMAN);
		Rigger jane = new Rigger("Jane", MetaType.ELF);
		Armor armorJacket = new Armor(ArmorType.ARMOR, "Armor Jacket", 12, false);
		Armor linedCoat = new Armor(ArmorType.ARMOR, "Testing Coat", 8, false);
		Armor helmet = new Armor(ArmorType.HELMETS, "Helmet", 20, true);
		System.out.printf("%s has armor: %s%n", jane.getName(), jane.getArmor());
		System.out.printf("%s has armor: %s%n", jack.getName(), jack.getArmor());
		System.out.println(armorJacket.toString());
		jack.setArmor(armorJacket);
		jack.setArmor(linedCoat);
		jack.setArmor(helmet);
		jack.setArmor(helmet);	
		System.out.println(jack.getArmor());
		System.out.printf("Armor Rating: %d%n", jack.getArmor().getArmRating());
		jack.printInfo();
		System.out.println(Arrays.toString(jack.getAttr().getAttrMod()));
		
//		// Test Weapon + Cyberware
//		System.out.printf("Jane Attr: %s%n", jane.getAttr().toString());
//		System.out.println(jane.getDrvdAttr().toString());
//		System.out.printf("Jane Wpn: %s%n", jane.getWpn().toString());
//		System.out.println(jack.getAttr().toString());
//		System.out.println(jack.getDrvdAttr().toString());
//		System.out.printf("Jack Wpn: %s%n", jack.getWpn().toString());


		/*RangedWeapon predator = new RangedWeapon(WeaponType.HEAVY_PISTOL, "Ares Predator V", 5, 8, -1);
		RangedWeapon defiance = new RangedWeapon(WeaponType.TASER, "Defiance EX Shocker", 4, 9, -5, true, VarDmg.NONE);
		MeleeWeapon monofilament = new MeleeWeapon(WeaponType.OTHER, "Monofilament Whip", 5, 12, -8, 2, false, VarDmg.NONE);
		
		System.out.println(predator);
		System.out.println(defiance);
		System.out.println(monofilament);
		System.out.println(monofilament.getWeaponType().isRanged());
		System.out.println(armorJacket);
		System.out.println(unarmored);
		System.out.println(helmet);
		
		SpellCaster jane = new SpellCaster("Joe", MetaType.ELF);
		jane.setArmor(armorJacket);
		jane.setArmor(helmet);
		System.out.println(jane.getArmor());
		int[] attrMod = new int [Attributes.values().length];
		attrMod = jane.getAttr().getAttrMod();
		System.out.println(attrMod[Attributes.AGILITY.getIndex()]);
		System.out.println(attrMod[Attributes.REACTION.getIndex()]);
		System.out.println(attrMod[0]);*/
	}
}
