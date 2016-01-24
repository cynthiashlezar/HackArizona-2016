package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.CirclePost;
import model.Draftboard;
import model.Node;
import model.SquarePost;

/*
 * Board models the part of the GUI you can drop Nodes onto.
 * "extends JPanel" indicates that this is a component of a JFrame.
 */

public class Board extends JPanel {

	private Draftboard draftboard;
	private Image circleNode, squareNode;
	private Point dropPoint;
	private final int NODE_LENGTH = 100;
	
	public Board() {
		draftboard = new Draftboard();
		// debug
		draftboard.addNode(new CirclePost("whatever", "lol", new Point(400, 400)));
		draftboard.addNode(new CirclePost("whateverrrrrr", "lol", new Point(500, 400)));
		draftboard.addNode(new SquarePost("whateverrrrrr", "lol", new Point(600, 500)));
		draftboard.getNodes().get(0).addNodeRef(draftboard.getNodes().get(1));
		draftboard.getNodes().get(0).addNodeRef(draftboard.getNodes().get(2));

		// Reads in images from filenames

		try {
			circleNode = ImageIO.read(new File("src/images/circle.png"));
			squareNode = ImageIO.read(new File("src/images/square.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Sets Board size to size of screen (w/scrollbars)
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);

		// Sets the visible size of the Board to these dimensions & sets background color
		this.setPreferredSize(new Dimension(2000, 1000));
		this.setBackground(Color.WHITE);
	}
	
	/* 
	 * paintComponent draws whenever repaint() is called. It draws the nodes and lines between the nodes.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(92, 221, 92));
		g2.setStroke(new BasicStroke(10));
		for(Node n : draftboard.getNodes()) {
			// TODO make it go from corner to corner
			for(Node r : n.getNodeRefs())
				g2.drawLine(n.getLocation().x + NODE_LENGTH / 2, n.getLocation().y + NODE_LENGTH / 2,
						r.getLocation().x + NODE_LENGTH / 2, r.getLocation().y + NODE_LENGTH / 2);
			
			if(n.getClass() == CirclePost.class) {
				g2.drawImage(circleNode, n.getLocation().x, n.getLocation().y, NODE_LENGTH, NODE_LENGTH, null);
			} else if(n.getClass() == SquarePost.class) {
				g2.drawImage(squareNode, n.getLocation().x, n.getLocation().y, NODE_LENGTH, NODE_LENGTH, null);
			}
			
		}
	}
}
