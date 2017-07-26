/*
* File: HangmanLexicon.java
* Name: Maverick C. Rivera
* -------------------------
* This file contains a stub implementation of the HangmanLexicon
* class that you will reimplement for Part III of the assignment.
*/

package ph.edu.dlsu.rivera.hangman;

import acm.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class HangmanLexicon {
    private RandomGenerator rgen = RandomGenerator.getInstance();
    MyLinkedList<String> words = new MyLinkedList<>();
    public HangmanLexicon() throws FileNotFoundException, IOException{
       // String filePath = System.getProperty("user.dir")+ "\\src\\hangman\\HangmanLexicon.txt";
        String filePath = System.getProperty("user.dir")+ "\\ShorterLexicon.txt";
        File f = new File(filePath);
        FileReader source = new FileReader(f);
        BufferedReader  br = new BufferedReader(source);
        

        String temp = br.readLine();
        int i=1;
        while (temp!=null){
            words.add(i, temp);
            i++;
            
           temp = br.readLine();
        }   
    }
    

/** Returns the number of words in the lexicon. */
public int getWordCount() {
return 10;
}
/** Returns the word at the specified index. */
public String getWord() {
return words.get(rgen.nextInt(1,words.size()));
}

}