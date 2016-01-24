package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
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
	private int drawState; // 0 is default, 1 is circles, 2 is squares
	private boolean nodeClicked;
	private int clickedNodeIndex;
	private final int NODE_LENGTH = 100;
	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	private static final String ADDRESS = "localhost";
	
	public Board() {
		draftboard = new Draftboard();
		

		this.addMouseListener(new DrawListener());
		
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
	
	private void openConnection() {
		/* Our server is on our computer, but make sure to use the same port. */
		try {
			socket = new Socket(ADDRESS, DrawServer.SERVER_PORT);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			cleanUpAndQuit("Couldn't connect to the server");
		}
	}
	
	private void cleanUpAndQuit(String message) {
		JOptionPane.showMessageDialog(Board.this, message);
		try {
			if(socket != null) socket.close();
		} catch (IOException e) {
			// Couldn't close the socket, we are in deep trouble. Abandon ship.
			e.printStackTrace(); 
		}
	}
	
	private class ServerListener extends Thread {

		@Override
		public void run() {
			try {
				/* The server sent us an ArrayList, update */
				while (true) {
					ArrayList<Node> recievedNodes = (ArrayList<Node>) ois.readObject();
				}
			} catch (IOException e) {
				cleanUpAndQuit("The server hung up on us. Exiting...");
			} catch (ClassNotFoundException e) {
				cleanUpAndQuit("Got something from the server that wasn't a String...");
			}
		}		
	}
	
	public class DrawListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			String reply = "";
			if(drawState == 1) {
				reply = JOptionPane.showInputDialog("Enter Text: ");
				draftboard.addNode(new CirclePost(reply, reply, e.getPoint()));
				repaint();
			} else if(drawState == 2) {
				reply = JOptionPane.showInputDialog("Enter Text: ");
				draftboard.addNode(new SquarePost(reply, reply, e.getPoint()));
				repaint();
			} else if(nodeClicked) {
				for(int i = 0; i < draftboard.getNodes().size(); i++) {
					if(e.getPoint().distance(new Point(draftboard.getNodes().get(i).getLocation().x + NODE_LENGTH / 2,
							draftboard.getNodes().get(i).getLocation().y + NODE_LENGTH / 2)) < NODE_LENGTH / 2 &&
							i != clickedNodeIndex) {
						draftboard.getNodes().get(clickedNodeIndex).addNodeRef(draftboard.getNodes().get(i));
					}
				}
				nodeClicked = false;
				repaint();
			} else {
				for(int i = 0; i < draftboard.getNodes().size(); i++) {
					if(e.getPoint().distance(draftboard.getNodes().get(i).getLocation()) < NODE_LENGTH) {
						clickedNodeIndex = i;
						nodeClicked = true;
					}
				}
			}
			
			// Regardless of the draw state, it's probably a good idea to write to the server.
//			try {
//				oos.writeObject(draftboard.getNodes());
//			} catch (IOException ex) {
//				cleanUpAndQuit("Couldn't send nodes to the server");
//			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

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
			for(Node r : n.getNodeRefs()) {
				g2.setColor(new Color(92, 221, 92));
				g2.setStroke(new BasicStroke(10));
				g2.drawLine(n.getLocation().x + NODE_LENGTH / 2, n.getLocation().y + NODE_LENGTH / 2,
						r.getLocation().x + NODE_LENGTH / 2, r.getLocation().y + NODE_LENGTH / 2);
			}
		}
		
		for(Node n : draftboard.getNodes()) {
			if(n.getClass() == CirclePost.class) {
				g2.drawImage(circleNode, n.getLocation().x, n.getLocation().y, NODE_LENGTH, NODE_LENGTH, null);
				g2.setColor(Color.BLACK);
				g2.drawString(n.getText(), n.getLocation().x + 50, n.getLocation().y + 50);
			} else if(n.getClass() == SquarePost.class) {

				g2.drawImage(squareNode, n.getLocation().x, n.getLocation().y, NODE_LENGTH, NODE_LENGTH, null);
				g2.setColor(Color.BLACK);
				g2.drawString(n.getText(), n.getLocation().x + 50, n.getLocation().y + 50);
			}
			
		}
	}

	public void setDrawState(int s) {
		drawState = s;
	}
	
	public int getDrawState() {
		return drawState;
	}
}
