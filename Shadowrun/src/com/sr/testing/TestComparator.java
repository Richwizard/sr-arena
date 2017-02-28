package com.sr.testing;

import java.util.ArrayList;
import java.util.Collections;

import com.sr.combatant.Combatant;
import com.sr.person.MetaType;
import com.sr.person.Person;
import com.sr.person.Rigger;
import com.sr.person.SpellCaster;
import com.sr.person.StreetSamurai;
import com.sr.utils.StringNumberComparator;
import com.sr.utils.CmbtntExhstdIniComparator;
import com.sr.utils.Message;

public class TestComparator {

	public static void main(String[] args) {	
/*		String s1 = new String("13 Rigi");
		String s2 = new String("5 Sam");
		String s3 = new String("32 Sorc");
		ArrayList<String> test = new ArrayList<>();
		test.add(s1);
		test.add(s2);
		test.add(s3);
		Collections.sort(test, new StringNumberComparator());
		System.out.println(test);
*/		
		ArrayList<Combatant> combtnts = new ArrayList<>();
		StreetSamurai joe = new StreetSamurai("Joe", MetaType.TROLL);
		StreetSamurai honda = new StreetSamurai("Honda", MetaType.ORK);
		SpellCaster jane = new SpellCaster("Jane", MetaType.ELF);
		SpellCaster leon = new SpellCaster("Leon Adler", MetaType.DWARF);
		Rigger rigi = new Rigger("Rigi", MetaType.DWARF);
		Rigger zak = new Rigger("Zak", MetaType.HUMAN);
		joe.getStat().setExhstd(true);
		honda.getStat().setExhstd(true);
		jane.getStat().setExhstd(true);
		rigi.getStat().addPhyDmg(6);
		rigi.getStat().addStunDmg(6);
		combtnts.add(jane);
		combtnts.add(joe);
		combtnts.add(rigi);
		combtnts.add(honda);
		combtnts.add(leon);
		combtnts.add(zak);
		for (Combatant cmbtnt: combtnts)	{
			cmbtnt.getInitiative().rollIni();
			Person pers = (Person) cmbtnt;
			System.out.println(pers.getName() + " " + pers.getAttr().toString() + " INI: " +  cmbtnt.getInitiative().getIni());
		}
		Collections.shuffle(combtnts);
		Collections.sort(combtnts, new CmbtntExhstdIniComparator());
		Message.ini(combtnts);
	}
}
