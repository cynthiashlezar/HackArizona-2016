package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		
		// This is where the toolbox will go....eventually.......
		JPanel toolboxPanel = new JPanel(new BorderLayout());
		JButton circle = new JButton("Circle");
		JButton square = new JButton("Square");
		circle.addActionListener(new CircleListener());
		square.addActionListener(new SquareListener());
		toolboxPanel.setPreferredSize(new Dimension(screenSize.width, 200));
		toolboxPanel.add(circle, BorderLayout.WEST);
		toolboxPanel.add(square, BorderLayout.CENTER);
		this.add(toolboxPanel, BorderLayout.PAGE_END);
		
		// Chatbox over here.
		chatbox.setPreferredSize(new Dimension(400, screenSize.height));
		this.add(chatbox, BorderLayout.EAST);
	}
	
	public class SquareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(board.getDrawState() == 2) board.setDrawState(0);
			else board.setDrawState(2);
		}

	}

	public class CircleListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(board.getDrawState() == 1) board.setDrawState(0);
			else board.setDrawState(1);
		}

	}
}
