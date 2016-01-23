package model;

import java.awt.Point;
import java.util.ArrayList;

public class CirclePost extends Node {
	
	private String title;
	private String text;
	private String shape;
	private Point location;
	private ArrayList<Node> nodeRefs;
	
	public CirclePost (String title, String text, Point location) {
		super(title, text, location);
		shape = "circle.jpeg";
	}

	@Override
	Point topRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Point botLeft() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
