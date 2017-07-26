/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
            int smallest=0,largest=0,number=-1,starter=0;
            println("This program finds the largest and smallest numbers.");
		while(number!=0){
                    number = readInt();
                    if(starter==0){
                        smallest=number;
                        largest=number;
                        starter=1;
                    }
                    if(smallest>number){
                        smallest=number;
                    }
                    if(largest<number){
                        largest = number;
                    }
                }
                println("smallest: "+ smallest);
                println("largest: "+ largest);
	}
        public static void main(String[] args ){
            new FindRange().start(args);
        }
}

