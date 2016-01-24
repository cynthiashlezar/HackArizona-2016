package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * This is the code for the actual window, so it extends JFrame.
 * JFrames are basically windows and they hold a bunch of JPanels.
 * The JPanels generally have the actual functional stuff on them!
 * Board extends JPanel.
 */

public class DraftboardGUI extends JFrame {
	private Board board;
	private Chatbox chatbox;
	
	public static void main(String[] args) {
		(new DraftboardGUI()).setVisible(true);
	}
	
	public DraftboardGUI() {
		// if you close it the program exits
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		board = new Board();
		chatbox = new Chatbox();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		
		// JScrollPane makes the Board scrollable.
		JScrollPane boardScroll = new JScrollPane(board);
		boardScroll.setPreferredSize(new Dimension(screenSize));
		this.add(boardScroll);
		board.setBackground(new Color(78, 186, 186));
		
		// This is where the toolbox will go....eventually.......
		JPanel toolboxPanel = new JPanel(new BorderLayout());
		JButton circle = new JButton("Circle");
		JButton square = new JButton("Square");
		circle.setBackground(new Color(172, 237, 237));
		square.setBackground(new Color(172, 237, 237));
		toolboxPanel.setPreferredSize(new Dimension(screenSize.width, 200));
		toolboxPanel.add(circle, BorderLayout.WEST);
		toolboxPanel.add(square, BorderLayout.CENTER);
		this.add(toolboxPanel, BorderLayout.PAGE_END);
		
		// Chatbox over here.
		chatbox.setPreferredSize(new Dimension(400, screenSize.height));
		this.add(chatbox, BorderLayout.EAST);
		chatbox.setBackground(new Color(78, 186, 186));
	}
	
	

}
