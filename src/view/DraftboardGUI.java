package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class DraftboardGUI extends JFrame {
	private Board board;
	
	public static void main(String[] args) {
		(new DraftboardGUI()).setVisible(true);
	}
	
	public DraftboardGUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		board = new Board();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		
		JScrollPane cs = new JScrollPane(board);
		cs.setPreferredSize(new Dimension(screenSize));
		this.add(cs);
		
		JPanel toolboxPanel = new JPanel(new BorderLayout());
		toolboxPanel.setPreferredSize(new Dimension(screenSize.width, 400));
		this.add(toolboxPanel, BorderLayout.PAGE_END);
	}

}
