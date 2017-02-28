package com.sr.person;

import java.util.ArrayList;

import com.sr.gear.CyberwareTypes;
import com.sr.gear.CyberwareGear;

public class SpellCaster extends Runner	{
	
	public SpellCaster(MetaType metaType)	{
		super(metaType, Role.SPELLCASTER, getCyber());
	}
	
	public SpellCaster(String name, MetaType metaType)	{
		this(metaType);
		super.setName(name);
	}
	
	private static ArrayList<CyberwareGear> getCyber()	{
		ArrayList<CyberwareGear> cyberware = new ArrayList<CyberwareGear>();
		cyberware.add(new CyberwareGear(CyberwareTypes.NONE, 0, 0));
		return cyberware;
	}
}
