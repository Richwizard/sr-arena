package com.sr.initiative;

import com.sr.utils.Dice;
import com.sr.combatant.Combatant;
import com.sr.summons.Drone;

public class DroneInitiative extends Initiative {
	private Drone drone;
	
	public DroneInitiative(Drone drone)	{
		super((Combatant) drone);
		this.drone = drone;
	}
	
	@Override
	public int rollIni()	{
		if (drone.getStat().isTargetable())	{
			this.ini = drone.getPilot() + Dice.rollD6(4, cmbtnt);
		}
	return ini;
	}
}