package model;
import java.util.ArrayList;
import java.awt.Point;

public class SquarePost extends Node {
	
	private String title;
	private String text;
	private String shape;
	private Point location;
	private ArrayList<Node> nodeRefs;
	
	public SquarePost (String title, String text, Point location) {
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

	@Override
	Point topLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Point botRight() {
		// TODO Auto-generated method stub
		return null;
	}
}



