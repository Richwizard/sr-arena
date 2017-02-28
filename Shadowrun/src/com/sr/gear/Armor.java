package com.sr.gear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringJoiner;

import com.sr.person.Runner;
import com.sr.utils.ArmComparator;

public class Armor {
	private ArrayList<Armor> armors; 
	private String name;
	private ArmorType type;
	private int armRating;
	boolean isCumulative;

	{
		armors = new ArrayList<>();
	}

	public Armor(ArmorType type, String name, int armorRating, boolean isCumulative)	{
		this.type = type;
		this.name = name;
		this.armRating = armorRating;
		this.isCumulative = isCumulative;
	}
	
	public Armor()	{
		this.type = ArmorType.UNARMORED;
		this.name = "None";
		this.armRating = 0;
		this.isCumulative = false;
	}
	
	public void addArmor(Runner runner, Armor arm)	{
		if(	(arm.isCumulative && this.name != "None") ||
			(arm.isCumulative && arm.type != ArmorType.CYBERWARE))	{
			armors.add(arm);
		} else 	{
			if(armors.size() != 0)	{
				Collections.sort(armors, new ArmComparator());
				if(armors.get(0).type != ArmorType.CYBERWARE)	{
					armors.remove(0);
				}
			}
			this.type = arm.type;
			this.name = arm.name;
			this.armRating = arm.armRating;
			this.isCumulative = arm.isCumulative;
			armors.add(arm);
		}
		setHasArmorMix(runner);
	}
		
	private Armor mergeArmors()	{		
		StringJoiner sj = new StringJoiner(", ");
		int armRating = 0; 
		for (Armor arm : armors)	{
			sj.add(arm.getNameRating());
			armRating += arm.armRating;
		}
		String mergedName = sj.toString();
		return new Armor(ArmorType.MIXED, mergedName, armRating, false);
	}
	
	private Armor mergeCheck()	{
		if (armors.size() > 1)	{
			return mergeArmors();
		} else
			return this;
	}
	
	public int getCmltveArmRating()	{
		int cmltveArmRating = 0;
		for(Armor arm : armors)	{
			if(arm.isCumulative && arm.type != ArmorType.CYBERWARE)	{
				cmltveArmRating += arm.armRating;
			}
		}
		return cmltveArmRating;
	}
	
	@Override
	public String toString()	{
		if(armors.isEmpty())	{
			return (name + " [" + armRating + "]");
		}
		Collections.sort(armors, new ArmComparator());
		return mergeArmors().name;
	}
	
	private String getNameRating()	{
		if (isCumulative)	{
			char c = '+';
			return  (name + " " + "[" + c + armRating + "]");
		}
		return  (name + " " + "[" + armRating + "]");
	}
	
	public String getName() {
		return mergeCheck().name;
	}

	public ArmorType getType() {
		return mergeCheck().type;
	}

	public int getArmRating() {
		return mergeCheck().armRating;
	}

	public boolean isCumulative() {
		return mergeCheck().isCumulative;
	}
	
	private void setHasArmorMix(Runner runner)	{
		if (getCmltveArmRating() > 0)	{
			runner.getStat().setHasArmorMix(runner, true);
		}
	}
}

/*
If a character is wearing more than one piece of armor
at a time, the value of the highest armor piece applies
for determining Armor. All the other pieces do nothing
but add a lot of bulk; too much can make Joe Shadowrunner
look like the SoyPuff Marshmallow Man, slowing
him down more than the protection is worth.
Armor accessories, items listed with a “+” in front of
their rating, add to the character’s Armor for the purpose
of Damage Resistance tests. The maximum bonus a
character receive from these items is limited to their
Strength attribute. For every 2 full points by which the
bonus exceeds the character’s Strength, the character
suffers a –1 penalty to Agility and Reaction.

Full Deck is headed out on a run and wants to be
able to make a quick change from runner chic to corporate
clean. He throws on some Actioneer Business
Clothes (Armor 8) and then pulls on his Urban Explorer
jumpsuit (Armor 9). For resistance tests he uses only
the Armor 9 from the jumpsuit.
Caster is looking for a little extra protection during
a particularly dangerous raid the team is planning. He
borrows Wombat’s riot shield (Armor +6) to hide behind
as they enter. He’s seen Wombat use it dozens
of times and thinks it shouldn’t be a problem. Caster
has a Strength of 2. Comparing the Armor bonus to his
Strength shows a difference of 4 full points, meaning
Caster receives a –2 to both his Agility and Reaction
while trying to use the riot shield.
When Caster eventually decides he can’t handle
hauling around the big shield, he gives it back to Wombat.
Wombat has a Strength attribute of 5. Comparing
his Strength to the Armor bonus of the shield gives a
difference of only 1. He doesn’t have a problem with it,
but he is also wearing a helmet (Armor +2). The total
Armor bonus needs to be compared to his Strength.
Wombat is now at a +8 Armor bonus with a Strength of
5, so he suffers a –1 penalty to his Agility and Reaction
while using both armor accessories.
*/