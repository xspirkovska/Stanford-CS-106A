/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int sentinal = 0;
	
	public void run() {
		findRange();
	}
	
	private void findRange() {
		println("This program finds the largest and smallest numbers. Enter `0` to exit and find the range.");
		
		int firstNumber = readInt("Enter a number:");
		if (firstNumber == sentinal) { 
			println("this is not a valid first value. Please enter a value > 0");
		} 
		
		int smallest = firstNumber; 
		int largest = firstNumber; 
		
		while(firstNumber != sentinal) { 
			int newNumber = readInt ("Enter another number:"); 
			
			if (newNumber != sentinal) {
				if (newNumber < smallest) { 
					smallest = newNumber;
				}
				
				if (newNumber > largest) {
					largest = newNumber;
				}
			}
			
			if(newNumber == sentinal) { 
				println ("The smallest number you enetered is: " + smallest); 
				println ("The largest number you entered is: " + largest); 
				firstNumber = sentinal; 
			}
		}
	}
}