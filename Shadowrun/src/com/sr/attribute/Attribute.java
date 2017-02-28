package com.sr.attribute;

import java.util.Random;

import com.sr.person.MetaType;
import com.sr.person.Role;
import com.sr.person.Runner;

public class Attribute {
	private int [] attrMod = new int[Attributes.values().length];
	private int none, body, agility, reaction, strength, willpower, logic, intuition, charisma, edge;
	private double essence;
	
	public Attribute(Runner runner)	{
		setRndmAttr(runner.getMetatype());
		setPrmry(runner.getMetatype(), runner.getRole());
		this.essence = 6;
		this.none = 0;
	}
	
	private void setAttr(int [] attr, MetaType metaType)	{
		setBod(attr[Attributes.BODY.getIndex()], metaType);
		setAgi(attr[Attributes.AGILITY.getIndex()], metaType);
		setRea(attr[Attributes.REACTION.getIndex()], metaType);
		setStr(attr[Attributes.STRENGTH.getIndex()], metaType);
		setWil(attr[Attributes.WILLPOWER.getIndex()], metaType);
		setLog(attr[Attributes.LOGIC.getIndex()], metaType);
		setInt(attr[Attributes.INTUITION.getIndex()], metaType);
		setCha(attr[Attributes.CHARISMA.getIndex()], metaType);
		setEdg(attr[Attributes.EDGE.getIndex()], metaType);
	}
	
	private String [] getAbbrv()	{
		String[] abbrv = new String[Attributes.values().length];
		for (Attributes attrNames : Attributes.values())	{
			abbrv[attrNames.getIndex()] = attrNames.getAbbrv();
		}
		return abbrv;
	}
	
	public int getAgi() {
		return getAgiBase() + attrMod[Attributes.AGILITY.getIndex()];
	}
	
	public int getAgiBase()	{
		return agility;
	}
	
	public int [] getAttr()	{
		int[] attrBase = getAttrBase();
		int[] attrMod = getAttrMod();
		for (int i = 0; i < attrBase.length; i++)	{
			attrBase[i] += attrMod[i];
		}
		return attrBase;
	}
	
	public int [] getAttrBase()	{
		int[] attrBase = {none, body, agility, reaction, strength, willpower, logic, intuition, charisma, edge};
		return attrBase;
	}
	
	public int getAttrBaseByIndex(int index)	{
		int[] attr = getAttrBase();
		return attr[index];
	}
	
	public int getAttrByIndex(int index)	{
		int[] attr = getAttr();
		return attr[index];
	}
	
	public int [] getAttrMod()	{
		return attrMod;
	}
	
	public int getAttrModByIndex(int index)	{
		return attrMod[index];
	}
	
	public int getBod() {
		return getBodBase() + attrMod[Attributes.BODY.getIndex()];
	}
	
	public int getBodBase() {
		return body;
	}
	
	public int getCha() {
		return getChaBase() + attrMod[Attributes.CHARISMA.getIndex()];
	}
	
	public int getChaBase()	{
		return charisma;
	}
	
	public int getEdg()	{
		return edge;
	}
	
	public double getEss()	{
		return essence;
	}
	
	public int getInt() {
		return getIntBase() + attrMod[Attributes.INTUITION.getIndex()];
	}
	
	public int getIntBase() {
		return intuition;
	}
	
	public int getLog() {
		return getLogBase() + attrMod[Attributes.LOGIC.getIndex()];
	}
	
	public int getLogBase() {
		return logic;
	}
	
	public int getRea() {
		return getReaBase() + attrMod[Attributes.REACTION.getIndex()];
	}
	
	public int getReaBase() {
		return reaction;
	}
	
	public int getStr() {
		return getStrBase() + attrMod[Attributes.STRENGTH.getIndex()];
	}
	
	public int getStrBase() {
		return strength;
	}
	
	public int getWil() {
		return getWilBase() + attrMod[Attributes.WILLPOWER.getIndex()];
	}
	
	public int getWilBase() {
		return willpower;
	}
	
	private void setAgi(int agility) {
		this.agility = agility;
	}
	
	public void setAgi(int agility, MetaType metaType)	{
		int limit = Attributes.AGILITY.getAttrLimit(metaType);
		if (agility > limit)	{
			throw new IllegalArgumentException(Attributes.AGILITY.getAbbrv() + " cannot be set to " + agility + " exceeds racial limit of " + limit);
		} else	{
			setAgi(agility);
		}
	}
	
	/**
	 * This method must be manually altered when the indices in
	 * the Attributes Enum change. The switch starts at 1 because
	 * at index 0 is Attribute.NONE, which we do not explicitly
	 * reference or set.
	 */
	private void setAttrByIndex(int index, int val)	{
		switch(index)	{
		case 1:
			setBod(val);
			break;
		case 2:
			setAgi(val);
			break;
		case 3:
			setRea(val);
			break;
		case 4:
			setStr(val);
			break;
		case 5:
			setWil(val);
			break;
		case 6:
			setLog(val);
			break;
		case 7:
			setInt(val);
			break;
		case 8:
			setCha(val);
			break;
		case 9:
			setEdg(val);
			break;
		}
	}
	
	public void setAttrMod(int [] mod)	{
		for(int i = 0; i < attrMod.length; i++)	{
			attrMod[i] += mod[i];
		}
	}
	
	public void setAttrModByIndex(int index, int mod)	{
			attrMod[index] += mod;
	}
	
	private void setBod(int body) {
		this.body = body;
	}
	
	public void setBod(int body, MetaType metaType)	{
		int limit = Attributes.BODY.getAttrLimit(metaType);
		if (body > limit)	{
			throw new IllegalArgumentException(Attributes.BODY.getAbbrv() + " cannot be set to " + body + " exceeds racial limit of " + limit);
		} else	{
			setBod(body);
		}
	}
	
