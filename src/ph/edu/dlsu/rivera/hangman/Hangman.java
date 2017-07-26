/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.rivera.hangman;

import acm.graphics.GLabel;
import acm.graphics.GObject;

import acm.program.ConsoleProgram;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author student
 */
public class Hangman extends ConsoleProgram {

    private boolean newgame = true;
    Users user = new Users();
    private String currentUser;
    private GLabel try1;
    public HangmanCanvas canvas;

    private HangmanLexicon lexicon;
    boolean notClicked;
    double xClick, yClick;
    private String currentWord;
    private int guessesLeft = 8;
    private String lastGuess;
    char ClastGuess;
    char[] wordNow;
    int userNumberSelected = 1;
    MyLinkedList<Integer> scores = new MyLinkedList<>();

    MyLinkedList<String> scoresString;
    MyLinkedList<String> names;
    private String name;
    private Integer SCORE = 0;
    GLabel score = new GLabel("Score: " + SCORE);
    GLabel pWhenReady = new GLabel("Click when ready,", 50, 250);
    GLabel nextWord = new GLabel("for the next Word", 50, 300);
    GLabel hooray = new GLabel("Hooray, you got it!", 50, 200);

    /**
     * @param args the command line arguments
     */
    public Hangman() {

        try {

            canvas = new HangmanCanvas();

            add(canvas);

            lexicon = new HangmanLexicon();
            drawScore();

        } catch (IOException ex) {
            Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {

        try {
            gameLoop();
        } catch (IOException ex) {
            Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void waitForClick() {
        notClicked = true;
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notClicked = false;
            }
        });
        while (notClicked) {
            pause(1);
        }

    }

    private void playagain() throws IOException {
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Hangman", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            SCORE = 0;
            score.setLabel("Score: " + SCORE);
            canvas.reset();
            guessesLeft = 8;
            canvas.add(canvas.status);
            for (int i = 0; i < 20; i++) {
                println("\n");
            }
            gameLoop();

        } else {
            saveScoretoFile();
            saveScoretoFile2();
            System.exit(0);
        }
    }

    private void saveScore() {
        int i = 1;
        if (scores.size() == 0) {
            scores.add(1, SCORE);
            names.add(1, name);
            scoresString.add(1, SCORE.toString());
        } else {
            while (scores.get(i) > SCORE) {
                i++;
            }
            scoresString.add(i, SCORE.toString());
            scores.add(i, SCORE);
            names.add(i, name);
        }
    }
//    private void selectUser() {
//        
//            canvas.addMouseListener(new MouseAdapter() {
//               public void mouseClicked(MouseEvent e){
//                   xClick=e.getX();
//                   yClick=e.getY();
//                   GObject name = canvas.getElementAt(xClick, yClick);
//                   
//                       try1=(GLabel) name;
//                       currentUser=try1.getLabel();
//                       notClicked=false;    
//               } 
//               
//});
//            while(notClicked)
//            pause(1);
//    }

    private void drawScore() {
        score.setVisible(true);
        score.setFont("Serif-bold-20");
        score.setColor(Color.RED);
        score.sendToFront();
        canvas.add(score, canvas.getWidth(), 30);
        score.setLocation(50, 30);
    }

    private void displayScore(MyLinkedList score, MyLinkedList names) {

        int offset = 30;
        canvas.removeAll();
        canvas.add(canvas.bg);
        GLabel heading = new GLabel("Highscores");
        heading.setFont("Century Schoolbook L-30-italic");
        heading.setColor(Color.RED);

        canvas.add(heading, canvas.getWidth() / 2 - heading.getWidth() / 2, HEIGHT / 2 + 30);

        for (int i = 1; i <= score.size(); i++) {
            GLabel item = new GLabel("" + scoresString.get(i));
            GLabel item2 = new GLabel("" + i + ". " + names.get(i));
            item.setFont("Century Schoolbook L-25");
            item.setColor(Color.RED);
            item2.setFont("Century Schoolbook L-25");
            item2.setColor(Color.BLACK);
            canvas.add(item2, (canvas.getWidth() - item.getWidth() - item2.getWidth() - 30) / 2, canvas.getWidth() / 10 + i * (item.getHeight() + 3) + offset);

            canvas.add(item, item2.getX() + item2.getWidth() + 30, canvas.getWidth() / 10 + i * (item.getHeight() + 3) + offset);

        }
    }

