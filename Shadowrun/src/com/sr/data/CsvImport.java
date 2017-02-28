package com.sr.data;

import java.io.*;
import java.util.ArrayList;

import com.sr.gear.*;

import org.apache.commons.csv.*;

public class CsvImport {
	public static ArrayList<Weapon> rngdWpnImprt() {
		ArrayList<Weapon> rngdWpns = new ArrayList<>();
		try{
			Reader in = new FileReader("data/RangedWeapons.csv");
			Iterable<CSVRecord> weaponsCsv = 
				CSVFormat.EXCEL.withHeader("WeaponType", "Name", "Accuracy", "Damage", "AP", "IsStun", "VarDmg").withSkipHeaderRecord().parse(in);
			for (CSVRecord rngdWpn : weaponsCsv)	{
				String sWeaponType = rngdWpn.get("WeaponType");
				String name = rngdWpn.get("Name");
				String sAccuracy = rngdWpn.get("Accuracy");
				String sDamage = rngdWpn.get("Damage");
				String sAp = rngdWpn.get("AP");
				String sIsStun = rngdWpn.get("IsStun");
				String sVarDmg = rngdWpn.get("VarDmg");
				rngdWpns.add(new RangedWeapon(WeaponType.valueOf(sWeaponType), 
						name, Integer.parseInt(sAccuracy), Integer.parseInt(sDamage), 
						Integer.parseInt(sAp), Boolean.parseBoolean(sIsStun), VarDmg.valueOf(sVarDmg)));
			}
		} catch (IOException e)	{
			e.printStackTrace();
		}
		System.out.printf("Successfully imported %d Ranged Weapons.%n", rngdWpns.size());
		return rngdWpns;
	}
	
	public static ArrayList<Weapon> meleeWpnImprt() {
		ArrayList<Weapon> meleeWpns = new ArrayList<>();
		try{
			Reader in = new FileReader("data/MeleeWeapons.csv");
			Iterable<CSVRecord> weaponsCsv = 
				CSVFormat.EXCEL.withHeader("WeaponType", "Name", "Accuracy", "Damage", "AP", "IsStun", "VarDmg", "Reach").withSkipHeaderRecord().parse(in);
			for (CSVRecord meleeWpn : weaponsCsv)	{
				String sWeaponType = meleeWpn.get("WeaponType");
				String name = meleeWpn.get("Name");
				String sAccuracy = meleeWpn.get("Accuracy");
				String sDamage = meleeWpn.get("Damage");
				String sAp = meleeWpn.get("AP");
				String sIsStun = meleeWpn.get("IsStun");
				String sVarDmg = meleeWpn.get("VarDmg");
				String sReach = meleeWpn.get("Reach");
				meleeWpns.add(new MeleeWeapon(WeaponType.valueOf(sWeaponType), 
						name, Integer.parseInt(sAccuracy), Integer.parseInt(sDamage), 
						Integer.parseInt(sAp), Integer.parseInt(sReach), Boolean.parseBoolean(sIsStun), 
						VarDmg.valueOf(sVarDmg)));
			}
		} catch (IOException e)	{
			e.printStackTrace();
		}
		System.out.printf("Successfully imported %d Melee Weapons.%n", meleeWpns.size());
		return meleeWpns;
	}
	
	public static ArrayList<Armor> armorImprt() {
		ArrayList<Armor> armors = new ArrayList<>();
		try{
			Reader in = new FileReader("data/Armors.csv");
			Iterable<CSVRecord> armorsCsv = 
				CSVFormat.EXCEL.withHeader("ArmorType", "Name", "ArmorRating", "IsCumulative").withSkipHeaderRecord().parse(in);
			for (CSVRecord armor : armorsCsv)	{
				String sArmorType = armor.get("ArmorType");
				String name = armor.get("Name");
				String sArmorRating = armor.get("ArmorRating");
				String sIsCumulative = armor.get("IsCumulative");

				armors.add(new Armor(ArmorType.valueOf(sArmorType), name, Integer.parseInt(sArmorRating), Boolean.parseBoolean(sIsCumulative)));
			}
		} catch (IOException e)	{
			e.printStackTrace();
		}
		System.out.printf("Successfully imported %d Armors.%n", armors.size());
		return armors;
	}
	
	public static ArrayList<Armor> armorCmltveImprt() {
		ArrayList<Armor> armorsCmltve = new ArrayList<>();
		try{
			Reader in = new FileReader("data/CmltveArmors.csv");
			Iterable<CSVRecord> armorsCsv = 
				CSVFormat.EXCEL.withHeader("ArmorType", "Name", "ArmorRating", "IsCumulative").withSkipHeaderRecord().parse(in);
			for (CSVRecord armor : armorsCsv)	{
				String sArmorType = armor.get("ArmorType");
				String name = armor.get("Name");
				String sArmorRating = armor.get("ArmorRating");
				String sIsCumulative = armor.get("IsCumulative");

				armorsCmltve.add(new Armor(ArmorType.valueOf(sArmorType), name, Integer.parseInt(sArmorRating), Boolean.parseBoolean(sIsCumulative)));
			}
		} catch (IOException e)	{
			e.printStackTrace();
		}
		System.out.printf("Successfully imported %d Cumulative Armors.%n", armorsCmltve.size());
		return armorsCmltve;
	}
}
