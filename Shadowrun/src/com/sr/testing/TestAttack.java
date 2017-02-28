package com.sr.testing;

import java.util.Arrays;

import com.sr.combat.Attack;
import com.sr.gear.Armor;
import com.sr.gear.ArmorType;
import com.sr.gear.MeleeWeapon;
import com.sr.gear.VarDmg;
import com.sr.gear.WeaponType;
import com.sr.person.MetaType;
import com.sr.person.*;

public class TestAttack {

	public static void main(String[] args) {	
		SpellCaster jane = new SpellCaster("Jane", MetaType.ELF);
		Armor riot = new Armor(ArmorType.SHIELDS, "Riot shield", 6, true);
		Armor urban = new Armor(ArmorType.ARMOR, "Urban Explorer Jumpsuit", 9, false);
		MeleeWeapon monofilament = new MeleeWeapon(WeaponType.OTHER, "Monofilament Whip", 5, 12, -8, 2, false, VarDmg.NONE);
		MeleeWeapon club = new MeleeWeapon(WeaponType.CLUB, "Clubbowich", 5, 8, -4, 0, false, VarDmg.NONE);
		System.out.println(jane.getAttr().toString());
		System.out.println(jane.getAttr().toStringBase());
		jane.setArmor(urban);
		System.out.println(jane.getArmor());
		jane.getStat().setEncmbrnce(jane);
		System.out.println(Arrays.toString(jane.getStat().getEncmbrnceMod()));
		jane.getStat().addPhyDmg(6);
		StreetSamurai trog = new StreetSamurai("Trog", MetaType.TROLL);
		jane.setWpn(monofilament);
		trog.setWpn(club);
		System.out.println("Jane: " + jane.getAttr().toStringBase());
		System.out.println("Trog: " + trog.getAttr().toStringBase());
		System.out.println("Trog PhysLimit: " + trog.getDrvdAttr().getPhyLmt());
		new Attack(trog, jane);
	}
}
