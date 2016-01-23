
package model;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Node {
	
	private Point location; //middle point of the post
	private String title; //user input for the title
	private String text; //user input for the text
	private ArrayList<Node> nodeRefs; //list of node references to the post-its that the arrows are pointing to
	
	abstract Point topRight(); //top right of the post it
	abstract Point botLeft(); //bottom left of the post it
	abstract Point topLeft(); //top left of the post it
	abstract Point botRight(); //bottom right of the post it
	
	//constructor that sets the value for title, text, and location
	public Node (String title, String text, Point location) {
		this.title = title;
		this.text = text;
		this.location = location;
	}
	
	//returns the title
	public String getTitle () {
		return title;
	}
	
	//returns the text
	public String getText () {
		return text;
	}
	
	//returns the location
	public Point getLocation () {
		return location;
	}
	
	//returns the list of node references
	public ArrayList<Node> getNodeRefs () {
		return nodeRefs;
	}
	
	//changes the title if needed
	public void setTitle (String title) {
		this.title = title;
	}
	
	//changes the text if needed
	public void setText (String text) {
		this.text = text;
	}
	
	//changes the location if needed
	public void setLocation (Point location) {
		this.location = location;
	}

	
	
	
}

