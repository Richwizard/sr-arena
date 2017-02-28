package com.sr.utils;

import java.util.Comparator;

import com.sr.person.Person;
import com.sr.attribute.Attribute;
import com.sr.combatant.Combatant;
import com.sr.combatant.CombatantType;
import com.sr.combatant.StatusEffects;

public class CmbtntExhstdIniComparator implements Comparator<Combatant>	{
	@Override
	public int compare(Combatant c1, Combatant c2)	{
		StatusEffects c1Stat = c1.getStat();
		StatusEffects c2Stat = c2.getStat();
		
		if (c2Stat.isExhstd() == true && c1Stat.isExhstd() == false)	{
			return 1;
		} else if (c2Stat.isExhstd() == false && c1Stat.isExhstd() == true)	{
			return -1;
		} else if (c2.getInitiative().getIni() != c1.getInitiative().getIni())	{
			return c2.getInitiative().getIni() - c1.getInitiative().getIni();
		}
		
		/*else if (c2.getCmbtntType() == CombatantType.PERSON && c1.getCmbtntType() == CombatantType.PERSON)	{
			Person p1 = (Person) c1;
			Person p2 = (Person) c2;
			Attribute p1Attr = p1.getAttr();
			Attribute p2Attr = p2.getAttr();
			if (p2Attr.getRea() != p1Attr.getRea())	{
				return p2Attr.getRea() - p1Attr.getRea();
			} else if (p2Attr.getInt() != p1Attr.getInt())	{
				return p2Attr.getInt() - p1Attr.getInt();
			} else	if (p2Attr.getEdg() != p1Attr.getEdg())	{
				return p2Attr.getEdg() - p1Attr.getEdg();
			}
		}
//			else if (c2.getInitiative().getIni() > 0 && c1.getInitiative().getIni() > 0)	{
//				Message.iniEven(c1, c2);
//				System.out.println("DEBUG: " + c2.getIni() + " " + c1.getIni());
//			}
		*/
		return 0;
	}
}
