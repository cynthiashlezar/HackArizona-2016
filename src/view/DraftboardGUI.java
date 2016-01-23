package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.UnknownHostException;

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
		
		// This is where the toolbox will go....eventually.......
		JPanel toolboxPanel = new JPanel(new BorderLayout());
		toolboxPanel.setPreferredSize(new Dimension(screenSize.width, 200));
		this.add(toolboxPanel, BorderLayout.PAGE_END);
		
		// Chatbox over here.
		JScrollPane chatboxScroll = new JScrollPane(chatbox);
		chatboxScroll.setPreferredSize(new Dimension(400, screenSize.height));
		this.add(chatboxScroll, BorderLayout.EAST);
	}
	
	

}
