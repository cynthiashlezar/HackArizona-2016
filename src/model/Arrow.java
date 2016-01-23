
package model;
import java.awt.Point;


public class Arrow {
	
	private Node base, destination; //Connecting one idea to the next.
	private Point start, end; //Where does it start, where does it end???
	
	public Arrow(Node base, Node destination) {
		this.base = base;
		this.destination = destination;
		refreshLocation();
	}
	
	public void refreshLocation() {
		int xOff = this.base.getLocation().x - this.destination.getLocation().x; //stands for "x offset"
		int yOff = this.base.getLocation().y - this.destination.getLocation().y; //stands for "y offset"
		if (xOff >= 0 && yOff >= 0) { //base x to the right of dest x, base y under dest y
			start = this.base.topLeft();
			end = this.destination.botRight();
		} else if (yOff >= 0) { //base x to the left of dest x, base y under dest y
			start = this.base.topRight();
			end = this.destination.botLeft();
		} else if (xOff >= 0) { //base x to the right of dest x, base y above dest y
			start = this.base.botLeft();
			end = this.destination.topRight();
		} else { //base x to the left of dest x, base y above dest y
			start = this.base.botRight();
			end = this.destination.topLeft();
		}
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getEnd() {
		return end;
	}
}
