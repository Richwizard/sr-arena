package com.sr.person;

import java.util.ArrayList;

import com.sr.gear.CyberwareTypes;
import com.sr.gear.Cyberware;
import com.sr.gear.CyberwareGear;
import com.sr.summons.Drone;
import com.sr.utils.RandomInt;

public class Rigger extends Runner {
	private Drone drone;
	private boolean droneSpawned;
	
	public Rigger(MetaType metaType)	{
		super(metaType, Role.RIGGER, getCyber());
	}
	
	public Rigger(String name, MetaType metaType)	{
		this(metaType);
		super.setName(name);	
	}
	
	public void spawnDrone()	{
		int rndm = RandomInt.randInt(1, 6);
		if (rndm == 1)
			this.drone = new Drone(this, "GM-Nissan Doberman", 5, 3, 1, 4, 4, 3, 3, 0);
		if (rndm == 2)
			this.drone = new Drone(this, "Aztechnology Crawler", 4, 3, 1, 3, 3, 4, 3, 0);
		if (rndm == 3)
			this.drone = new Drone(this, "Ares Duelist", 3, 3, 1, 4, 4, 3, 3, 0);
		if (rndm == 4)
			this.drone = new Drone(this, "MCT-Nissan-Roto-Drone", 4, 4, 2, 4, 4, 3, 3, 0);
		if (rndm == 5)
			this.drone = new Drone(this, "C-D Dalmatian", 5, 5, 3, 5, 5, 3, 3, 0);
		if (rndm == 6)
			this.drone = new Drone(this, "Steel Lynx", 5, 4, 2, 6, 12, 3, 3, 0);
		this.droneSpawned = true;
	}
	
	public Drone getDrone()	{
		return drone;
	}
	
	public boolean isDroneSpawned()	{
		return droneSpawned;
	}
	
	private static ArrayList<CyberwareGear> getCyber()	{
		return Cyberware.getRndmCyberware(3, CyberwareTypes.CONTROL_RIG, 
				CyberwareTypes.DERMAL_PLATING, 
				CyberwareTypes.REACTION_ENHANCERS);
	}
}