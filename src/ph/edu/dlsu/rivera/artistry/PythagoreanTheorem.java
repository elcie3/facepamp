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
		println("Enter values to compute for the Pythagorean Theorem");
                double a = readDouble("Enter a: ");
                double b = readDouble("Enter b: ");
                double c = Math.sqrt(a*a+b*b);
                println("c = "+ c);
	}
        public static void main(String[] args ){
            new PythagoreanTheorem().start(args);
        }
}
