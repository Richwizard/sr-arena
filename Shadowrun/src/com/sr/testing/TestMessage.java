package com.sr.testing;

import java.util.ArrayList;

import com.sr.gear.RangedWeapon;
import com.sr.gear.WeaponType;
import com.sr.person.MetaType;
import com.sr.person.StreetSamurai;
import com.sr.combatant.Combatant;

import com.sr.utils.Message;

public class TestMessage {
	
	public static void main(String[] args)	{
		StreetSamurai joe = new StreetSamurai("Joe", MetaType.TROLL);
		StreetSamurai honda = new StreetSamurai("Honda", MetaType.ORK);
		RangedWeapon predator = new RangedWeapon(WeaponType.HEAVY_PISTOL, "Ares Predator V", 5, 8, -1);
		
		ArrayList<Combatant> combtnts = new ArrayList<>();
		combtnts.add(joe);
		combtnts.add(honda);
		int cmbtTrn = 1;
		Message.cdmArena(combtnts, cmbtTrn);
		int x = 1;
		int y = 2;
		System.out.println(x/y);
	}
}
