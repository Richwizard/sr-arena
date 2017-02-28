package com.sr.skill;

import java.util.HashMap;
import java.util.Map;

public enum SkillGroup {
	NONE(0, "None"),
	ACTING(1, "Acting"),
	ATHLETICS(2, "Athletics"),
	BIOTECH(3, "Biotech"),
	CLOSE_COMBAT(4, "Close Combat"),
	CONJURING(5, "Conjuring"),
	CRACKING(6, "Cracking"),
	ELECTRONICS(7, "Electronics"),
	ENCHANTING(8, "Enchanting"),
	FIREARMS(9, "Firearms"),
	INFLUENCE(10, "Influence"),
	ENGINEERING(11, "Engineering"),
	OUTDOORS(12, "Outdoors"),
	SORCERY(13, "Sorcery"),
	STEALTH(14, "Stealth"),
	TASKING(15, "Tasking");
	
	private static final Map<Integer,SkillGroup> map;
	private final int index;
	private final String name;
	
	static	{
		map = new HashMap<Integer,SkillGroup>();
		for (SkillGroup skillGroup : SkillGroup.values())	{
			map.put(skillGroup.index, skillGroup);
		}
	}
	
	public static SkillGroup findByIndex(int index)	{
		return map.get(index);
	}
	
	SkillGroup(int index, String name)	{
		this.index = index;
		this.name = name;
	}
	
	public int getIndex()	{
		return index;
	}
	
	@Override
	public String toString()	{
		return name;
	}
}
