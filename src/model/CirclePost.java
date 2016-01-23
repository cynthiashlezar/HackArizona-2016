package model;

import java.awt.Point;
import java.util.ArrayList;

public class CirclePost extends Node {
	
	private String title; //user input for the title
	private String text; //user input for the text
	private String shape; //filename for the shape
	private Point location; //middle point of the post
	private int radius; //how large the circle is
	private ArrayList<Node> nodeRefs; //list of node references to the post-its that the arrows are pointing to
	
	
	//constructor that takes the the super from node, as well as
	//setting values for shape and radius
	public CirclePost (String title, String text, Point location) {
		super(title, text, location);
		shape = "circle.jpeg";
		radius = 100;
	}
	
	@Override
	//returns the top right point of the edge of the circle
	public Point topRight() {
		Point edge = new Point ((int)(this.location.x + radius * Math.cos(Math.PI / 4)), (int)(this.location.y + radius * Math.sin(Math.PI / 4)));
		return edge;
	}

	@Override
	//returns the bottom left point of the edge of the circle
	public Point botLeft() {
		Point edge = new Point ((int)(this.location.x + radius * Math.cos(5 * Math.PI / 4)), (int)(this.location.y + radius * Math.sin(5 * Math.PI / 4)));
		return edge;
	}

	@Override
	//returns the top left point of the edge of the circle
	public Point topLeft() {
		Point edge = new Point ((int)(this.location.x + radius * Math.cos(3 * Math.PI / 4)), (int)(this.location.y + radius * Math.sin(3 * Math.PI / 4)));
		return edge;
	}

	@Override
	//returns the bottom right point of the edge of the circle
	public Point botRight() {
		Point edge = new Point ((int)(this.location.x + radius * Math.cos(7 * Math.PI / 4)), (int)(this.location.y + radius * Math.sin(7 * Math.PI / 4)));
		return edge;
	}

	
}
