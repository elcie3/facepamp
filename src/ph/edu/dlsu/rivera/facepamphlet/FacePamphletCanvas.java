package ph.edu.dlsu.rivera.facepamphlet;

/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

    GLabel message = new GLabel("New profile Created"), name = new GLabel("Name"), friends_header = new GLabel("Friends: "), status = new GLabel("No current status"), no_image = new GLabel("No image");
    GCanvas canvas = this;
    GImage display_picture = new GImage("");
    GRect image_border = new GRect(200, 200);
    String profile_name;

    /**
     * Constructor This method takes care of any initialization needed for the
     * display
     */
    public FacePamphletCanvas(FacePamphlet base) {
        // You fill this in
        this.setSize(base.getWidth() - 200, base.getHeight() - 50);
        initDisplay();
    }

    /**
     * This method displays a message string near the bottom of the canvas.
     * Every time this method is called, the previously displayed message (if
     * any) is replaced by the new message text passed in.
     */
    public void showMessage(String msg) {
        // You fill this in
        message.setLabel(msg);
    }

    /**
     * This method displays the given profile on the canvas. The canvas is first
     * cleared of all existing items (including messages displayed near the
     * bottom of the screen) and then the given profile is displayed. The
     * profile display includes the name of the user from the profile, the
     * corresponding image (or an indication that an image does not exist), the
     * status of the user, and a list of the user's friends in the social
     * network.
     */
    public void displayProfile(FacePamphletProfile profile) {
        // You fill this in
        removeAll();
        if (profile == null) {
            initDisplay();
        } else {
            initDisplay();
            profile_name = profile.getName();
            profile_name = fixName(profile_name);
            name.setLabel(profile_name);
            message.setVisible(true);

            status.setLabel(profile.getStatus());

            display_picture.setImage(profile.getImage().getImage());
            display_picture.setSize(image_border.getSize());
            no_image.setVisible(false);
            ArrayList<String> friendlist = profile.getFriends();
            for (int i = 0; i < friendlist.size(); i++) {
                GLabel friend_name = new GLabel(fixName(friendlist.get(i)));
                add(friend_name, friends_header.getX() + 150 * (i / 6), friends_header.getY() + (friends_header.getHeight() + 10) * ((i) % 6) + friends_header.getHeight() + 10);
            }
        }
    }

    public void initDisplay() {
        removeAll();
        message.setVisible(false);
        add(message, canvas.getWidth() / 2 - message.getWidth() / 2, canvas.getHeight() * 7 / 8);
        add(image_border, 50, canvas.getHeight() / 5);
        name.setColor(Color.BLUE);
        name.setFont("Arial-bold-25");
        friends_header.setFont("Arial-bold-18");
        status.setFont("Arial-bold-18");

        add(name, image_border.getX(), image_border.getY() - 10);
        add(friends_header, name.getX() + canvas.getWidth() / 2, name.getY());
        add(display_picture, image_border.getLocation());
        add(no_image, image_border.getX() + image_border.getWidth() / 2 - no_image.getWidth() / 2, image_border.getY() + image_border.getHeight() / 2 - no_image.getHeight() / 2);
        add(status, image_border.getX(), image_border.getY() + image_border.getHeight() + 40);
    }

    private String fixName(String name) {
        StringTokenizer token = new StringTokenizer(name, " ");
        String temp_name = "";
        while (token.hasMoreTokens()) {
            String word = token.nextToken();
            temp_name = temp_name.concat(word.substring(0, 1).toUpperCase() + word.substring(1));
            temp_name += " ";
        }

        return temp_name;
    }

}
