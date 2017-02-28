package com.sr.testing;

import java.util.Arrays;

import com.sr.gear.Armor;
import com.sr.gear.ArmorType;
import com.sr.person.MetaType;
import com.sr.person.SpellCaster;
import com.sr.person.Rigger;
import com.sr.person.Person;

public class TestStatusEffects {

	public static void main(String[] args) {
		
		// Test Encumbrance
		SpellCaster jane = new SpellCaster("Jane", MetaType.ELF);
		Armor riot = new Armor(ArmorType.SHIELDS, "Riot shield", 6, true);
		Armor urban = new Armor(ArmorType.ARMOR, "Urban Explorer Jumpsuit", 9, false);
		jane.getAttr().setStr(1, jane.getMetatype());
		jane.setArmor(urban);
		jane.setArmor(riot);
		jane.getArmor();
		jane.getStat().setEncmbrnce(jane);
		jane.printInfo();
		
//		// Test DMG and CDM Messages
//		Rigger rigi = new Rigger("Rigi", MetaType.ORK);
//		System.out.println(rigi.getAttr().toString());
//		System.out.println(rigi.getDrvdAttr().toString());
//		rigi.getStat().addStunDmg(6, rigi);
//		System.out.println("Wound Mod: " + rigi.getStat().getWoundMod());
//		System.out.println(rigi.getCdm());
//		rigi.getStat().addStunDmg(6, rigi);
//		System.out.println(rigi.getCdm());
//		System.out.println(rigi.getStat().isTargetable());
	}
}
