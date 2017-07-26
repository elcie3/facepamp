package ph.edu.dlsu.rivera.facepamphlet;

/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.io.IOConsole;
import acm.util.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import ph.edu.dlsu.rivera.artistry.Artistry;
import static ph.edu.dlsu.rivera.artistry.Artistry.APPLICATION_HEIGHT;
import static ph.edu.dlsu.rivera.artistry.Artistry.APPLICATION_WIDTH;
import ph.edu.dlsu.rivera.hangman.Hangman;
import sun.awt.im.InputMethodJFrame;

public class FacePamphlet extends Program implements FacePamphletConstants {
boolean visible=true;
    JFrame networkframe;
    ArrayList<String> names = new ArrayList<>();
    FacePamphletCanvas canvas;
    FacePamphletProfile profileCurrent;
    private JLabel name = new JLabel("Name ");
    private JTextField txtFieldChange_status = new JTextField(3), txtFieldChange_picture = new JTextField(3), txtFieldAdd_friend = new JTextField(3), txtFieldName = new JTextField(20), txtFieldRemoveFriend = new JTextField(3);
    private JButton bShowNetwork = new JButton("Show/Hide Network"), bAdd = new JButton("add"), bDelete = new JButton("delete"), bSearch = new JButton("search"), bChange_status = new JButton("change status"), bChange_picture = new JButton("change picture"), bAdd_friend = new JButton("add friend"), bRemove_friend = new JButton("unfriend");
    private FacePamphletDatabase database;
    FacePamphletNeworkCanvas networkCanvas;
    FacePamphletNode temp_node;
    GPoint newLoc = new GPoint();

