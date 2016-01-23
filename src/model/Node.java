
package model;
import java.util.ArrayList;


public abstract class Node {
	
	private String title;
	private String text;
	private ArrayList<Node> nodeRefs;
	
	public Node (String title, String text) {
		this.title = title;
		this.text = text;
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
	
	
	
	
	
}

