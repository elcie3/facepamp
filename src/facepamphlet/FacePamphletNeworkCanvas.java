/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facepamphlet;

/**
 *
 * @author Rivera
 */
import acm.graphics.*;
import acm.util.RandomGenerator;
import java.awt.*;
import java.util.*;
import javax.swing.JFrame;

public class FacePamphletNeworkCanvas extends GCanvas {

    RandomGenerator rgen = new RandomGenerator().getInstance();
    public ArrayList<FacePamphletNode> nodes = new ArrayList<>();
    public ArrayList<FacePamphletEdge> edges = new ArrayList<>();
    public double x, y, width, height;
    private String name, initials = "";

    public FacePamphletNeworkCanvas(JFrame frame) {
        this.removeAll();
        width = frame.getWidth();
        height = frame.getHeight();
    }

    public void reset() {
        this.removeAll();
        nodes.removeAll(nodes);
    }

    public FacePamphletNode createNode(String name) {

        FacePamphletNode node = new FacePamphletNode(name, rgen.nextDouble(100, width - 100), rgen.nextDouble(100, height - 100));

        checkOverlapping();
        nodes.add(node);

        return node;
    }

    public void displayNodes() {
        this.removeAll();
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).DrawNode(this);
        }

    }

    private void checkOverlapping() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = nodes.size() - 1; j > i; j--) {
                double dist = distance(nodes.get(i).getLocation(), nodes.get(j).getLocation());

                while (dist < 100) {

                    GPoint newLoc = new GPoint(rgen.nextDouble(100, width - 100), rgen.nextDouble(100, height - 100));
                    nodes.get(j).setLocation(newLoc);
                    nodes.get(j).update();

                    dist = distance(nodes.get(i).getLocation(), nodes.get(j).getLocation());
                }

            }
        }

    }

    private double distance(GPoint gpoint1, GPoint gpoint2) {
        double dist = Math.sqrt(Math.pow(gpoint1.getX() - gpoint2.getX(), 2) + Math.pow(gpoint1.getY() - gpoint2.getY(), 2));

        return dist;
    }

    public void displayConnections() {
        for (int i = 0; i < edges.size(); i++) {
            edges.get(i).update();
            edges.get(i).show();

        }
    }

    public void createEdge(FacePamphletNode a, FacePamphletNode b, int weight) {
        FacePamphletEdge newEdge = new FacePamphletEdge(a, b, this, weight);
        edges.add(newEdge);
    }

    public FacePamphletNode getNode(GObject object) {
        for (int i = 0; i < nodes.size(); i++) {
            try {
                if (nodes.get(i).node == (GOval) object) {
                    return nodes.get(i);
                }
            } catch (Exception e) {
            }
            try {
                if (nodes.get(i).label == (GLabel) object) {
                    return nodes.get(i);
                }
            } catch (Exception e) {
            }

        }
        return null;
    }

    public void removeConnection(FacePamphletNode a, FacePamphletNode b) {
        for (int i = 0; i < edges.size(); i++) {
            FacePamphletEdge temp_edge = edges.get(i);
            if ((temp_edge.nodeA == a && temp_edge.nodeB == b) || (temp_edge.nodeB == a && temp_edge.nodeA == b)) {
                edges.remove(i);
                System.out.println("Connection between " + a.name + " and " + b.name + " removed");
            }
        }

    }

}
