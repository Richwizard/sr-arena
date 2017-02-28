package com.sr.attribute;

import java.util.StringJoiner;

public class DerivedAttributes {
	private int phyDmgTrk;
	private int stunDmgTrk;
	private int mentLmt, phyLmt, socLmt; 
	
	public DerivedAttributes(int[] attr, double essence)	{
		// In Java, if it is required to round up the result of dividing m by n (where m and n are integers), one should compute (m+n-1)/n
		phyDmgTrk = 8 + (attr[Attributes.BODY.getIndex()] + 3) / 2;
		stunDmgTrk = 8 + (attr[Attributes.WILLPOWER.getIndex()] + 3) / 2;
		mentLmt = (attr[Attributes.LOGIC.getIndex()] * 2 
				+ attr[Attributes.INTUITION.getIndex()] 
				+ attr[Attributes.WILLPOWER.getIndex()] + 2) / 3;
		phyLmt = (attr[Attributes.STRENGTH.getIndex()] * 2 
				+ attr[Attributes.BODY.getIndex()] 
				+ attr[Attributes.REACTION.getIndex()] + 2) / 3;
		socLmt = (attr[Attributes.CHARISMA.getIndex()] * 2 
				+ attr[Attributes.WILLPOWER.getIndex()] 
				+ (int) essence + 2) / 3;
	}
	
	@Override
	public String toString()	{
		StringJoiner sj = new StringJoiner(", ");
		sj.add("Physical Limit: " + phyLmt);
		sj.add("Mental Limit: " + mentLmt);
		sj.add("Social Limit: " + socLmt);
		sj.add("Physical DmgTrk: " + phyDmgTrk);
		sj.add("Stun DmgTrk: " + stunDmgTrk);
		return new String("Derived Attributes: " + sj.toString());
	}
	
	
	public int getMentLmt() {
		return mentLmt;
	}

	public int getPhyLmt() {
		return phyLmt;
	}

	public int getSocLmt() {
		return socLmt;
	}
	
	public int getPhyDmgTrk() {
		return phyDmgTrk;
	}

	public int getStunDmgTrk() {
		return stunDmgTrk;
	}
}