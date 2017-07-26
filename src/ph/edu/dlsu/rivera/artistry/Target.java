/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
    public static final int APPLICATION_WIDTH = 640;
    public static final int APPLICATION_HEIGHT = 480;
	public void run() {
                
		GOval myOval = new GOval(72,72);
                GOval myOval2 = new GOval(46.8,46.8);
                GOval myOval3 = new GOval(21.6,21.6);
                myOval.setFilled(true); 
                myOval.setFillColor(Color.RED); 
                myOval2.setFilled(true); 
                myOval2.setFillColor(Color.WHITE); 
                myOval3.setFilled(true); 
                myOval3.setFillColor(Color.RED);
                add(myOval,APPLICATION_WIDTH/2-myOval.getWidth()/2,APPLICATION_HEIGHT/2-myOval.getWidth()/2);
                add(myOval2,APPLICATION_WIDTH/2-myOval2.getWidth()/2,APPLICATION_HEIGHT/2-myOval2.getWidth()/2);
                add(myOval3,APPLICATION_WIDTH/2-myOval3.getWidth()/2,APPLICATION_HEIGHT/2-myOval3.getWidth()/2);
	}
       
        public static void main(String[] args ){
            new Target().start(args);
        }
}
