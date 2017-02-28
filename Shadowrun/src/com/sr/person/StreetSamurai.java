package com.sr.person;

import com.sr.gear.CyberwareTypes;
import com.sr.gear.Cyberware;
import com.sr.gear.CyberwareGear;
import java.util.ArrayList;

public class StreetSamurai extends Runner {
	
	public StreetSamurai(MetaType metaType)	{
		super(metaType, Role.STREET_SAMURAI, getCyber());	
	}
	
	public StreetSamurai(String name, MetaType metaType)	{
		this(metaType);
		super.setName(name);
	}
	
	private static ArrayList<CyberwareGear> getCyber()	{
		return Cyberware.getRndmCyberware(0.9, CyberwareTypes.BONELACING, 
				CyberwareTypes.MUSCLE_REPLACEMENT, 
				CyberwareTypes.WIRED_REFLEXES, 
				CyberwareTypes.DERMAL_PLATING);
	}	
}