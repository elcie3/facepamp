/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
                int number=readInt("Enter a number: ");
                while(number!=1){
                    if(number%2==1){
                        println(number + " is odd, so I make 3n+1:  "+ 3*number+1);
                        number=3*number+1;
                    }
                    /*else if(number==1);{
                    println("the number is already 1");
                }*/
                    else{
                        println(number + " is even, so I take half:  "+ number/2);
                        number/=2;
                    }
                }
                
	}
        public static void main(String[] args ){
            new Hailstone().start(args);
        }
}

