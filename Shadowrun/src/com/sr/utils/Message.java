package com.sr.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.*;
import com.sr.gear.Weapon;
import com.sr.person.Person;
import com.sr.person.Faction;
import com.sr.combat.Attack;
import com.sr.combatant.DiceRoller;
import com.sr.combatant.Combatant;
import com.sr.combatant.CombatantType;
import com.sr.person.Runner;
import com.sr.summons.Summon;
import com.sr.combat.DmgType;

public class Message {
	// Messages for com.sr.domain.StatusEffects
	public static void unconsciousStun(String cdm)	{
		System.out.println("STATUS UNCONSCIOUS: Stun damage equals stun damage track. " + cdm);
	}
	
	public static void unconsciousStunExcess(String cdm)	{
		System.out.println("STATUS UNCONSCIOUS: Damage exceeds stun damage track, converting to physical at 2:1. " + cdm);
	}
	
	public static void unconsciousPhysical(String cdm)	{
		System.out.println("STATUS UNCONSCIOUS: Physical damage equals physical damage track. " + cdm);
	}
	
	public static void dying(String cdm)	{
		System.out.println("STATUS DYING: Total physical damage exceeds physical damage track, bleeding out. " + cdm);
	}
	
	public static void dead(String cdm)	{
		System.out.println("STATUS DEAD: Total physical damage exceeds death threshhold. " + cdm);
	}
	
	public static void dthTmr(Person pers)	{
		System.out.printf("DEATH TIMER: %s has been bleeding out for 20 turns. Adding 1 physical damage!%n", pers.getName());
	}
	
	public static void encmbrnce(Runner runner, int mod)	{
		System.out.printf("ENCUMBRANCE: %s's cumulative armor [+%d] exceeds STR %d. Reducing AGI and REA by %s.%n", runner.getName(), runner.getArmor().getCmltveArmRating(), runner.getAttr().getStr(), mod);
	}
	
	// Messages for com.sr.domain.Attack
	public static void newMeleeAtk(Combatant atcker, Combatant opnt)	{
		System.out.printf("---===NEW MELEE ATTACK===---%n");
		System.out.printf("%s strikes with %s at %s armored with %s.%n", atcker.toString(), atcker.getWpn().getName(), opnt.getName(), opnt.getArmor().toString());
	}
	
	public static void newRngdAtk(Combatant atcker, Combatant opnt)	{
		System.out.printf("---===NEW RANGED ATTACK===---%n");
		System.out.printf("%s shoots with %s at %s armored with %s.%n", atcker.toString(), atcker.getWpn().getName(), opnt.getName(), opnt.getArmor().toString());
	}
	
	public static void newAtk(Attack atk, int hitsAtckr, int atckrDicePool, String atckrDesc, String rolls)	{
		Combatant atckr = atk.getAtckr();
		Combatant opnt = atk.getOpnt();
		int limit = atckr.getWpn().getAcc();
		String newAtk = String.format("%s has %d hits with dice pool of %d %s %s%n", atckr.getName(), hitsAtckr, atckrDicePool, atckrDesc, rolls.toString());
		if (atckr.getStat().hasRanged())	{
			newRngdAtk(atckr, opnt);
			System.out.printf("RANGED ATTACK: %s", newAtk);
			if(hitsAtckr > limit)	{
				Message.limit(atckr, hitsAtckr);
			}
		} else	{
			newMeleeAtk(atckr, opnt);
			System.out.printf("MELEE ATTACK: %s", newAtk);
			if(hitsAtckr > limit)	{
				Message.limit(atckr, hitsAtckr);
			}
		}
	}
	
	public static void newDef(Attack atk, int opntHits, int opntDicePool, String opntDesc, String rolls)	{
		Combatant atckr = atk.getAtckr();
		Combatant opnt = atk.getOpnt();
		if (atk.getNetReach() != 0 && atckr.getStat().hasMelee())	{
			melAtkReach(atckr, opnt, atk.getNetReach());
			System.out.printf("DEFENSE: %s has %d hits with dice pool of %d (%s, Wounded %+d, Reach %+d) %s%n", opnt.getName(), opntHits, opntDicePool, opntDesc, opnt.getStat().getWoundMod(), atk.getNetReach(), rolls.toString());
		} else	{
			System.out.printf("DEFENSE: %s has %d hits with dice pool of %d (%s, %+d Wounded) %s%n", opnt.getName(), opntHits, opntDicePool, opntDesc, opnt.getStat().getWoundMod(), rolls.toString());
		}
	}
	