	private void setCha(int charisma) {
		this.charisma = charisma;
	}
	
	public void setCha(int charisma, MetaType metaType)	{
		int limit = Attributes.CHARISMA.getAttrLimit(metaType);
		if (charisma > limit)	{
			throw new IllegalArgumentException(Attributes.CHARISMA.getAbbrv() + " cannot be set to " + charisma + " exceeds racial limit of " + limit);
		} else	{
			setCha(charisma);
		}
	}
	
	private void setEdg(int edge)	{
		this.edge = edge;
	}

	public void setEdg(int edge, MetaType metaType)	{
		int limit = Attributes.EDGE.getAttrLimit(metaType);
		if (edge > limit)	{
			throw new IllegalArgumentException(Attributes.EDGE.getAbbrv() + " cannot be set to " + edge + " exceeds racial limit of " + limit);
		} else	{
			setEdg(edge);
		}
	}
	
	public void changeEss(double essenceChange)	{
		if (this.essence + essenceChange <= 0)	{
			throw new IllegalArgumentException("Trying to install too much cyberware. Essence below zero.");
		} else	{
			this.essence += essenceChange;
		}
	}
	
	private void setInt(int intuition) {
		this.intuition = intuition;
	}

	public void setInt(int intuition, MetaType metaType)	{
		int limit = Attributes.INTUITION.getAttrLimit(metaType);
		if (intuition > limit)	{
			throw new IllegalArgumentException(Attributes.INTUITION.getAbbrv() + " cannot be set to " + intuition + " exceeds racial limit of " + limit);
		} else	{
			setInt(intuition);
		}
	}

	private void setLog(int logic) {
		this.logic = logic;
	}
	
	public void setLog(int logic, MetaType metaType)	{
		int limit = Attributes.LOGIC.getAttrLimit(metaType);
		if (logic > limit)	{
			throw new IllegalArgumentException(Attributes.LOGIC.getAbbrv() + " cannot be set to " + logic + " exceeds racial limit of " + limit);
		} else	{
			setLog(logic);
		}
	}

	private void setPrmry(MetaType metaType, Role role)	{
		boolean[] prmryAttr = role.getPrmryAttr();
		for (int i = 0; i < prmryAttr.length; i++ )	{
			if (prmryAttr[i] == true)	{
				vldtdIncrmnt(metaType, i, 2);
			}
		}
	}

	private void setRea(int reaction) {
		this.reaction = reaction;
	}
	
	public void setRea(int reaction, MetaType metaType)	{
		int limit = Attributes.REACTION.getAttrLimit(metaType);
		if (reaction > limit)	{
			throw new IllegalArgumentException(Attributes.REACTION.getAbbrv() + " cannot be set to " + reaction + " exceeds racial limit of " + limit);
		} else	{
			setRea(reaction);
		}
	}

	private void setRndmAttr(MetaType metaType)	{
		int [] attr = new int[Attributes.values().length];
		int i = 0;
		for (Attributes attrNames : Attributes.values())	{
			if (i == Attributes.EDGE.getIndex())	{
					if (metaType == MetaType.HUMAN)	{
					attr[i] = 2;
				} else 	{
					attr[i] = 1;
				}
			} else if (i != Attributes.NONE.getIndex()) {
				attr[i] = new Random().nextInt(attrNames.getAttrLimit(metaType)) + 1;
			}
			i++;
		}
		setAttr(attr, metaType);
	}

	private void setStr(int strength) {
		this.strength = strength;
	}
	
	public void setStr(int strength, MetaType metaType)	{
		int limit = Attributes.STRENGTH.getAttrLimit(metaType);
		if (strength > limit)	{
			throw new IllegalArgumentException(Attributes.STRENGTH.getAbbrv() + " cannot be set to " + strength + " exceeds racial limit of " + limit);
		} else	{
			setStr(strength);
		}
	}

	private void setWil(int willpower) {
		this.willpower = willpower;
	}

	public void setWil(int willpower, MetaType metaType)	{
		int limit = Attributes.WILLPOWER.getAttrLimit(metaType);
		if (willpower > limit)	{
			throw new IllegalArgumentException(Attributes.WILLPOWER.getAbbrv() + " cannot be set to " + willpower + " exceeds racial limit of " + limit);
		} else	{
			setWil(willpower);
		}
	}
	
	/**
	 * Matches the two separate arrays holding the attribute values and abbreviations.
	 * @return The attribute values and their abbreviations.
	 */
	@Override
	public String toString()	{
		int[] attr = getAttr();
		String s = new String("MOD ATTR: ");
		return toStringBldr(attr, s);
	}
	
	public String toStringBase()	{
		int[] attrBase = getAttrBase();
		String s = new String("BASE ATTR: ");
		return toStringBldr(attrBase, s);
	}
	
	private String toStringBldr(int[] attr, String s)	{
		String[] abbrv = getAbbrv();
		StringBuilder tmp = new StringBuilder();
		for (int i = 1; i < attr.length; i++)	{
			if (i != 1)	{
				tmp.append(" ");
			} else	{
				tmp.append(s);
			}
			tmp.append(abbrv[i]);
			tmp.append(" ");
			tmp.append(attr[i]);
		}
		tmp.append(" ESS " + essence);
		return tmp.toString();
	}

	public void vldtdIncrmnt(MetaType metaType, int index, int incr)	{
		int limit = Attributes.findByIndex(index).getAttrLimit(metaType);
		int attr = getAttrByIndex(index);
		if (limit >= (attr + incr))	{
			setAttrByIndex(index, attr + incr);
		} else if (limit > attr)	{
			incr = limit - attr;
			setAttrByIndex(index, (attr + incr));
		}
	}
}