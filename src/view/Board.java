package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Draftboard;
import model.Node;

public class Board extends JPanel {

	private Draftboard draftboard;
	private Image circleNode, squareNode;
	
	public Board() {
		draftboard = new Draftboard();
		
		try {
			circleNode = ImageIO.read(new File("circle.jpg"));
			squareNode = ImageIO.read(new File("square.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);

		this.setPreferredSize(new Dimension(2000, 1000));
		this.setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for(Node n : draftboard.getNodes()) {
		}
	}
}