	public static void atkHit(Attack atk)	{
		Combatant atckr = atk.getAtckr();
		Combatant opnt = atk.getOpnt();
		Weapon wpn = atckr.getWpn();
		
		if (atckr.getStat().hasRanged())	{
			System.out.printf("HIT with %s: %s's bullets stay true and hit %s with a satisfying thud.%n", wpn.toStringShort(), atckr.getName(), opnt.getName());
		} else if (atckr.getStat().hasMelee())	{
			System.out.printf("HIT with %s: %s delivers a smashing blow.%n", wpn.toStringShort(), atckr.getName());
		}
	}
	
	public static void atkGraze(Attack atk)	{
		Combatant atckr = atk.getAtckr();
		Combatant opnt = atk.getOpnt();
		if (atckr.getStat().hasRanged())	{
			System.out.printf("GRAZING HIT: %s's rounds barely connect and don't seem to do any damage. Tough luck!%n", atckr.getName());
		} else if (atckr.getStat().hasMelee())	{
			System.out.printf("GRAZING HIT: %s and %s seem equally matched, locked in their struggle.%n", atckr.getName(), opnt.getName());
		}
	}
	
	public static void atkMiss(Attack atk)	{
		Combatant atckr = atk.getAtckr();
		Combatant opnt = atk.getOpnt();
		if (atckr.getStat().hasRanged())	{
			System.out.printf("MISS: %s hits nothing but stale air. %s sure is having a good laugh!%n", atckr.getName(), opnt.getName());
		} else if (atckr.getStat().hasMelee())	{
			System.out.printf("MISS: %s fumbles his attack and will soon find himself victim.%n", atckr.getName());
		}
	}
	
	public static void woundMod(Combatant cmbtnt)	{
		System.out.printf("WOUNDED: %s is wounded (%s).%n", cmbtnt.getName(), cmbtnt.getStat().getWoundMod());
	}
	
	public static void melAtkReach(Combatant atckr, Combatant opnt, int netReach)	{
		System.out.printf("REACH: Defender %s has net reach of %s.%n", opnt.getName(), netReach);
	}

	public static void calcDmg(Attack atk, int dmgRes, int rawDmg, int dmgResTst, int netDmg) 	{
		Combatant atckr = atk.getAtckr();
		Combatant opnt = atk.getOpnt();
		ArrayList<Integer> rolls = atk.getRolls();
		DmgType dmgType = atk.getDmgType();
		char marker = (dmgType == DmgType.STUN) ? 'S' : 'P';
		System.out.printf("DAMAGE CALCULATION: %s resists with %dxD6 (BOD %d, AR %d, AP-%d, Wounded %+d) against %d%s (DMG %d%s, HITS %d).%n", opnt.getName(), dmgRes, opnt.getBod(), opnt.getArmor().getArmRating(), -atckr.getWpn().getAP(), opnt.getStat().getWoundMod(), rawDmg, marker, atckr.getWpn().getDmg(), marker, atk.getNetHits());
		if (dmgResTst == 0)	{
			System.out.printf("DAMAGE RESULT: %s rolls %d hits, failing to reduce the damage of %s%s. %s%n", opnt.getName(), dmgResTst, netDmg, marker, rolls.toString());
		} else	{
			System.out.printf("DAMAGE RESULT: %s rolls %d hits to reduce the damage to %d%s. %s%n", opnt.getName(), dmgResTst, netDmg, marker, rolls.toString());
		}
	}
	
	public static void isDmgImmune(Combatant opnt, DmgType dmgType)	{
		System.out.printf("%s is immune to %s. No damage inflicted.%n", opnt, dmgType);
	}
	
	public static void rslveDmgStun()	{
		System.out.println("ARMOR: Modified dmg value of physical attack less than AP-modified armor rating. Physical dmg transformed to stun.");
	}
	
	public static void atkScratch(Combatant opnt)	{
		if (opnt.getCmbtntType() == CombatantType.PERSON)	{
			System.out.printf("JUST A SCRATCH: %s takes the hit like a true troll quarterback. %s%n", opnt.getName(), opnt.getCdm());
		} else if (opnt.getCmbtntType() == CombatantType.DRONE)	{
			System.out.printf("JUST A SCRATCH: %s's chassis has a new dent, but that's it. %s%n", opnt.getName(), opnt.getCdm());
		}
	}
	
