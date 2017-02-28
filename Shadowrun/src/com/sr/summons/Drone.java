package com.sr.summons;

import com.sr.person.Rigger;
import com.sr.utils.RandomInt;
import com.sr.person.Faction;
import com.sr.gear.Armor;
import com.sr.gear.ArmorType;
import com.sr.gear.RangedWeapon;
import com.sr.gear.MeleeWeapon;
import com.sr.gear.VarDmg;
import com.sr.gear.WeaponType;
import com.sr.initiative.DroneInitiative;
import com.sr.initiative.Initiative;
import com.sr.gear.Weapon;
import com.sr.combat.DmgType;
import com.sr.combatant.Combatant;
import com.sr.combatant.CombatantType;
import com.sr.combatant.StatusEffects;

public class Drone implements Combatant, Summon {
	private int handling, speed, acceleration, body, armor, pilot, sensor, seats, wpnAutoSoft, mnvrngAutoSoft;
	private String name;
	private Armor arm;
	private CombatantType cmbtntType;
	private Faction fctn;
	private Initiative initiative;
	private Rigger owner;
	private StatusEffects stat;
	private Weapon wpn;
	
	/**
	 * Initialization order important, stat --> CombatantType --> wpn.
	 */
	public Drone(Rigger owner, String name, int handling, int speed, int acceleration, int body, int armor, int pilot, int sensor, int seats)	{
		this.owner = owner;
		this.name = name;
		this.handling = handling;
		this.speed = speed;
		this.acceleration = acceleration;
		this.body = body;
		this.armor = armor;
		this.pilot = pilot;
		this.sensor = sensor;
		this.seats = seats;
		this.fctn = owner.getFctn();
		this.cmbtntType = CombatantType.DRONE;
		this.stat = new StatusEffects(this);
		setWpnAutoSoft();
		setMnvrngAutoSoft();
		setWpn();
		setArm();
	}
	
	private void setWpnAutoSoft()	{
		this.wpnAutoSoft = RandomInt.randInt(1, pilot);
	}
	
	private void setMnvrngAutoSoft()	{
		this.mnvrngAutoSoft = RandomInt.randInt(1, pilot);
	}
	
	private void setWpn()	{
		stat.resetHasWpn();
		if (name.equals("Ares Duelist"))	{
			this.wpn = new MeleeWeapon(WeaponType.BLADE, "Sword", 6, body + 3, -2, 1, false, VarDmg.NONE);
			this.wpnAutoSoft = 3;
		} else	{
			int rndm = RandomInt.randInt(1, 2);
			if(rndm == 1)
				this.wpn = new RangedWeapon(WeaponType.MACHINE_GUN, "Ingram Valiant", 5, 9, -2, false, VarDmg.NONE);
			if(rndm == 2)
				this.wpn = new RangedWeapon(WeaponType.MACHINE_GUN, "Stoner-Ares M202", 5, 10, -3, false, VarDmg.NONE);
		}
	}
	
	private void setArm()	{
		this.arm = new Armor(ArmorType.DRONE_ARMOR, "Drone Armor", armor, false);
	}
	
	@Override
	public Weapon getWpn()	{
		return wpn;
	}
	
	@Override
	public Armor getArmor()	{
		return arm;
	}
	
	@Override
	public Faction getFctn()	{
		return fctn;
	}
	
	@Override
	public String getName()	{
		return name;
	}
	
	@Override
	public Initiative getInitiative()	{
		if (initiative == null)	{
			initiative = new DroneInitiative(this);
		}
		return initiative;
	}
	
	public int getPilot()	{
		return pilot;
	}
	
	@Override
	public StatusEffects getStat()	{
		return stat;
	}
	
	@Override
	public CombatantType getCmbtntType()	{
		return cmbtntType;
	}
	
	public void setFctn(Faction fctn)	{
		this.fctn = fctn;
	}
	
	@Override
	public void setFctnByIndex(int rnd)	{
		setFctn(Faction.findByIndex(rnd));
	}
	
	@Override
	public int getAtckDicePool()	{
		return pilot * 2 + wpnAutoSoft;
	}
	
	@Override
	public String getAtckDicePoolString()	{
		String desc = String.format("((PIL*2) %d, AUTOSOFT %d)", pilot*2, wpnAutoSoft);
		return desc;
	}
	
	// Muss ich nachschauen.
	@Override
	public int getAtckLimit()	{
		return 0;
	}
	
	// Drones roll their Pilot + Autosoft [Handling]
	@Override
	public int getDefDicePool()	{
		return pilot + mnvrngAutoSoft;
	}
	
	@Override
	public String getDefDicePoolString()	{
		return String.format("PIL %d, AUTOSOFT %d", pilot, mnvrngAutoSoft);
	}
	
	@Override
	public int getDefLimit()	{
		return handling;
	}
	
	@Override
	public int getBod()	{
		return body;
	}
	
	// Drones have a Condition Monitor equal to 6 plus half their Body.
	@Override
	public int[] getDmgTrks()	{
		int[] dmgTrks = new int[2];
		dmgTrks[0] = 6 + body / 2;
		dmgTrks[1] = 0;
		return dmgTrks;
	}
	
	// Drones have a Condition Monitor equal to 6 plus half their Body.
	@Override
	public String getCdm()	{
		return getStat().cdmToString(6 + body / 2);
	}
	
	@Override
	public String toString()	{
		return String.format("%s (%s)", name, owner.getName());
	}
	
	@Override
	public boolean isDmgImmune(DmgType dmgType)	{
		if (dmgType == DmgType.STUN)	{
			return true;
		}
		return false;
	}
	
	@Override
	public Combatant getOwner()	{
		return owner;
	}
}
