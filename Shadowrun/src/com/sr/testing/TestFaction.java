package com.sr.testing;

import java.util.HashSet;

import com.sr.person.Faction;


public class TestFaction {

	public static void main(String[] args) {
		Faction fctn1 = Faction.PATROL_OF_SCHIZOSOLDIERS;
		Faction fctn2 = Faction.PATROL_OF_SCHIZOSOLDIERS;
		
		HashSet<Faction> factions = new HashSet<>();
		factions.add(fctn1);
		factions.add(fctn2);
		
		System.out.println(factions.toString());
	}
}
