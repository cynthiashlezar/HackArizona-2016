package model;

import java.util.ArrayList;

public class Draftboard {
	
	ArrayList<Node> nodes;
	ArrayList<Arrow> arrows;
	
	public Draftboard() {
		nodes = new ArrayList<Node>();
		arrows = new ArrayList<Arrow>();
	}
	
	public void addNode(Node e) {
		nodes.add(e);
	}
	
	public void addArrow(Arrow e) {
		arrows.add(e);
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public ArrayList<Arrow> getArrows() {
		return arrows;
	}

}
