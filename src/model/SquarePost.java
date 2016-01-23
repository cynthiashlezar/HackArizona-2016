package model;
import java.util.ArrayList;
import java.awt.Point;

public class SquarePost extends Node {
	
	private String title;
	private String text;
	private String shape;
	private Point location;
	private int radius;
	private ArrayList<Node> nodeRefs;
	
	public SquarePost (String title, String text, Point location) {
		super(title, text, location);
		shape = "square.jpeg";
		radius = 100;
	}

	@Override
	public Point topRight() {
		Point edge = new Point ((int)(this.location.x + radius * Math.cos(Math.PI / 4)), (int)(this.location.y + radius * Math.sin(Math.PI / 4)));
		return edge;
	}

	@Override
	public Point botLeft() {
		Point edge = new Point ((int)(this.location.x + radius * Math.cos(5 * Math.PI / 4)), (int)(this.location.y + radius * Math.sin(5 * Math.PI / 4)));
		return edge;
	}

	@Override
	public Point topLeft() {
		Point edge = new Point ((int)(this.location.x + radius * Math.cos(3 * Math.PI / 4)), (int)(this.location.y + radius * Math.sin(3 * Math.PI / 4)));
		return edge;
	}

	@Override
	public Point botRight() {
		Point edge = new Point ((int)(this.location.x + radius * Math.cos(7 * Math.PI / 4)), (int)(this.location.y + radius * Math.sin(7 * Math.PI / 4)));
		return edge;
	}
}



