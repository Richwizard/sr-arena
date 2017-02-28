package com.sr.gear;

public class CyberwareGear {
	private CyberwareTypes cyberwareType;
	private int rating, capacity;
	
	public CyberwareGear(CyberwareTypes cyberwareType, int rating, int capacity)	{
		this.cyberwareType = cyberwareType;
		if (rating > cyberwareType.getMaxRating() || rating < 0)	{
			throw new IllegalArgumentException("Cyberware rating exceeds max value or is below zero!");
		}
		this.rating = rating;
		this.capacity = capacity;
	}
	
	public CyberwareTypes getCyberwareType() {
		return cyberwareType;
	}

	public int getRating() {
		return rating;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public String toString()	{
		return String.format("%s (%s)", cyberwareType.getName(), cyberwareType.getDesc(rating));
	}
}
