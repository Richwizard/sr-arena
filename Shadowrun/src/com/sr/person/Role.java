package com.sr.person;

import com.sr.attribute.Attributes;

public enum Role	{	
	SPELLCASTER("Spellcaster")	{
		public boolean[] getPrmryAttr()	{
			boolean[] prmryAttr = new boolean[Attributes.values().length];
			prmryAttr[Attributes.CHARISMA.getIndex()] = true;
			prmryAttr[Attributes.INTUITION.getIndex()] = true;
			prmryAttr[Attributes.WILLPOWER.getIndex()] = true;
			return prmryAttr;
		}
	},	
	RIGGER("Rigger")	{
		public boolean[] getPrmryAttr()	{
			boolean[] prmryAttr = new boolean[Attributes.values().length];
			prmryAttr[Attributes.REACTION.getIndex()] = true;
			return prmryAttr;
		}
	}, 
	STREET_SAMURAI("Street Samurai")	{
		public boolean[] getPrmryAttr()	{
			boolean[] prmryAttr = new boolean[Attributes.values().length];
			prmryAttr[Attributes.BODY.getIndex()] = true;
			prmryAttr[Attributes.STRENGTH.getIndex()] = true;
			prmryAttr[Attributes.WILLPOWER.getIndex()] = true;
			return prmryAttr;
		}
	};
	/*
	FACE("Face")	{
		public boolean[] getPrmryAttr()	{
			boolean[] prmryAttr = new boolean[Attributes.values().length];
			prmryAttr[Attributes.CHARISMA.getIndex()] = true;
			prmryAttr[Attributes.WILLPOWER.getIndex()] = true;
			return prmryAttr;
		}
	}, 
	DECKER("Decker")	{
		public boolean[] getPrmryAttr()	{
			boolean[] prmryAttr = new boolean[Attributes.values().length];
			prmryAttr[Attributes.LOGIC.getIndex()] = true;
			prmryAttr[Attributes.INTUITION.getIndex()] = true;
			prmryAttr[Attributes.WILLPOWER.getIndex()] = true;
			return prmryAttr;
		}
	},
	TECHNOMANCER("Technomancer")	{
		public boolean[] getPrmryAttr()	{
			boolean[] prmryAttr = new boolean[Attributes.values().length];
			prmryAttr[Attributes.LOGIC.getIndex()] = true;
			prmryAttr[Attributes.INTUITION.getIndex()] = true;
			prmryAttr[Attributes.WILLPOWER.getIndex()] = true;
			return prmryAttr;
		}
	},
	*/
	
	private final String role;
	
	private Role(String role)	{
		this.role = role;
	}
	
	public String getRoleName()	{
		return role;
	}
	
	public abstract boolean[] getPrmryAttr();
}