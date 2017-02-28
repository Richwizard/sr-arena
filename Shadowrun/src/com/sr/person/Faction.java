package com.sr.person;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public enum Faction {

	// Check http://www.seventhsanctum.com/generate.php?Genname=superorganization	
	NONE("None", 0),
	THE_HAND_OF_SHIVA("The Hand of Shiva", 1),
	THE_WRECKING_CREW("The Wrecking Crew", 2),
	BLOODWIND("Bloodwind", 3),
	SIGMA_THIEVES("Sigma Thieves", 4),
	PATROL_OF_SCHIZOSOLDIERS("Patrol of Schizosoldiers", 5),
	DREAD_HOST("Dread Host", 6),
	YAKUZA("Yakuza", 7),
	CHOSON_RING("Choson Ring", 8),
	WEREWOLVES_OF_RUSSIA("Werewolves of Russia", 9),
	TURBOMARINES("Turbomarines", 10),
	BRIGADE_OF_NIGHT("Brigade of Night", 11),
	PREACHERS_OF_THE_BUMM_ZACK("Servants of Bumm-Zack", 12),
	FELLOWSHIP_OF_DADDY_D("Fellowship of Daddy-D", 13),
	ORDER_OF_ETERNITY("Order of Eternity", 14),
	BATTLE_PACK("Battle Pack", 15);
	
	private final String fctn;
	private final int index;
	private static final Map<Integer,Faction> map;
	
	static	{
		map = new HashMap<Integer,Faction>();
		for (Faction fctn : Faction.values())	{
			map.put(fctn.index, fctn);
		}
	}
	
	private Faction(String fctn, int index)	{
		this.fctn = fctn;
		this.index = index;
	}
	
	public static Faction findByIndex(int index)	{
		return map.get(index);
	}
	
	@Override
	public String toString()	{
		return fctn;
	}
	
	public int getIndex()	{
		return index;
	}
		
	public static ArrayList<Integer> getIndices()	{
		ArrayList<Integer> indices = new ArrayList<>();
		for (Faction faction : Faction.values())	{
			indices.add(faction.getIndex());
		}
 		return indices;
	}
}
