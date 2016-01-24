package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ChatMessage;
import view.Chatbox.LoginListener;

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
	private ArrayList<String> messageList;

	private JTextField usernameTextField;
	private String username;
	private JButton login;
	private JLabel usernameLabel;
	private JTextField userMessage;
	private JButton enter;
	private JButton logout;
	private JTextArea aChat;
	private JScrollPane theChat;
	private DefaultListModel<String> finalMessage;
	private JList<String> displayList;
	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	private static final String ADDRESS = "localhost";
	

	public Chatbox() {
		// server garbage
				
		messageList = new ArrayList<String>();
		displayList = new JList<String>();
		usernameLabel = new JLabel("Username: ");
		this.add(usernameLabel);
		usernameTextField = new JTextField();
		usernameTextField.setColumns(5);
		this.add(usernameTextField);
		login = new JButton("Login");
		logout = new JButton("Logout");
		login.addActionListener(new LoginListener());
		this.add(login);
		this.add(logout);
		logout.setEnabled(false);
		logout.addActionListener(new LogoutListener());
		aChat = new JTextArea(20, 32);
		aChat.setEditable(false);
		theChat = new JScrollPane(aChat);
		this.add(theChat);
		userMessage = new JTextField();
		userMessage.setColumns(6);
		this.add(userMessage);
		enter = new JButton("Enter");
		this.add(enter);
		enter.setEnabled(false);
		enter.addActionListener(new SendListener());
		
		Color textAreaBG = new Color(172, 237, 237);
		aChat.setBackground(textAreaBG);
		logout.setBackground(textAreaBG);
		enter.setBackground(textAreaBG);
		login.setBackground(textAreaBG);
		
		openConnection();
		new ServerListener().start();
	}
	
	private void openConnection() {
		/* Our server is on our computer, but make sure to use the same port. */
		try {
			socket = new Socket(ADDRESS, ChatServer.SERVER_PORT);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			cleanUpAndQuit("Couldn't connect to the server");
		}
	}
	
	private void cleanUpAndQuit(String message) {
		JOptionPane.showMessageDialog(Chatbox.this, message);
		try {
			if(socket != null) socket.close();
		} catch (IOException e) {
			// Couldn't close the socket, we are in deep trouble. Abandon ship.
			e.printStackTrace(); 
		}
	}
	
	public class SendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String message = new ChatMessage(userMessage.getText(), username).message();
				oos.writeObject(message + "\n");
				userMessage.setText("");
			} catch (IOException ex) {
				cleanUpAndQuit("Couldn't send a message to the server");
			}
		}
		
	}
	
	private class ServerListener extends Thread {

		@Override
		public void run() {
			try {
				/* The server sent us a String? Stick it in the JList! */
				while (true)
					aChat.append((String) ois.readObject());
			} catch (IOException e) {
				cleanUpAndQuit("The server hung up on us. Exiting...");
			} catch (ClassNotFoundException e) {
				cleanUpAndQuit("Got something from the server that wasn't a String...");
			}
		}

		
	}
		
	public class LogoutListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			enter.setEnabled(false);
			logout.setEnabled(false);
			usernameLabel.setVisible(true);
			usernameTextField.setVisible(true);
			usernameLabel.setText("Username: ");
			login.setVisible(true);
			
			
		}
	}
	
	public class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			enter.setEnabled(true);
			username = usernameTextField.getText();
			login.setVisible(false);
			logout.setEnabled(true);
			usernameTextField.setText("");
			usernameTextField.setVisible(false);
			usernameLabel.setText("Username: " + username);
			
		}

	}

}
