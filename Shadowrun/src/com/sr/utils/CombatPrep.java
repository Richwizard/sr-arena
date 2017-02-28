package com.sr.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.sr.data.CsvImport;
import com.sr.data.DBController;
import com.sr.gear.Armor;
import com.sr.gear.Weapon;
import com.sr.person.MetaType;
import com.sr.person.Role;
import com.sr.person.Rigger;
import com.sr.person.Person;
import com.sr.combatant.Combatant;
import com.sr.person.SpellCaster;
import com.sr.person.StreetSamurai;

public class CombatPrep {
	private ArrayList<Armor> armors = new ArrayList<>();
	private ArrayList<Armor> armorsCmltve = new ArrayList<>();
	private ArrayList<Weapon> weapons = new ArrayList<>();
	private ArrayList<Combatant> combtnts = new ArrayList<>();
	private ArrayList<MetaType> metaTypes = new ArrayList<>();
	private ArrayList<Role> roles = new ArrayList<>();

	public CombatPrep(int combtnts)	{
		stockArsenal();
		spawnRunners(combtnts);
	}
	
	private void stockArsenal()	{
		weapons = CsvImport.rngdWpnImprt();
		weapons.addAll(CsvImport.meleeWpnImprt());
		armors = CsvImport.armorImprt();
		armorsCmltve = CsvImport.armorCmltveImprt();
		Collections.shuffle(weapons);
		Collections.shuffle(armors);
		Collections.shuffle(armorsCmltve);
	}
			
	private void spawnRunners(int amount)	{
		setArcheTypes();
		for (int i = 0; i < amount; i++)	{
			Collections.shuffle(metaTypes);
			Collections.shuffle(roles);
			if (roles.get(0) == Role.RIGGER)	{
				combtnts.add(new Rigger(metaTypes.get(0)));
			} else if (roles.get(0) == Role.SPELLCASTER)	{
				combtnts.add(new SpellCaster(metaTypes.get(0)));
			} else if (roles.get(0) == Role.STREET_SAMURAI)	{
				combtnts.add(new StreetSamurai(metaTypes.get(0)));
			}
		}
		setNames();
		equip();
		Collections.shuffle(combtnts);
	}
	
	private void setArcheTypes()	{
		for (MetaType metaType : MetaType.values())	{
			metaTypes.add(metaType);
		}
		for (Role role : Role.values())	{
			roles.add(role);
		}
	}

	private void setNames()	{
		ArrayList<String> names = new ArrayList<>(combtnts.size());
		DBController.dbController(names, combtnts.size());
		int i = 0;
		for (Combatant cmbtnt : combtnts)	{
//			System.out.println("Assigning Name: " + i + " " + names.get(i));
			((Person ) cmbtnt).setName(names.get(i));
			i++;
		}
	}
	
	private void equip()	{
		for (Combatant cmbtnt : combtnts)	{
			((Person ) cmbtnt).setArmor(armors.get((new Random()).nextInt(armors.size())));
			int randInt = RandomInt.randInt(1, 6);
			if (randInt == 5 || randInt == 6)	{
				((Person ) cmbtnt).setArmor(armorsCmltve.get((new Random()).nextInt(armorsCmltve.size())));
			}
			((Person ) cmbtnt).setWpn(weapons.get((new Random()).nextInt(weapons.size())));
		}
	}

	public ArrayList<Armor> getArmors() {
		return armors;
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	public ArrayList<Combatant> getCombtnts() {
		return combtnts;
	}
}
