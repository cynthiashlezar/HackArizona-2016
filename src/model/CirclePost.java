package model;

import java.awt.Point;
import java.util.ArrayList;

public class CirclePost extends Node {
	
	private String title;
	private String text;
	private String shape;
	private Point location;
	private int radius;
	private ArrayList<Node> nodeRefs;
	
	public CirclePost (String title, String text, Point location) {
		super(title, text, location);
		shape = "circle.jpeg";
		radius = 100;
	}

	@Override
	public Point topRight() {
		Point edge = new Point ();
		return edge;
	}

	@Override
	public Point botLeft() {
		Point edge = new Point ();
		return edge;
	}

	@Override
	public Point topLeft() {
		Point edge = new Point ();
		return edge;
	}

	@Override
	public Point botRight() {
		Point edge = new Point ();
		return edge;
	}
	
	
}
