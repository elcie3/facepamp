/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facepamphlet;

import acm.graphics.GCanvas;
import acm.graphics.GLine;
import java.awt.Color;

/**
 *
 * @author Rivera
 */
public class FacePamphletEdge {

    public FacePamphletNode nodeA, nodeB;

    double x1, y1, x2, y2;
    GLine connection;
    GCanvas canvas;
    public int weight;

    public FacePamphletEdge(FacePamphletNode a, FacePamphletNode b, GCanvas canvas) {
        nodeA = a;
        nodeB = b;
        this.canvas = canvas;
        x1 = nodeA.getCenter().getX();
        y1 = nodeA.getCenter().getY();
        x2 = nodeB.getCenter().getX();
        y2 = nodeB.getCenter().getY();
        connection = new GLine(x1, y1, x2, y2);

        System.out.println("Connected " + a.name + " and " + b.name);
        setColor();
    }

    public FacePamphletEdge(FacePamphletNode a, FacePamphletNode b, GCanvas canvas, int weight) {
        this.weight = weight;
        nodeA = a;
        nodeB = b;
        this.canvas = canvas;
        x1 = nodeA.getCenter().getX();
        y1 = nodeA.getCenter().getY();
        x2 = nodeB.getCenter().getX();
        y2 = nodeB.getCenter().getY();
        connection = new GLine(x1, y1, x2, y2);

        setColor();
        System.out.println("Connected " + a.name + " and " + b.name);
        
    }

    public void setColor() {
        switch (weight) {
            case 1:
                connection.setColor(Color.BLACK);
                break;
            case 2:
                connection.setColor(Color.BLUE);
                break;
            case 3:
                connection.setColor(Color.GREEN);
                break;
            case 4:
                connection.setColor(Color.MAGENTA);
                break;
            case 5:
                connection.setColor(Color.RED);
                break;
            default:

        }
    }

    void update() {
        x1 = nodeA.getCenter().getX();
        y1 = nodeA.getCenter().getY();
        x2 = nodeB.getCenter().getX();
        y2 = nodeB.getCenter().getY();
        connection.setStartPoint(x1, y1);
        connection.setEndPoint(x2, y2);

    }

    void show() {
        canvas.remove(connection);
        canvas.add(connection);
        connection.sendToBack();
    }

}
