package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatServer {

	public static final int SERVER_PORT = 9001;

	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());

	public static void main(String[] args) throws IOException {
		sock = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);

		while (true) {
			Socket s = sock.accept();

			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

			/* Save the output stream so we can broadcast to them */
			clients.add(os);
			/* Start a thread to listen for input from this client. */
			ClientHandler c = new ClientHandler(is, clients);
			c.start();

			System.out.println("Accepted a new connection from " + s.getInetAddress());
		}
	}

	static class ClientHandler extends Thread {

		ObjectInputStream input;
		List<ObjectOutputStream> clients;

		public ClientHandler(ObjectInputStream input, List<ObjectOutputStream> clients) {
			this.input = input;
			this.clients = clients;
		}

		@Override
		public void run() {
			while (true) {

				String s = null;
				try {
					s = (String) input.readObject();
				} catch (IOException e) {
					/* Client left -- clean up and let the thread die */
					cleanUp();
					return;
				} catch (ClassNotFoundException e) {
					/* This one is probably a bug though */
					e.printStackTrace();
					cleanUp();
					return;
				}
				System.out.println("Received the String " + s + " from a client");

				writeStringToClients(s);
			}
		}

		private void writeStringToClients(String s) {
			synchronized (clients) {
				Set<ObjectOutputStream> closed = new HashSet<>();
				for (ObjectOutputStream client : clients) {
					System.out.println("Writing the String " + s + " to a client.");
					try {
						client.writeObject(s);
					} catch (IOException e) {
						/*
						 * If we can't write to the client, their socket was
						 * closed. Lets remove it from the list.
						 */
						closed.add(client);
					}
				}
				/* Remove closed connections from the list */
				clients.removeAll(closed);
			}
		}

		private void cleanUp() {
			/*
			 * Don't forget to close those sockets. Not an issue here, but you
			 * WILL run out eventually if you neglect this.
			 */
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}