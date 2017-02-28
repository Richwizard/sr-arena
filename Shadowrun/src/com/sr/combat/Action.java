package com.sr.combat;

import java.util.ArrayList;

import com.sr.combatant.Combatant;
import com.sr.summons.Summon;
import com.sr.person.*;
import com.sr.summons.*;

public class Action {
	ArrayList<Combatant> combtnts;
	ArrayList<Summon> spawns = new ArrayList<Summon>();
	private Combatant agnt, trgt;
	private Arena arena;

	public Action(Arena arena, Combatant agnt, Combatant trgt)	{
		this.arena = arena;
		this.agnt = agnt;
		this.trgt = trgt;
		takeActn();
	}
		
	private void takeActn()	{
		switch (agnt.getCmbtntType())	{
		case PERSON:
			Person pers = (Person) agnt;
			takeActnPers(pers);
			break;
		case DRONE:
			Drone drone = (Drone) agnt;
			takeActnDrone(drone);
			break;
		}
	}
		
	private void takeActnPers(Person pers)	{
		switch(pers.getRole())	{
		case STREET_SAMURAI:
		case SPELLCASTER:
			new Attack(agnt, trgt);
			break;
		case RIGGER:
			Rigger rigger = (Rigger) pers;
			takeActnRigger(rigger);
			break;
		}
	}
	
	private void takeActnRigger(Rigger rigger)	{
		if(! rigger.isDroneSpawned())	{
			rigger.spawnDrone();
			arena.addSummon(rigger.getDrone());
			System.out.printf("%s has spawned %s to arrive next turn!%n", agnt, rigger.getDrone());
		} else	{
			new Attack(agnt, trgt);
		}
	}
	
	private void takeActnDrone(Drone drone)	{
		new Attack(agnt, trgt);
	}
}