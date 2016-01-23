package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
		usernameTextField.setColumns(5);
		this.add(usernameTextField);
		JButton login = new JButton("Login");
		JButton logout = new JButton("Logout");
		this.add(login);
		this.add(logout);
		JTextField userMessage = new JTextField();
		userMessage.setColumns(6);
		this.add(userMessage);
		JButton enter = new JButton("Enter");
		this.add(enter);
		JList<String> messageLog;
		JTextArea aChat = new JTextArea(20, 32);
		aChat.setEditable(false);
		JScrollPane theChat = new JScrollPane(aChat);
		this.add(theChat);
		//messageLog.add("hello!");
		//this.add(messageLog);
	}
	
	public class SendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
