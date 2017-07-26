package ph.edu.dlsu.rivera.artistry;

/*
 * File: Artistry.java
 * Name:
 * Section Leader:
 * ==========================================================
 * Replace these comments with a description of your program.
 * Since this program is more freeform than the rest, tell us
 * a bit about it in these comments!
 */
import acm.graphics.*;
import acm.program.*;
import java.awt.Color;
import javax.swing.JFrame;



public class Artistry extends GraphicsProgram {
    public static final int APPLICATION_WIDTH = 640;
    public static final int APPLICATION_HEIGHT = 480;
    public GOval face;
    public GRect eye1,eye2;
    public GLine mouth;
    public GLabel myLabel;
	public void run() {
		
                 face = new GOval(200,200);
                face.setFilled(true); 
                face.setFillColor(Color.YELLOW); 
                 eye1= new GRect(25,50);
                eye1.setFilled(true);
                eye1.setFillColor(Color.BLACK); 
                  eye2= new GRect(25,50);
                eye2.setFilled(true);
                eye2.setFillColor(Color.BLACK); 
                 mouth = new GLine(APPLICATION_WIDTH/2-50,APPLICATION_HEIGHT/2+50,APPLICATION_WIDTH/2+50,APPLICATION_HEIGHT/2+50);
                mouth.setColor(Color.RED);
                
                 myLabel = new GLabel("Artistry by Maverick C. Rivera");
                myLabel.setFont("Serif-bold-20");
                myLabel.setColor(Color.BLACK);
                eye1.setLocation(APPLICATION_WIDTH/2-50,APPLICATION_HEIGHT/2-50);
                eye2.setLocation(APPLICATION_WIDTH/2+25,APPLICATION_HEIGHT/2-50);
                
                add(myLabel,350,440);
                add(face,APPLICATION_WIDTH/2-face.getWidth()/2,APPLICATION_HEIGHT/2-face.getWidth()/2);
                add(eye1);
                add(eye2);
                add(mouth);
               
	}
}
