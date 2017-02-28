package com.sr.testing;

import com.sr.person.MetaType;
import com.sr.person.StreetSamurai;
import com.sr.person.Rigger;
import com.sr.skill.Skills;

public class TestSkills {

	public static void main(String[] args) {
		StreetSamurai joe = new StreetSamurai("Joe", MetaType.HUMAN);
		int i = 4;
		System.out.println(Skills.findByIndex(i));
		System.out.printf(joe.getSkill().toString());
		System.out.printf(joe.getSkill().toStringWithGroups());
		System.out.println(Skills.AUTOMATICS.toString() + ": " + joe.getSkill().getVal(Skills.AUTOMATICS.getIndex()));
		
		Rigger rigi = new Rigger("Rigi", MetaType.DWARF);
		System.out.printf(rigi.getSkill().toString());
	}
}
