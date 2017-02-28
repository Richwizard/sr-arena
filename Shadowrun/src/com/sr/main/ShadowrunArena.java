package com.sr.main;

import java.util.ArrayList;

import com.sr.combat.Arena;
import com.sr.combatant.Combatant;
import com.sr.utils.CombatPrep;
import com.sr.utils.RandomInt;

public class ShadowrunArena {
	
	public static void main(String[] args) {
		/*
		 * Spawns a random amount of runners that battle to the death.
		 * Parameters of method randInt give lower and upper bound of
		 * combatants and can be adjusted (needs to be greater than two).
		 * Combatants have random attributes, skills, and equipment suited
		 * for their class. They get assigned to random factions (at least
		 * two, maximum is size of Faction Enum - 1) and then battle to death.
		 */
		
		CombatPrep cp = new CombatPrep(RandomInt.randInt(2, 12));	
		ArrayList<Combatant> combtnts = cp.getCombtnts();
		new Arena(combtnts);
	}
}