    private void pressWhenReady() {
        canvas.removeAll();
        canvas.add(canvas.bg);
        drawScore();
        pWhenReady.setFont("Serif-bold-36");
        pWhenReady.setColor(Color.RED);
        hooray.setFont("Serif-bold-36");
        hooray.setColor(Color.RED);
        nextWord.setFont("Serif-bold-36");
        nextWord.setColor(Color.RED);
        canvas.add(pWhenReady, canvas.getWidth() / 2 - pWhenReady.getWidth() / 2, canvas.getHeight() / 2 - 50);
        canvas.add(hooray, canvas.getWidth() / 2 - pWhenReady.getWidth() / 2, canvas.getHeight() / 2 - 100);
        canvas.add(nextWord, canvas.getWidth() / 2 - pWhenReady.getWidth() / 2, canvas.getHeight() / 2);
        waitForClick();
        for (int i = 0; i < 20; i++) {
            println("\n");
        }

        canvas.add(canvas.status);
        canvas.remove(pWhenReady);
        canvas.remove(hooray);
        canvas.remove(nextWord);
    }

    private void saveScoretoFile() {
        try {
            File userfile = new File(System.getProperty("user.dir") + "\\users.txt");

            userfile.delete();

            BufferedWriter out = new BufferedWriter(new FileWriter("users.txt"));
            for (int i = 1; i <= names.size(); i++) {

                out.write(names.get(i));
                out.newLine();
            }
            out.close();

        } catch (IOException e) {
            System.out.println("Exception ");
        }
    }

    private void saveScoretoFile2() {
        try {

            File scorefile = new File(System.getProperty("user.dir") + "\\scores.txt");

            scorefile.delete();

            BufferedWriter out2 = new BufferedWriter(new FileWriter("scores.txt"));

            for (int i = 1; i <= names.size(); i++) {
                out2.write(scoresString.get(i));
                out2.newLine();
            }
            out2.close();
        } catch (IOException e) {
            System.out.println("Exception ");
        }
    }

    private void gameLoop() throws IOException {
        canvas.setSize(400, 600);
        if (newgame) {
            ;
            name = "User";
        }

        currentWord = lexicon.getWord();

        canvas.drawNext(0);
        println(currentWord);
        String word = "";
        drawScore();
        for (int i = 0; i < currentWord.length(); i++) {
            word += "-";
        }
        canvas.displayWord(word);
        canvas.status.setSize(300, 400);
        println("Welcome to Hangman, " + name + "!");

        for (int i = 1; i <= guessesLeft; i += 0) {
            println("The current word looks like this " + word);
            println("You have " + guessesLeft + " guesses left");
            println("Your guess: ");
            lastGuess = readLine();
            while (lastGuess.length() > 1) {
                println("You cannot input more than 1 letter. Please input again");
                lastGuess = readLine();
            }
            lastGuess = lastGuess.toUpperCase();
            ClastGuess = lastGuess.charAt(0);

            if (!currentWord.contains(lastGuess)) {
                println("There are no " + lastGuess + "'s in the word.");
                guessesLeft--;
                canvas.noteIncorrectGuess(ClastGuess);
                canvas.status.setSize(300, 400);
            } else {
                println("That guess is correct!");
                for (int k = 0; k < currentWord.length(); k++) {

                    if (ClastGuess == currentWord.charAt(k)) {
                        wordNow = word.toCharArray();
                        wordNow[k] = ClastGuess;
                        word = String.valueOf(wordNow);
                        SCORE += 100;
                        score.setLabel("Score: " + SCORE);
                    }

                }

                canvas.displayWord(word);
                drawScore();

                if (word.equals(currentWord)) {
                    println("You have Guessed the word! hooray!");
                    canvas.displayWord(word);
                    pause(1000);
                    pressWhenReady();
                    canvas.guessList = new MyLinkedList<>();
                    newgame = false;
                    gameLoop();

                }
            }

        }
        if (guessesLeft == 0) {
            println("Game over! You deserve to be hanged!");
            println("The word is " + currentWord);
            waitForClick();
        }

        saveScore();
        displayScore(scores, names);
        waitForClick();
        playagain();
    }
}
