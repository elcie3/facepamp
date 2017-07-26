package ph.edu.dlsu.rivera.facepamphlet;

/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import acm.graphics.GImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

    public ArrayList<FacePamphletProfile> profiles = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    private String path = System.getProperty("user.dir") + "\\profiles.txt";

    /**
     * Constructor This method takes care of any initialization needed for the
     * database.
     */
    public FacePamphletDatabase() throws FileNotFoundException, IOException {
        // You fill this in
        File f = new File(path);
        FileReader source = new FileReader(f);
        BufferedReader br = new BufferedReader(source);

        String temp = br.readLine();
        while (temp != null) {

            FacePamphletProfile temp_profile = new FacePamphletProfile(temp);
            temp = br.readLine();

            temp_profile.setStatus(temp);
            temp_profile.setImage(br.readLine());
            temp = br.readLine();
            while (!temp.equals("end profile")) {
                StringTokenizer token = new StringTokenizer(temp, " ");
                String name = token.nextToken();
                int weight;
                if (token.hasMoreTokens()) {
                    weight = Integer.parseInt(token.nextToken());
                } else {
                    System.out.println("No weight detected");
                    weight = 1;
                }
                temp_profile.addFriend(name, weight);

//            System.out.println(temp+" added friend");
                temp = br.readLine();
            }

            addProfile(temp_profile);

            temp = br.readLine();
        }

    }

    public void saveProfiles() throws IOException {
        FileWriter writer = new FileWriter(path, false);
        PrintWriter print = new PrintWriter(writer);
        for (int i = 0; i < profiles.size(); i++) {
            print.println(profiles.get(i).save());
        }
        print.close();
    }

    public ArrayList<String> getProfiles() {

        for (int i = 0; i < profiles.size(); i++) {
            if (!names.contains(profiles.get(i).getName())) {
                names.add(profiles.get(i).getName());
            }
        }
        return names;
    }

    /**
     * This method adds the given profile to the database. If the name
     * associated with the profile is the same as an existing name in the
     * database, the existing profile is replaced by the new profile passed in.
     */
    public void addProfile(FacePamphletProfile profile) {
        // You fill this in
        profiles.add(profile);
    }

    /**
     * This method returns the profile associated with the given name in the
     * database. If there is no profile in the database with the given name, the
     * method returns null.
     */
    public FacePamphletProfile getProfile(String name) {
        // You fill this in.  Currently always returns null.
        FacePamphletProfile profile = null;
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getName().equals(name)) {
                profile = profiles.get(i);
            }
        }

        return profile;
    }

    /**
     * This method removes the profile associated with the given name from the
     * database. It also updates the list of friends of all other profiles in
     * the database to make sure that this name is removed from the list of
     * friends of any other profile.
     *
     * If there is no profile in the database with the given name, then the
     * database is unchanged after calling this method.
     */
    public void deleteProfile(String name) {
        // You fill this in
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getName().equals(name)) {
                profiles.remove(i);
            }
        }
    }

    /**
     * This method returns true if there is a profile in the database that has
     * the given name. It returns false otherwise.
     */
    public boolean containsProfile(String name) {
        // You fill this in.  Currently always returns false.
        if (getProfile(name) != null) {
            return true;
        } else {
            return false;
        }
    }

}