	public static void atkDamaged(Combatant opnt)	{
		if (opnt.getCmbtntType() == CombatantType.PERSON)	{
			System.out.printf("ALIVE: %s now has an ugly flesh wound dripping blood. %s%n", opnt.getName(), opnt.getCdm());
		} else if (opnt.getCmbtntType() == CombatantType.DRONE)	{
			System.out.printf("ALIVE: Black smoke has started emanating from %s. %s%n", opnt.getName(), opnt.getCdm());
		}
	}
	
	public static void atkKnockOut(Combatant opnt)	{
		System.out.printf("KNOCK-OUT: %s falls unconscious due to the heavy wounds. Sustaining any more damage will mean bleeding out! %s%n", opnt.getName(), opnt.getCdm());
	}
	
	public static void atkDying(Combatant opnt)	{
		System.out.printf("DYING: %s is dying and bleeding out. %s%n", opnt.getName(), opnt.getCdm());
	}
	
	public static void atkDead(Combatant opnt)	{
		System.out.printf("EXECUTED: %s has bled to death. The Lord giveth and the Lord taketh away. %s%n", opnt.getName(), opnt.getCdm());
	}
	
	public static void atkDestroyed(Combatant opnt)	{
		System.out.printf("DESTROYED: %s explodes in a blaze of glory. %s%n", opnt.getName(), opnt.getCdm());
	}
	
	public static void atkStunDmg(Combatant opnt)	{
		System.out.printf("STUN DMG: %s receives stun damage, feeling increasingly dazed. %s%n", opnt.getName(), opnt.getCdm());
	}
	
	public static void atkUnconscious(Combatant opnt)	{
		System.out.printf("STUNNED: The stun damage knocks %s out. %s%n", opnt.getName(), opnt.getCdm());
	}
	
	// Messages for com.sr.utils.Dice
	public static void rollD6(int dice, ArrayBlockingQueue<Integer> queue, DiceRoller diceRoller)	{
		System.out.printf("%s rolls %dD6: %s%n", diceRoller.getName(), dice, queue);
	}
	
	// Messages for com.sr.utils.Tests
	public static void success(int dice, int hits, ArrayBlockingQueue<Integer> queue)	{
		int ratio = (int) (hits * 100.0) / dice;
		System.out.printf("%d hits with dice pool of %d. %s (%d%%)%n", hits, dice, queue, ratio);
	}
	
	public static void success(int dice, int mod, Person p)	{
		System.out.printf("%s has a dice pool of %s and total modifiers of %s.%n", p.getName(), dice, mod);
	}
	
	public static void successLimit(int dice, int mod, int limit, Person p)	{
		System.out.printf("%s has a dice pool of %s [%s] and total modifiers of %s.%n", p.getName(), dice, limit, mod);
	}
	
	public static void autoFail(Combatant cmbtnt, String test)	{
		System.out.printf("FAILED %s: %s's dice pool is negative, test failed automatically!%n", test, cmbtnt);
	}
	
	public static void opposed(Combatant self, Combatant opnt, int ownHits, int opntHits)	{
		if (ownHits > opntHits)	{
			System.out.printf("%s succeeds with %s net hits against %s. (%s vs %s hits).%n", self.getName(), (ownHits - opntHits), opnt.getName(), ownHits, opntHits);
		} else	{
			System.out.printf("%s fails with %s net hits against %s. (%s vs %s hits).%n", self.getName(), (opntHits - ownHits), opnt.getName(), ownHits, opntHits);
		}
	}
	
	public static void limit (Combatant atckr, int hits)	{
		int limit = atckr.getWpn().getAcc();
		System.out.printf("LIMIT: %s's %d hits reduced to %d due to limit.%n", atckr.getName(), hits, limit);
	}
	