    /**
     * This method has the responsibility for initializing the interactors in
     * the application, and taking care of any other initialization that needs
     * to be performed.
     */
    public void init() {

        try {
            // You fill this in
            database = new FacePamphletDatabase();

        } catch (IOException ex) {
            Logger.getLogger(FacePamphlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        initDisplay();
        initNework();
        networkCanvas.displayNodes();
        networkCanvas.displayConnections();
    }

    public void run() {
        while (true) {

        }
    }

    /**
     * This class is responsible for detecting when the buttons are clicked or
     * interactors are used, so you will have to add code to respond to these
     * actions.
     */
    public void actionPerformed(ActionEvent e) {
        // You fill this in as well as add any additional methods

        FacePamphletProfile temp_profile = null;
        temp_profile = database.getProfile(txtFieldName.getText());
        String action = e.getActionCommand();
        if (action.equals("add")) {
            if (!txtFieldName.getText().equals("")) {
                if (temp_profile != null) {
                    System.out.println("user already exist");
                    canvas.showMessage("Profile already exists. Displaying the Profile");
                    canvas.displayProfile(temp_profile);
                } else {
                    String gender;
                    JFrame genderFrame = new JFrame("Gender");;

                    gender = JOptionPane.showInputDialog(genderFrame, "Input gender.");
                    temp_profile = new FacePamphletProfile(txtFieldName.getText());
                    System.out.println("Add: " + txtFieldName.getText());
                    temp_profile.setGender(gender);
                    database.addProfile(temp_profile);

                    canvas.showMessage("New Profile Created");
                    this.canvas.displayProfile(temp_profile);
                    temp_profile.profile_node = networkCanvas.createNode(temp_profile.getName());
                }
            } else if (temp_profile != null) {
                System.out.println("profile duplicate");
            }
        } else if (action.equals("delete")) {
            if (!txtFieldName.getText().equals("")) {
                System.out.println("Delete: " + txtFieldName.getText());
                database.deleteProfile(txtFieldName.getText());
                canvas.initDisplay();
                FacePamphletProfile temp_profile2 = null;
                ArrayList<String> friends = new ArrayList<>();
                friends = temp_profile.getFriends();
                for (int i = 0; i < friends.size(); i++) {
                    temp_profile2 = database.getProfile(friends.get(i));
                    temp_profile2.removeFriend(temp_profile.getName());
                    temp_profile.profile_node.disconnect(temp_profile2.profile_node);
                    temp_profile2.profile_node.disconnect(temp_profile.profile_node);
                }

                networkCanvas.nodes.remove(temp_profile.profile_node);

            }
        } else if (action.equals("search")) {
            if (!txtFieldName.getText().equals("")) {
                System.out.println("Search: " + txtFieldName.getText());
                if (temp_profile != null) {
                    temp_profile = database.getProfile(txtFieldName.getText());
                    canvas.message.setVisible(true);
                    canvas.showMessage("Profile Loaded");
                    canvas.displayProfile(temp_profile);
                } else {
                    canvas.message.setVisible(true);
                    canvas.showMessage("Profile not found! Please check the name carefully");

                }
            }
        } else if (action.equals("change picture")) {
            if (!txtFieldChange_picture.getText().equals("")) {
                System.out.println("Change Picture: " + txtFieldChange_picture.getText());
//                    dp.setImage(System.getProperty("user.dir")+ "\\src\\facepamphlet\\images\\"+txtFieldChange_picture.getText());
                temp_profile.setImage(txtFieldChange_picture.getText());
                this.canvas.displayProfile(temp_profile);
            }
        } else if (action.equals("change status")) {
            if (!txtFieldChange_status.getText().equals("")) {
                System.out.println("Change Status: " + txtFieldChange_status.getText());

                temp_profile.setStatus(txtFieldChange_status.getText());
                this.canvas.displayProfile(temp_profile);
            }
        } else if (action.equals("add friend")) {
            if (!txtFieldAdd_friend.getText().equals("")) {
                System.out.println("Add Friend: " + txtFieldAdd_friend.getText());
                FacePamphletProfile temp_profile2 = null;
                temp_profile2 = database.getProfile(txtFieldAdd_friend.getText());
                if (temp_profile2 != null) {

                    String weight;
                    JFrame weightFrame = new JFrame("Weight");
                     weight = JOptionPane.showInputDialog(weightFrame, "Input the degree of friendship with the person." + System.lineSeparator() + "1. Acquaintance" + System.lineSeparator() + "2. Friend" + System.lineSeparator() + "3. Close Friend" + System.lineSeparator() + "4. Best Friends" + System.lineSeparator() + "5. Can't live without.");
                   
                    temp_profile.addFriend(txtFieldAdd_friend.getText(),Integer.parseInt(weight));
                    temp_profile2.addFriend(temp_profile.getName(),Integer.parseInt(weight));
                    temp_profile.profile_node.connect(temp_profile2.profile_node, Integer.parseInt(weight));
                    temp_profile2.profile_node.connect(temp_profile.profile_node, Integer.parseInt(weight));
                    networkCanvas.createEdge(temp_profile.profile_node, temp_profile2.profile_node, Integer.parseInt(weight));
                    networkCanvas.displayConnections();
                } else {
                    canvas.showMessage("User does not exist! Please check the name!");
                }
                this.canvas.displayProfile(temp_profile);

            }
        } else if (action.equals("remove")) {
            if (!txtFieldRemoveFriend.getText().equals("")) {
                System.out.println("Remove Friend: " + txtFieldRemoveFriend.getText());
                FacePamphletProfile temp_profile2 = null;
                temp_profile2 = database.getProfile(txtFieldRemoveFriend.getText());
                if (temp_profile2 != null && temp_profile.isFriend(temp_profile2)) {
                    temp_profile.removeFriend(txtFieldRemoveFriend.getText());
                    temp_profile2.removeFriend(temp_profile.getName());
                    temp_profile.profile_node.disconnect(temp_profile2.profile_node);
                    temp_profile2.profile_node.disconnect(temp_profile.profile_node);
                    networkCanvas.removeConnection(temp_profile.profile_node, temp_profile2.profile_node);
                } else if (!temp_profile.isFriend(temp_profile2)) {
                    canvas.showMessage("The users are not friends.");
                } else {
                    canvas.showMessage("User does not exist! Please check the name!");
                }
                this.canvas.displayProfile(temp_profile);

            }
        } else if (action.equals("network")) {
            if (networkframe.isVisible()) {
                networkframe.setVisible(false);
            } else {
                networkframe.setVisible(true);

            }

        }else if (action.equals("Hangman")) {
            JFrame hangmanFrame = new JFrame("Hangman");
            hangmanFrame.setVisible(true);
            hangmanFrame.setSize(700,500);
            
            System.out.println("Launched Hangman");
            
            Hangman hangmanObject = new Hangman();
            hangmanObject.run();
            IOConsole console = hangmanObject.getConsole();

            
            hangmanFrame.add(hangmanObject.getContentPane());
            
            hangmanFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }else if (action.equals("Artistry")) {
            Artistry art = new Artistry();
           art.run();
            
            
           System.out.println("Launched artistry");
           
           
           canvas.add(art.face);
           canvas.add(art.mouth);
           canvas.add(art.eye1);
           canvas.add(art.eye2);
           art.eye1.sendToBack();
           art.eye2.sendToBack();
           art.mouth.sendToBack();
           art.face.sendToBack();
           
           
        }
        updateNetwork();
        try {
            saveProfiles();
        } catch (IOException ex) {
            Logger.getLogger(FacePamphlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initDisplay() {

        this.canvas = new FacePamphletCanvas(this);
        add(this.canvas);
        txtFieldChange_status.setSize(100, 30);
        txtFieldChange_picture.setSize(100, 30);
        txtFieldName.setSize(this.getWidth() / 4, 30);

        bShowNetwork.addActionListener(this);
        bAdd_friend.addActionListener(this);
        bChange_picture.addActionListener(this);
        bChange_status.addActionListener(this);
        bDelete.addActionListener(this);
        bAdd.addActionListener(this);
        bSearch.addActionListener(this);
        bRemove_friend.addActionListener(this);
        addKeyListener(this);

        bAdd.setActionCommand("add");
        bAdd_friend.setActionCommand("add friend");
        bChange_picture.setActionCommand("change picture");
        bChange_status.setActionCommand("change status");
        bDelete.setActionCommand("delete");
        bSearch.setActionCommand("search");
        bShowNetwork.setActionCommand("network");
        bRemove_friend.setActionCommand("remove");

        add(name, NORTH);
        add(txtFieldName, NORTH);
        add(bAdd, NORTH);
        add(bDelete, NORTH);
        add(bSearch, NORTH);
        add(txtFieldChange_status, WEST);
        add(bChange_status, WEST);
        add(txtFieldChange_picture, WEST);
        add(bChange_picture, WEST);
        add(txtFieldAdd_friend, WEST);
        add(bAdd_friend, WEST);
        add(txtFieldRemoveFriend, WEST);
        add(bRemove_friend, WEST);
        add(bShowNetwork, WEST);
        add(new JButton("Artistry"),EAST);
         add(new JButton("Hangman"),EAST);
         add(new JButton("Calculator"),EAST);
         add(new JButton("Breakout"),EAST);
           addActionListeners();
        //hangman
        //network visualization
        System.out.println("network");
        networkframe = new JFrame("network graph");
        networkframe.setVisible(false);
        networkframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        networkframe.setSize(600, 700);

        networkCanvas = new FacePamphletNeworkCanvas(networkframe);
        networkframe.add(networkCanvas);
        this.setLocation(0, 0);
        networkframe.setLocation(this.getX() + this.getWidth() + TEXT_FIELD_SIZE, this.getY());

        networkCanvas.addMouseListener(this);
        networkCanvas.addMouseMotionListener(new MouseAdapter() {

            public void mouseDragged(MouseEvent e) {
                System.out.println("mouse dragged");
                newLoc.setLocation(e.getX(), e.getY());
                temp_node.setLocation(newLoc);
                System.out.println(temp_node.label);
                temp_node.update();
                networkCanvas.displayNodes();
                networkCanvas.displayConnections();
            }
        });
        //adding close option
        networkframe.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(networkframe,
                        "Are you sure you want to exit the application? To hide this window click on the \"show/hide network graph\" button", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x, y;
        x = e.getX();
        y = e.getY();
        System.out.println("x = " + e.getX() + " y = " + e.getY());
        GObject temp_object = null;
        try {
            temp_object = networkCanvas.getElementAt(x, y);

        } catch (Exception d) {
        }

        temp_node = networkCanvas.getNode(temp_object);
        if (temp_node != null) {
            canvas.displayProfile(database.getProfile(temp_node.getName()));
            txtFieldName.setText(temp_node.getName());
            canvas.showMessage("Profile loaded!");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("mouse dragged");
        newLoc.setLocation(e.getX(), e.getY());
        temp_node.setLocation(newLoc);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            println("add");
        }
    }

    public void updateNetwork() {
        networkCanvas.displayNodes();
        networkCanvas.displayConnections();
    }

    private void saveProfiles() throws IOException {
        database.saveProfiles();
    }

    private void initNework() {
        FacePamphletProfile temp_profile;
        ArrayList<FacePamphletProfile> profiles = database.profiles;
        for (int i = 0; i < profiles.size(); i++) {
            profiles.get(i).profile_node = networkCanvas.createNode(profiles.get(i).getName());
            System.out.println("node " + profiles.get(i).getName() + " created");

        }
        System.out.println("all nodes created");

        for (int i = 0; i < profiles.size(); i++) {
            ArrayList<String> friends = profiles.get(i).getFriends();
            for (int j = 0; j < friends.size(); j++) {
                temp_profile = database.getProfile(friends.get(j));
                temp_profile.profile_node.connect(profiles.get(i).profile_node);
                int weight;

                weight = profiles.get(i).weights.get(j);
                System.out.println("Edge weight = " + weight);
                networkCanvas.createEdge(temp_profile.profile_node, profiles.get(i).profile_node, weight);
            }
            System.out.println("exited inner for loop");
        }

        System.out.println("all nodes connected");
        networkCanvas.displayConnections();
    }

    public static void main(String[] args) {
        FacePamphlet app = new FacePamphlet();

        app.start(args);
    }
}
