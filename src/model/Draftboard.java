package model;

import java.util.ArrayList;

public class Draftboard {
	
	private ArrayList<Node> nodes;
	//ArrayList<Arrow> arrows;
	private boolean consolidationMode;
	private ArrayList<Node> consolidationList;
	
	public Draftboard() {
		nodes = new ArrayList<Node>();
		consolidationMode = false;
		consolidationList = new ArrayList<Node>();
	}
	
	public void addNode(Node e) { //adds a node without any connections
		nodes.add(e);
	}
	
	public void addNode(Node e, Node original) { //adds a node onto another
		nodes.add(e);
		original.addNodeRef(e);
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public boolean getConsolMode() { //is Consolidation Mode on or off?
		return consolidationMode;
	}
	
	public boolean switchConsolMode() { // stands for Consolidation Mode; switches it on or off
		if (consolidationMode) {
			consolidationList.clear();
		}
		consolidationMode = !consolidationMode;
		return consolidationMode;
	}
	
	public void addToConsolList(Node ref) { //adds a node reference to the consolidation list
		consolidationList.add(ref);
	}
	
	public void consolidate() { //wipes nodes, replaces it with the consolidation list, and re-establishes connections
		nodes.clear();
		for (Node node : consolidationList) {
			nodes.add(node);
		}
		for (Node node : nodes) {
			for (Node ref : node.getNodeRefs()) {
				if (!nodes.contains(ref)) {
					node.deleteRef(ref);
				}
			}
		}
		switchConsolMode();
	}
	
	/*
	public void addArrow(Arrow e) {
		arrows.add(e);
	}
	
	public ArrayList<Arrow> getArrows() {
		return arrows;
	}
	*/
	
}
