package guiexamples;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Implement a GUI that allows users to search through and sort a list of their
 * contacts.
 */
public class EmailSearcher extends JFrame {

	public static void main(String[] args) {
		EmailSearcher window = new EmailSearcher();
		window.setVisible(true);
	}

	// All emails that will be known in this example of event-driven programming
	private ArrayList<String> model;

	// DefaultListModel<E> is used here because it implements ListModel<E>
	// You could create your own collection and also implement ListModel<E>
	// These are the strings that will be store in displayList to show in the
	// GUI
	private DefaultListModel<String> matchingEmails;

	// The graphical view of any list that implements ListModel<E>. This
	// displayList
	// will store displayListModel as an instance variable with
	// setModel(ListModel<E>)
	private JList<String> displayList;

	// A single line editor used to enter a search string
	private JTextField searchBar;

	private JButton removeButton;
	
	private JPanel myPanel;

	public EmailSearcher() {
		layoutGUI();
		setUpModel(); // Must initialize all three lists (one is done)
		registerListeners();
	}

	private void layoutGUI() {
		/*
		 * TODO 03 Add the windowListener to the Jframe.
		 */
		/*
		 * TODO 04 Create an add button that allows a user to add a new contact
		 * to the email list.
		 */
		this.setTitle("Email Searcher");
		this.setSize(600, 600);
		this.setLocation(200, 20);
		this.addWindowListener(new CloseButtonListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchBar = new JTextField();
		this.add(searchBar, BorderLayout.NORTH);
		displayList = new JList<String>();
		this.add(displayList, BorderLayout.CENTER);
		removeButton = new JButton("Remove the element at the selected index");
		
		myPanel = new JPanel();
		myPanel.add(removeButton);
		
		this.add(myPanel, BorderLayout.SOUTH);
	}

	private void setUpModel() {
		/*
		 * TODO 01: Use FileInputStream and ObjectInputStream to read the model
		 * from the "myFile". If there is no "myFile", then the model should
		 * initialize the defualt arrayList.
		 */
		try {
			FileInputStream fis = new FileInputStream("myfile");
			ObjectInputStream ois = new ObjectInputStream(fis);
			model = (ArrayList<String>) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model = new ArrayList<String>();
			model.add("Peter Parker <spiderman@dailybugle.com>");
			model.add("Lester Mccann <lester@cs.arizona.edu>");
			model.add("Steve Rogers <captainamerica@shield.gov>");
			model.add("Saumya Debray <debray@cs.arizona.edu>");
			model.add("Patrick Homer <homer@cs.arizona.edu>");
			model.add("Tony Stark <ironman@starkindustries.com>");
			model.add("Beichuan Zhang <bz@cs.arizona.edu>");
			model.add("Rick Schlichting <rick@cs.arizona.edu>");
			model.add("Pete Downey <pete@cs.arizona.edu>");
			model.add("John Kececioglu <kece@cs.arizona.edu>");
			model.add("Natasha Romanoff <natalie@starkindustries.com>");
			model.add("Clark Kent <superman@dailyplanet.com>");
			model.add("Rick Snodgrass <rts@cs.arizona.edu>");
			model.add("Logan <wolverine@xmen.edu>");
			model.add("Hal Jordan <greenlantern@justiceleague.com>");
			model.add("Rick Mercer <mercer@cs.arizona.edu>");
			model.add("Sean Stephens <seanastephens@email.arizona.edu>");
			model.add("Jeremy Mowery <jermowery@email.arizona.edu");
			model.add("Sean Stephens  <seanastephens@email.arizona.edu");
			model.add("Daniel Vaughn <djvaughn@email.arizona.edu");
		}
		// Initialize the list of all the emails we will keep track of

		matchingEmails = new DefaultListModel<String>();
		for (String nameAndEmail : model)
			matchingEmails.addElement(nameAndEmail);

		displayList.setModel(matchingEmails);
	}

	private class SearchBarListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// Not necessary
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			updateSearch();
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			updateSearch();
		}
	}

	// Make the matching
	private void updateSearch() {
		String search = searchBar.getText().toLowerCase();
		matchingEmails.clear();
		for (String contact : model) {
			if (contact.toLowerCase().indexOf(search) != -1) {
				matchingEmails.addElement(contact);
			}
		}
		displayList.setModel(matchingEmails);
	}

	/*
	 * TODO 02 implement a window listener that when the user close the window a
	 * pop up appears asking if the user would like to save the model to myFile.
	 * also implement in this window listener the ability to save the file when
	 * the user says yes.
	 */
	private class CloseButtonListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			int reply = JOptionPane.showConfirmDialog(null,
					"Do you want to save", null, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_NO_CANCEL_OPTION) {
				try {
					FileOutputStream fos = new FileOutputStream("myfile");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(model);
					oos.close();
					fos.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private void registerListeners() {
		searchBar.getDocument().addDocumentListener(new SearchBarListener());

		JListListener listener = new JListListener();
		removeButton.addActionListener(listener);
	}

	private class JListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String emailToRemove = displayList.getSelectedValue();
			model.remove(emailToRemove);
			updateSearch();
		}
	}
}