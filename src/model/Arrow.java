
package model;
import java.awt.Point;


public class Arrow {
	private Node base, destination; //Connecting one idea to the next.
	
	private Point start, end; //Where does it start, where does it end???
	
	public Arrow(Node base, Node destination) {
		this.base = base;
		this.destination = destination;
		int xOffset = this.base.getLocation().x - this.destination.getLocation().x;
		int yOffset = this.base.getLocation().y - this.destination.getLocation().y;
		
	}
	
	
}
