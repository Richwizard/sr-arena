package com.sr.combatant;

import com.sr.gear.Armor;
import com.sr.gear.Weapon;
import com.sr.initiative.Initiative;
import com.sr.person.Faction;
import com.sr.combat.DmgType;

public interface Combatant extends DiceRoller {
	public abstract Armor getArmor();
	public abstract Weapon getWpn();
	public abstract Faction getFctn();
	public abstract Initiative getInitiative();
	public abstract StatusEffects getStat();
	public abstract CombatantType getCmbtntType();
	public abstract int getAtckDicePool();
	public abstract int getAtckLimit();
	public abstract int getDefDicePool();
	public abstract int getDefLimit();
	public abstract int getBod();
	public abstract int[] getDmgTrks();
	public abstract boolean isDmgImmune(DmgType dmgType);
	public abstract String getAtckDicePoolString();
	public abstract String getDefDicePoolString();
	public abstract String getCdm();
	public abstract void setFctnByIndex(int index);
}