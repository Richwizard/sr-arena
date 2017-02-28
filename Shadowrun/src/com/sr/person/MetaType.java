package com.sr.person;

import java.util.Map;
import java.util.HashMap;

public enum MetaType	{
	HUMAN("Human", 0), 
	DWARF("Dwarf", 1), 
	ELF("Elf", 2), 
	ORK("Ork", 3), 
	TROLL("Troll", 4);
	
	private final int index;
	private final String metaType;
	private static final Map<Integer,MetaType> map;

	static	{
		map = new HashMap<Integer,MetaType>();
		for (MetaType metaType : MetaType.values())	{
			map.put(metaType.index, metaType);
		}
	}
	
	private MetaType(String metaType, int index)	{
		this.metaType = metaType;
		this.index = index;
	}
	
	public static MetaType findByIndex(int index)	{
		return map.get(index);
	}
	
	@Override
	public String toString()	{
		return metaType;
	}
}