/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.rivera.hangman;

/**
 *
 * @author student
 */
/*
* File: HangmanCanvas.java
* ------------------------
* This file keeps track of the Hangman display.
*/
import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.Color;
import java.util.EventListener;

public class HangmanCanvas extends GCanvas{
    GImage bg = new GImage("bg.jpg",WIDTH,0);
    GImage status = new GImage("1.jpg",WIDTH+30,30);
    int incorrectCounter;
    GLabel dispWord = new GLabel("");
    public MyLinkedList<GLabel> guessList = new MyLinkedList<>();
    
         GRect pointer = new G3DRect(40, 10);

    public HangmanCanvas() {
        bg.sendToBack();
        bg.setSize(500 , 600);
        add(bg);
        status.sendToFront();
        status.setSize(400 , 500);
        add(status);
         pointer.setFillColor(Color.RED);
         pointer.setFilled(true);
    
    }
   
        
    
public void drawNext(int order){
//    switch(order){
//        case 0:
//            GLine scaffold = new GLine(50, 50, 50, 50+SCAFFOLD_HEIGHT);
//        add(scaffold);
//    GLine beam = new GLine(50, 50, 50+BEAM_LENGTH, 50);
//        add(beam);
//    GLine rope = new GLine(50+BEAM_LENGTH, 50, 50+BEAM_LENGTH, 50+ROPE_LENGTH);
//        add(rope);
//            break;
//        case 1:
//            GOval head = new GOval(2*HEAD_RADIUS, 2*HEAD_RADIUS);
//            add(head,155,68);break;
//        case 2:
//            GLine body = new GLine(50+BEAM_LENGTH, 50+ROPE_LENGTH+2*HEAD_RADIUS, 50+BEAM_LENGTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
//            add(body);
//            break;
//        case 3:
//            GLine leftUpperArm = new GLine(50+BEAM_LENGTH, ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH-60, 50+BEAM_LENGTH-UPPER_ARM_LENGTH, ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH-60);
//            GLine leftLowerArm = new GLine(50+BEAM_LENGTH-UPPER_ARM_LENGTH, ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH-60, 50+BEAM_LENGTH-UPPER_ARM_LENGTH, ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH-60+LOWER_ARM_LENGTH);
//                    add(leftUpperArm);
//                    add(leftLowerArm);
//                    break;
//        case 4:
//                GLine rightUpperArm = new GLine(50+BEAM_LENGTH, ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH-60, 50+BEAM_LENGTH+UPPER_ARM_LENGTH, ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH-60);
//                GLine rightLowerArm = new GLine(50+BEAM_LENGTH+UPPER_ARM_LENGTH, ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH-60, 50+BEAM_LENGTH+UPPER_ARM_LENGTH, ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH-60+LOWER_ARM_LENGTH);
//                add(rightUpperArm);
//                add(rightLowerArm);
//                break;
//        case 5:
//                GLine leftHip = new GLine(50+BEAM_LENGTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH, 50+BEAM_LENGTH-HIP_WIDTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
//                add(leftHip);
//                break;
//        case 6: GLine leftLeg = new GLine(50+BEAM_LENGTH-HIP_WIDTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH, 50+BEAM_LENGTH-HIP_WIDTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
//                GLine leftFoot = new GLine(50+BEAM_LENGTH-HIP_WIDTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH, 50+BEAM_LENGTH-HIP_WIDTH-FOOT_LENGTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
//                add(leftLeg);
//                add(leftFoot);
//                break;
//        case 7: GLine rightHip = new GLine(50+BEAM_LENGTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH, 50+BEAM_LENGTH+HIP_WIDTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
//                add(rightHip);
//                break;
//        case 8: GLine rightLeg = new GLine(50+BEAM_LENGTH+HIP_WIDTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH, 50+BEAM_LENGTH+HIP_WIDTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
//                GLine rightFoot = new GLine(50+BEAM_LENGTH+HIP_WIDTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH, 50+BEAM_LENGTH+HIP_WIDTH+FOOT_LENGTH, 50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
//                add(rightLeg);
//                add(rightFoot);
//                break;
//    }
    String temp = String.valueOf(order);
    
    status.setImage(temp+".jpg");
    
}    
/** Resets the display so that only the scaffold appears */ 
    
public void reset() {
/* You fill this in */
removeAll();
guessList = new MyLinkedList<>();
    add(bg);
    drawNext(0);
    incorrectCounter=0;
}
/**
* Updates the word on the screen to correspond to the current
* state of the game. The argument string shows what letters have
* been guessed so far; unguessed letters are indicated by hyphens.
*/
public void displayWord(String word) {
   remove(dispWord);
     dispWord = new GLabel(word,this.getWidth()/2-dispWord.getWidth()/2,420);
    
   
    dispWord.setFont("Serif-bold-30");
    dispWord.setColor(Color.RED);
    dispWord.setLabel(word);
    
    
    add(dispWord);
    
        
    
    
/* You fill this in */
}
/**
* Updates the display to correspond to an incorrect guess by the
* user. Calling this method causes the next body part to appear
* on the scaffold and adds the letter to the list of incorrect
* guesses that appears at the bottom of the window.
*/
public void noteIncorrectGuess(char letter) {
   
    GLabel incorrect = new GLabel(String.valueOf(letter));
    incorrect.setFont("Serif-bold-30");
    incorrect.setColor(Color.RED);
    guessList.add(guessList.size()+1, incorrect);
    
    for(int i =1;i<guessList.size()+1;i++){
        add(guessList.get(i),80+i*20,450);
    }
/* You fill this in */
incorrectCounter++;
    drawNext(incorrectCounter);
}
public void displayList(MyLinkedList list) {
                int offset = 10;
                int leftWindowOffset = getWidth()/2;
                GLabel heading = new GLabel("Users");
                heading.setFont("Century Schoolbook L-20-italic");
                heading.setColor(Color.RED);
                
                add(heading, WIDTH/2-heading.getWidth()/2 +leftWindowOffset ,HEIGHT/2+ 30);
                
		for(int i = 1; i <= list.size(); i++){
                   GLabel item = new GLabel(""+ i +". " + list.get(i));
                   //GRect rectbg = new GRect(getWidth(), item.getHeight()*1.2);
                   //rectbg.setFillColor(Color.RED);
                   //rectbg.sendToFront();
                   //rectbg.setColor(Color.yellow);
                   item.setFont("Century Schoolbook L-20");
                   item.setColor(Color.RED);
                   
                   add(item, (getWidth() - item.getWidth())/2, getWidth()/10 + i * (item.getHeight() + 3)+offset); 
                    //add(rectbg, 0, getWidth()/10 + i * (item.getHeight() + 3)+offset-item.getHeight()/2-5); 
                }
                add(pointer,50,getWidth()/10+heading.getHeight());
	}
/* Constants for the simple version of the picture (in pixels) */
private static final int SCAFFOLD_HEIGHT = 360;
private static final int BEAM_LENGTH = 144;
private static final int ROPE_LENGTH = 18;
private static final int HEAD_RADIUS = 36;
private static final int BODY_LENGTH = 144;
private static final int ARM_OFFSET_FROM_HEAD = 28;
private static final int UPPER_ARM_LENGTH = 72;
private static final int LOWER_ARM_LENGTH = 44;
private static final int HIP_WIDTH = 36;
private static final int LEG_LENGTH = 108;
private static final int FOOT_LENGTH = 28;

   
}
