package model;
import java.util.ArrayList;
import java.awt.Point;

public class SquarePost extends Node {
	
	private String title; //user input for the title
	private String text; //user input for the text
	private String shape; //filename for the shape
	private Point location; //middle point of the post
	private int radius; //how large the square is
	private ArrayList<Node> nodeRefs; //list of node references to the post-its that the arrows are pointing to
	
	
	//constructor that takes the the super from node, as well as
	//setting values for shape and radius
	public SquarePost (String title, String text, Point location) {
		super(title, text, location);
		shape = "square.jpeg";
		radius = 100;
	}


	@Override
	//returns the top right point of the edge of the square
	public Point topRight() {
		Point edge = new Point ();
		return edge;
	}

	@Override
	//returns the bottom left point of the edge of the square
	public Point botLeft() {
		Point edge = new Point ();
		return edge;
	}

	@Override
	//returns the top left point of the edge of the square
	public Point topLeft() {
		Point edge = new Point ();
		return edge;
	}

	@Override
	//returns the bottom right point of the edge of the square
	public Point botRight() {
		Point edge = new Point ();
		return edge;
	}
	
}



