package com.sr.utils;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import com.sr.combatant.DiceRoller;

public class Dice {
	
	public static int rollDice(int dice, int nSides, DiceRoller diceRoller)
	{
		int total = 0;
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(dice);
		if(nSides >= 2)	{
			int roll = 0;
			final Random r = new Random();
			for(int i = 0; i < dice; i++)	{
				roll = r.nextInt(nSides) + 1;
				queue.add(roll);
				total = total + roll;
			}
		Message.rollD6(dice, queue, diceRoller);
		} else	{
			System.out.println("Error: dice needs to have more than one side");
		}
		return total;
	}
	
	public static int rollD6(int dice, DiceRoller diceRoller)	{
		int total = rollDice(dice, 6, diceRoller);
		return total;
	}
}
