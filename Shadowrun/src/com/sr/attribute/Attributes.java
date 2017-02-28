package com.sr.attribute;

import com.sr.person.MetaType;
import java.util.Map;
import java.util.HashMap;

public enum Attributes {
	NONE(0, "NONE")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case TROLL:
			case ORK:
			case DWARF:
			case ELF:
			case HUMAN:
				return 0;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
		}}},
	BODY(1, "BOD")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case TROLL:
				return 10;
			case ORK:
				return 9;
			case DWARF:
				return 8;
			case ELF:
			case HUMAN:
				return 6;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
		}}},
	AGILITY(2, "AGI")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case ELF:
				return 7;
			case ORK:
			case DWARF:
			case HUMAN:
				return 6;
			case TROLL:
				return 5;
			default:
				  throw new IllegalArgumentException("Reached default - missing MetaType definition!");
		}}},
	REACTION(3, "REA")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case ELF:
			case ORK:
			case HUMAN:
			case TROLL:
				return 6;	
			case DWARF:
				return 5;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
		}}}, 
	STRENGTH(4, "STR")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case TROLL:
				return 10;
			case DWARF:
			case ORK:
				return 8;
			case ELF:
			case HUMAN:
				return 6;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
		}}}, 		
	WILLPOWER(5, "WIL")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case DWARF:
				return 7;
			case TROLL:
			case ORK:
			case ELF:
			case HUMAN:
				return 6;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
		}}},
	LOGIC(6, "LOG")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case DWARF:
			case ELF:
			case HUMAN:
				return 6;
			case TROLL:
			case ORK:
				return 5;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
			}}},
	INTUITION(7, "INT")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case DWARF:
			case ELF:
			case HUMAN:
			case ORK:
				return 6;
			case TROLL:
				return 5;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
			}}},
	CHARISMA(8, "CHA")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case DWARF:
			case ELF:
			case HUMAN:
			case ORK:
				return 6;
			case TROLL:
				return 5;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
			}}},
	EDGE(9, "EDG")	{
		@Override
		public int getAttrLimit(MetaType metaType)	{
			switch (metaType)	{
			case DWARF:
			case ELF:
			case ORK:
			case TROLL:
				return 6;
			case HUMAN:
				return 7;
			default:
				throw new IllegalArgumentException("Reached default - missing MetaType definition!");
			}}};
	
	private final int index;
	private final String abbrv;
	private static final Map<Integer,Attributes> map;
	
	static	{
		map = new HashMap<Integer,Attributes>();
		for (Attributes attrNames : Attributes.values())	{
			map.put(attrNames.index, attrNames);
		}
	}
	
	public abstract int getAttrLimit(MetaType metaType);
	
	Attributes(int index, String abbrv)	{
		this.index = index;
		this.abbrv = abbrv;
	}
	
	public static Attributes findByIndex(int index)	{
		return map.get(index);
	}
	
	public int getIndex()	{
		return index;
	}
	
	public String getAbbrv()	{
		return abbrv;
	}
}

