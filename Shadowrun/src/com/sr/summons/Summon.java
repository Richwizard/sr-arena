package com.sr.summons;

import com.sr.combatant.Combatant;

public interface Summon extends Combatant {
	public abstract Combatant getOwner();
}