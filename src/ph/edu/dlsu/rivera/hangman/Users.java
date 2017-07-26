/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.rivera.hangman;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Rivera
 */
public class Users extends GCanvas{
    public  MyLinkedList<String> userList = new MyLinkedList<>();
    public  MyLinkedList<String> scores = new MyLinkedList<>();
    
    public MyLinkedList getUsers() throws FileNotFoundException, IOException{
        String filePath = System.getProperty("user.dir")+ "\\users.txt";
        File f = new File(filePath);
        FileReader source = new FileReader(f);
        BufferedReader  br = new BufferedReader(source);
        String temp = br.readLine();
        String temp1 = br.readLine();
        int i=1;
        while (temp!=null){
            scores.add(i, temp);
            userList.add(i, temp1);
            i++;
            
           temp = br.readLine();
        }   
        br.close();
        return userList;
    }
    
   
}
