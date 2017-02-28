package com.sr.skill;

import com.sr.person.Role;
import java.util.StringJoiner;

public class Skill {
	int[] indices = Skills.getIndices();
	int[] values = new int[Skills.values().length];


	public Skill(Role role)	{
		for (Skills skill : Skills.values())	{
			setSkillByIndex(skill.getIndex(), skill.getDef(role));
		}
	}
	
	public void setSkillByIndex(int index, int value)	{
		this.values[index] = value;
	}
	
	public int[] getVal()	{
		return values;
	}
	
	public int getVal(int index)	{
		return values[index];
	}
	
	@Override
	public String toString()	{
		return toStringBldr(false);
	}
	
	public String toStringWithGroups()	{
		return toStringBldr(true);
	}
	
	private String toStringBldr(boolean withGroups)	{
		StringJoiner sj = new StringJoiner(", ", "", "%n");

		for (int i = 1; i < values.length; i++)	{
			if (values[i] != 0)	{
				if (Skills.findByIndex(i).getSkillGroup() != SkillGroup.NONE && (withGroups))	{
					sj.add(Skills.findByIndex(i).toString() + " " + values[i] + " (" + Skills.findByIndex(i).getSkillGroup().toString() + ")");
			} else	{
				sj.add(Skills.findByIndex(i).toString() + " " + values[i]);
			}
			}
		}
		return sj.toString();
		
	}
}

/*
	private int none, archery, automatics, blades, clubs, heavyWpns, 
	longarms, pistols, throwingWpns, unarmedCmbt, exoticMelee,
	exoticRngd;
	
	public int getNone() {
		return none;
	}
	
	private void setNone(int none) {
		this.none = none;
		
	}
	
	public int getArchery() {
		return archery;
	}
	
	private void setArchery(int archery) {
		this.archery = archery;
	}
	
	public int getAutomatics() {
		return automatics;
	}
	
	private void setAutomatics(int automatics) {
		this.automatics = automatics;
	}
	
	public int getBlades() {
		return blades;
	}
	
	private void setBlades(int blades) {
		this.blades = blades;
	}
	
	public int getClubs() {
		return clubs;
	}
	
	private void setClubs(int clubs) {
		this.clubs = clubs;
	}
	
	public int getHeavyWpns() {
		return heavyWpns;
	}
	
	private void setHeavyWpns(int heavyWpns) {
		this.heavyWpns = heavyWpns;
	}
	
	public int getLongarms() {
		return longarms;
	}
	
	private void setLongarms(int longarms) {
		this.longarms = longarms;
	}
	
	public int getPistols() {
		return pistols;
	}
	
	private void setPistols(int pistols) {
		this.pistols = pistols;
	}
	
	public int getThrowingWpns() {
		return throwingWpns;
	}
	
	private void setThrowingWpns(int throwingWpns) {
		this.throwingWpns = throwingWpns;
	}
	
	public int getUnarmedCmbt() {
		return unarmedCmbt;
	}
	
	private void setUnarmedCmbt(int unarmedCmbt) {
		this.unarmedCmbt = unarmedCmbt;
	}
	
	public int getExoticMelee() {
		return exoticMelee;
	}
	
	private void setExoticMelee(int exoticMelee) {
		this.exoticMelee = exoticMelee;
	}
	
	public int getExoticRngd() {
		return exoticRngd;
	}
	
	private void setExoticRngd(int exoticRngd) {
		this.exoticRngd = exoticRngd;
	}
}
	*/