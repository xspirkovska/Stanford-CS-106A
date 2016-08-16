/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		calculatePT();
	}

	private void calculatePT() {
		println("Enter values to compute the Pythagorian theorem:");
		
		int a = readInt ("a:"); 
		int b = readInt ("b:"); 
		
		double c = (double) Math.round(Math.sqrt((a*a) + (b*b))*100)/100;
		
		println("c:"+ c);
	}
}