	// Message for com.sr.combat.Arena
	public static void arenaWlcm(ArrayList<Combatant> combtnts)	{
		ArrayList<Integer> fctnsIndices = new ArrayList<>();
		Set<Integer> hs = new HashSet<>();
		for(Combatant combtnt : combtnts)	{
			hs.add(combtnt.getFctn().getIndex());
		}
		fctnsIndices.addAll(hs);	
		System.out.printf("%n---===WELCOME TO THE ARENA===---%n---===%d teams battle to death today===---%n", fctnsIndices.size());
		for(int i = 0; i < fctnsIndices.size(); i++)	{
			StringJoiner sj = new StringJoiner(", ");
			String faction = Faction.findByIndex(fctnsIndices.get(i)).toString();
			for(Combatant combtnt : combtnts)	{
				if(combtnt.getFctn().getIndex() == fctnsIndices.get(i))	{
					sj.add(combtnt.toString());
				}
			}
			String members = sj.toString();
			System.out.printf("Team %s: %s%n", faction, members);
		}
	}	
	
	public static void ini(ArrayList<Combatant> combtnts)	{
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(combtnts.size());
		for (Combatant combtnt : combtnts)	{
			if (combtnt.getStat().isTargetable() == true && combtnt.getInitiative().getIni() > 0)	{
				if(combtnt.getInitiative().getIniDesc().trim().isEmpty())	{
					queue.add(combtnt.getName() + " (INI " +  combtnt.getInitiative().getIni() + ")");
				} else	{
					queue.add(combtnt.getName() + " (INI " +  combtnt.getInitiative().getIni() + ", " + combtnt.getInitiative().getIniDesc() + ")");
				}
			}
		}
		System.out.println("INI SEQUENCE: " + queue);
	}
	
	public static void iniPass(Combatant combtnt, int iniPass)	{
		String iniPassString;
		int woundMod = combtnt.getStat().getWoundMod();
		switch (iniPass)	{
			case 1:
				iniPassString = "st";
				break;
			case 2:
				iniPassString = "nd";
				break;
			case 3:
				iniPassString = "rd";
				break;
			default:
				iniPassString = "th";
		}
		if (woundMod == 0 && combtnt.getInitiative().getIniDesc().trim().isEmpty())	{
			System.out.printf("%n---===INI %d: %d%s ini pass for %s (%s)===---%n", combtnt.getInitiative().getIni(), iniPass, iniPassString, combtnt.getName(), combtnt.getFctn().toString(), combtnt.getInitiative().getIni());
		} else	{
			System.out.printf("%n---===INI %d: %d%s ini pass for %s (%s) (INI %s + MOD %s, %s)===---%n", combtnt.getInitiative().getIni(), iniPass, iniPassString, combtnt.getName(), combtnt.getFctn().toString(), (combtnt.getInitiative().getIni() - woundMod), woundMod, combtnt.getInitiative().getIniDesc());
		}
	}
	
	public static void iniEven(Person p1, Person p2)	{
		System.out.printf("INI of %s and %s is even: Roll, REA, INT and EDG all the same!%n", p1.getName(), p2.getName());
	}
	
	public static void cdmArena(ArrayList<Combatant> combtnts, int cmbtTrn)	{
		System.out.printf("%n---===DAMAGE TOTALS AFTER TURN %s===---%n", cmbtTrn);
		Collections.sort(combtnts, new FctnDthStatNameComparator());
		for (int i = 0; i < combtnts.size(); i++)	{
			Combatant c = combtnts.get(i);
			System.out.printf("%1$-24s\t%2$-20s\tDV %3$02d AP%4$2d   AR %5$02d  %6$s%n", c.getFctn().toString(), c.getName(), c.getWpn().getDmg(), c.getWpn().getAP(), c.getArmor().getArmRating(), c.getCdm());
		}
	}
	
	public static void cmbtWon(Faction fctn, int cmbtTrn)	{
		System.out.printf("COMBAT RESOLVED: No viable targets remaining. Team %s has won the arena in %d turns!%n", fctn.toString(), cmbtTrn);
	}
	
	public static void turnAnnouncer(int cmbtTrn)	{
		System.out.printf("%nSTARTING COMBAT TURN: %d%n", cmbtTrn);
	}
	
	public static void spawnAnnouncer(Summon spawn)	{
		System.out.printf("%s (%s) has joined the arena under the control of %s.%n", spawn, spawn.getFctn(), spawn.getOwner()); 
	}
	
	public static void staleMate(int cmbtTrn)	{
		System.out.printf("STALEMATE: Could not resolve combat in %d turns. Combatants return home bloodied.%n", cmbtTrn);
	}
}