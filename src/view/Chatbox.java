package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ChatMessage;

public class Chatbox extends JPanel {
	
	/*
	 * Chatbox models the whole chat pane, including the actual field where messages appear,
	 * the field where the user can enter a message, and a button to send the message.
	 * 
	 * Message log should be a JList drawing from an ArrayList<Message>. See EmailSearcher
	 * Entry field should be a JTextField, see EmailSearcher
	 * Button should be a JButton button = new JButton("Text")
	 * Remember to put this.add(object) after each of these so it actually shows up on the panel.
	 * Formatting is a goddamn pain in the ass, see DraftboardGUI for some formatting stuff and Google
	 * for the rest.
	 * 
	 * SUPER BONUS: ACTION LISTENERS
	 * The button needs an ActionListener that takes the contents of the JTextField, creates
	 * a new Message, and adds it to the list of Messages (sends the message, basically).
	 * Use button.addActionlistener(new SendListener()) and then write the send message
	 * code in actionPerformed(ActionEvent e).
	 */
	//private ArrayList<ChatMessage> messageList;
	
	
	public Chatbox() {
		JLabel usernameLabel = new JLabel("Username: ");
		this.add(usernameLabel);
		JTextField usernameTextField = new JTextField();
		this.add(usernameTextField);
		JTextField userMessage = new JTextField();
		this.add(userMessage);
		JButton login = new JButton("Login");
		JButton logout = new JButton("Logout");
		JButton enter = new JButton("Enter");
		this.add(login);
		this.add(logout);
		this.add(enter);
		JList<String> messageLog;
		messageLog.add("hello!");
		this.add(messageLog);
	}
	
	public class SendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
