/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {
public static final int APPLICATION_WIDTH = 640;
    public static final int APPLICATION_HEIGHT = 480;
	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		
                for(int i=14;i>0;i--){
                      for(int j=0;j<i;j++){
                    GRect brick = new GRect(APPLICATION_WIDTH-(13)*BRICK_WIDTH,APPLICATION_HEIGHT-BRICK_HEIGHT,BRICK_WIDTH,BRICK_HEIGHT);
                
                    brick.setBounds(APPLICATION_WIDTH-(13-j)*BRICK_WIDTH+BRICK_WIDTH*(14-i)/2, APPLICATION_HEIGHT-BRICK_HEIGHT*(15-i), BRICK_WIDTH, BRICK_HEIGHT);
                     add(brick);
                      }
                }
                
	}
}

