package com.sr.person;

import java.util.ArrayList;

import com.sr.attribute.Attribute;
import com.sr.attribute.DerivedAttributes;
import com.sr.combat.DmgType;
import com.sr.combatant.Combatant;
import com.sr.combatant.CombatantType;
import com.sr.combatant.StatusEffects;
import com.sr.gear.Cyberware;
import com.sr.gear.CyberwareGear;
import com.sr.gear.Weapon;
import com.sr.gear.WeaponType;
import com.sr.initiative.Initiative;
import com.sr.initiative.PersonInitiative;
import com.sr.gear.Armor;
import com.sr.gear.MeleeWeapon;
import com.sr.gear.MetaWeapon;
import com.sr.gear.VarDmg;
import com.sr.skill.Skill;

public abstract class Runner implements Person, Combatant {
	protected String name;
	protected MetaType metaType;
	protected Role role;
	protected Armor arm;
	protected Attribute attr;
	protected DerivedAttributes drvdAttr;
	protected StatusEffects stat;
	protected Weapon wpn;
	protected Faction fctn;
	protected Skill skill;
	protected Cyberware cyberware;
	protected Initiative initiative;
	protected CombatantType cmbtntType;
	
	{
		arm = new Armor();
		fctn = Faction.NONE;
		cmbtntType = CombatantType.PERSON;
	}
	
	/**
	 * The order is important here: Attributes needs to be initialized before
	 * cyberware (which affects attributes), and the derived attributes need
	 * to be initialized after, because they are calculated based on the 
	 * attribute values modified by cyberware. What a mess!
	 */
	public Runner(MetaType metaType, Role role, ArrayList<CyberwareGear> cyberware)	{
		setMetaType(metaType);
		setRole(role);
		attr = new Attribute(this);
		this.cyberware = new Cyberware();
		for(CyberwareGear cyber : cyberware)	{
			this.getCyberware().addCyberware(this, cyber);
		}		
		drvdAttr = new DerivedAttributes(attr.getAttr(), attr.getEss());
		skill = new Skill(role);
		stat = new StatusEffects(this);
	}

	@Override
	public void setWpn(Weapon wpn)	{
		stat.resetHasWpn();
		this.wpn = wpn;
		wpn.setDmg(MetaWeapon.checkVarDmg(this, wpn)); 
		wpn.setIsStun(MetaWeapon.checkVarStun(this, wpn));
		wpn.setAcc(MetaWeapon.checkVarLimit(this, wpn));
		MetaWeapon.setStat(this, wpn);
	}

	@Override
	public DerivedAttributes getDrvdAttr()	{
		return drvdAttr;
	}

	@Override
	public void setArmor(Armor armor)	{
		arm.addArmor(this, armor);
	}

	@Override
	public String toString()	{
		return role.getRoleName() + " " + name + " (" + metaType.toString() + ")";
	}

	@Override
	public MetaType getMetatype() {
		return metaType;
	}
	
	private void setMetaType(MetaType metaType) {
		this.metaType = metaType;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Weapon getWpn()	{
		if(wpn == null)	{
			setWpn(new MeleeWeapon(WeaponType.UNARMED, "Unarmed", -1, 0, 0, 0, true, VarDmg.STR)); 
		}
		return wpn;
	}	

	@Override
	public Armor getArmor()	{
		return arm;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Attribute getAttr()	{
		return attr;
	}

	@Override
	public StatusEffects getStat() {
		return stat;
	}

	@Override
	public Faction getFctn()	{
		return fctn;
	}
	
	public void setFctn(Faction fctn)	{
		this.fctn = fctn;
	}

	@Override
	public void setFctnByIndex(int rnd)	{
		setFctn(Faction.findByIndex(rnd));
	}

	@Override
	public Skill getSkill()	{
		return skill;
	}

	@Override
	public Cyberware getCyberware()	{
		return cyberware;
	}
	
	public Runner getInstance()	{
		return this;
	}

	@Override
	public Initiative getInitiative()	{
		if(initiative == null)	{
			Person pers = (Person) this;
			this.initiative = new PersonInitiative(pers);
		}
		return initiative;
	}

	@Override
	public CombatantType getCmbtntType()	{
		return cmbtntType;
	}

	@Override
	public int getAtckDicePool()	{
		int skillValue = getSkill().getVal(getWpn().getWeaponType().getSkill().getIndex());
		int mod = getStat().getWoundMod();
		return getAttr().getAgi() + skillValue + mod;
	}

	@Override
	public String getAtckDicePoolString()	{
		String desc = String.format(	"(AGI %d, %s %d, Wounded %+d)", getAttr().getAgi(), 
									getWpn().getWeaponType().getSkill().toString(), 
									getSkill().getVal(getWpn().getWeaponType().getSkill().getIndex()),
									getStat().getWoundMod());
		return desc;
	}

	@Override
	public int getAtckLimit()	{
		return getWpn().getAcc();
	}

	@Override
	public int getDefDicePool()	{
		return getAttr().getRea() + getAttr().getInt();
	}

	@Override
	public String getDefDicePoolString()	{
		return String.format("REA %d, INT %d", getAttr().getRea(), getAttr().getInt());
	}

	@Override
	public int getDefLimit()	{
		return 0;
	}

	@Override
	public String getCdm()	{
		return getStat().cdmToString();
	}
	
	@Override
	public int getBod()	{
		return getAttr().getBod();
	}
	
	@Override
	public int[] getDmgTrks()	{
		int[] dmgTrks = new int[2];
		dmgTrks[0] = drvdAttr.getPhyDmgTrk();
		dmgTrks[1] = drvdAttr.getStunDmgTrk();
		return dmgTrks;
	}
	
	@Override
	public boolean isDmgImmune(DmgType dmgType)	{
		return false;
	}
	
	public void printInfo()	{
		System.out.println(toString() + " Faction: " + getFctn());
		System.out.println(getAttr().toStringBase());
		System.out.println(getAttr().toString());
		System.out.printf(getSkill().toStringWithGroups());
		System.out.println(getCyberware().toString());
		System.out.println(getWpn());
		System.out.println(getArmor());
		System.out.println(getCdm());
		System.out.println(getStat());
	}
}