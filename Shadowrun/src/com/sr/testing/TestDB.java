package com.sr.testing;

import java.util.ArrayList;

import com.sr.data.DBController;


public class TestDB {
	public static void main(String[] args) {	
		
		int namesAmount = 50;
		ArrayList<String> names = new ArrayList<>(namesAmount);
		DBController.dbController(names, namesAmount);
		System.out.print(names.toString());

	}
}
