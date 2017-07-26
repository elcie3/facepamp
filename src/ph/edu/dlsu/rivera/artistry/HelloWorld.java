/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmtry;
import java.awt.Color;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
/**
 *
 * @author student
 */
public class HelloWorld extends GraphicsProgram{
    @Override
    public void run(){
        add(new GLabel("Hello World"),100,75);
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        new HelloWorld().start(args);
    }
    
}
