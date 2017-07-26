package ph.edu.dlsu.rivera.facepamphlet;

/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;

import java.util.*;

public class FacePamphletProfile implements FacePamphletConstants {

    private String name = "", status = "Status", image = "";
    private GImage gImageobject = new GImage("");
    private ArrayList<String> friends = new ArrayList<>();
    public ArrayList<Integer> weights = new ArrayList<>();
    private String summary;
    public FacePamphletNode profile_node;
    public String gender = "male";

    /**
     * Constructor This method takes care of any initialization needed for the
     * profile.
     */

    public FacePamphletProfile(String name) {
        // You fill this in
        this.name = name;
    }

    /**
     * This method returns the name associated with the profile.
     */
    public String getName() {
        // You fill this in.  Currently always returns the empty string.
        return this.name;
    }

    /**
     * This method returns the image associated with the profile. If there is no
     * image associated with the profile, the method returns null.
     */
    public GImage getImage() {
        // You fill this in.  Currently always returns null.
        return this.gImageobject;
    }

    /**
     * This method sets the image associated with the profile.
     */
    public void setImage(String image) {
        // You fill this in
        if (!image.equals("")) {
            gImageobject.setImage(System.getProperty("user.dir") + "\\images\\" + image);
            this.image = image; 
        }

    }

    /**
     * This method returns the status associated with the profile. If there is
     * no status associated with the profile, the method returns the empty
     * string ("").
     */
    public String getStatus() {
        // You fill this in.  Currently always returns the empty string.
        return status;
    }

    /**
     * This method sets the status associated with the profile.
     */
    public void setStatus(String status) {
        // You fill this in
        this.status = status;
    }

    /**
     * This method adds the named friend to this profile's list of friends. It
     * returns true if the friend's name was not already in the list of friends
     * for this profile (and the name is added to the list). The method returns
     * false if the given friend name was already in the list of friends for
     * this profile (in which case, the given friend name is not added to the
     * list of friends a second time.)
     */
    public boolean addFriend(String friend) {
        // You fill this in.  Currently always returns true.
        friends.add(friend);
        weights.add(1);
        return true;
    }

    public boolean addFriend(String friend, int weight) {
        // You fill this in.  Currently always returns true.
        friends.add(friend);
        weights.add(weight);
        return true;
    }

    /**
     * This method removes the named friend from this profile's list of friends.
     * It returns true if the friend's name was in the list of friends for this
     * profile (and the name was removed from the list). The method returns
     * false if the given friend name was not in the list of friends for this
     * profile (in which case, the given friend name could not be removed.)
     */
    public boolean removeFriend(String friend) {
        // You fill this in.  Currently always returns false.
        int index = friends.indexOf(friend);
        boolean status = friends.remove(friend);
        weights.remove(index);
        return status;

    }

    /**
     * This method returns an iterator over the list of friends associated with
     * the profile.
     */
    public boolean isFriend(FacePamphletProfile profile) {
        if (friends.contains(profile.getName())) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList getFriends() {
        // You fill this in.  Currently always returns null.
        return friends;
    }

    /**
     * This method returns a string representation of the profile. This string
     * is of the form: "name (status): list of friends", where name and status
     * are set accordingly and the list of friends is a comma separated list of
     * the names of all of the friends in this profile.
     *
     * For example, in a profile with name "Alice" whose status is "coding" and
     * who has friends Don, Chelsea, and Bob, this method would return the
     * string: "Alice (coding): Don, Chelsea, Bob"
     */
    public String toString() {
        // You fill this in.  Currently always returns the empty string.
        summary = name + " (" + status + "): ";
        for (int i = 0; i < friends.size() - 1; i++) {
            summary = summary.concat((String) friends.get(i) + ", ");
        }
        summary = summary.concat((String) friends.get(friends.size() - 1));

        return summary;
    }

    public String save() {
        summary = name + System.lineSeparator() + status + System.lineSeparator() + image + System.lineSeparator();
        if (friends.size() > 0) {
            for (int i = 0; i < friends.size(); i++) {
                summary = summary.concat(friends.get(i) + " " + weights.get(i) + System.lineSeparator());
            }
        }
        summary = summary.concat("end profile");

        return summary;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {
        gender = gender.toUpperCase();
        if (gender.charAt(0) == 'M') {
            this.gender = "Male";
            setImage("male.jpg");
        } else {
            this.gender = "Female";
            setImage("female.jpg");
        }
    }

}
