
package model;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Node {
	
	private Point location;
	private String title;
	private String text;
	private ArrayList<Node> nodeRefs;
	
	abstract Point topRight();
	abstract Point botLeft();
	
	public Node (String title, String text, Point location) {
		this.title = title;
		this.text = text;
		this.location = location;
	}
	
	public String getTitle () {
		return title;
	}
	
	public String getText () {
		return text;
	}
	
	public ArrayList<Node> getNodeRefs () {
		return nodeRefs;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public void setText (String text) {
		this.text = text;
	}
	
	public Point getLocation () {
		return location;
	}
	
	
	
}

