package com.sr.person;

import com.sr.gear.Armor;
import com.sr.gear.Cyberware;
import com.sr.gear.Weapon;
import com.sr.skill.Skill;
import com.sr.attribute.Attribute;
import com.sr.attribute.DerivedAttributes;
import com.sr.combatant.DiceRoller;

public interface Person extends DiceRoller {
	public abstract Attribute getAttr();
	public abstract DerivedAttributes getDrvdAttr();
	public abstract MetaType getMetatype();
	public abstract Skill getSkill();
	public abstract Cyberware getCyberware();
	public abstract Role getRole();
	
	public abstract void setName(String n);
	public abstract void setWpn(Weapon wpn);
	public abstract void setArmor(Armor arm);
}