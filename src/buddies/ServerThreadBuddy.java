
package buddies;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import application.Main;

public class ServerThreadBuddy extends Thread {

	private Socket socket = null;

	public ServerThreadBuddy(Socket socket) {
		super("ServerThreadBuddy");
		this.socket = socket;
	}

	public void run() {
		try {
			System.out.println(socket.getLocalPort());
			System.out.println(socket.getPort());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			String recieveMessage = null;
			while (true) {
				while ((recieveMessage = in.readLine()) == null) {
				}
				System.out.println("Recieve Message: " + recieveMessage);
				Main.client.showRecieveMessage(recieveMessage);
				if (recieveMessage.equals("End Conversation")) {
					break;
				}
			}
			out.println(recieveMessage);
			out.flush();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
