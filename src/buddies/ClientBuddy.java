

package buddies;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientBuddy implements Runnable {
	private String hostName;
	private String portNumber;
	private TextArea convoBox;
	private TextField atf;
	private String sendMessage;

	public ClientBuddy(String hostName, String portNumber, TextArea convoBox,
			TextField atf) {
		this.hostName = hostName;
		this.portNumber = portNumber;
		this.convoBox = convoBox;
		this.atf = atf;
	}

	@Override
	public void run() {
		try {
			
			Socket socket = null;

			while (socket == null) {
				try {
					socket = new Socket(hostName, Integer.parseInt(portNumber));
				} catch (IOException e) {
					Main.progressIndicator.setVisible(true);
					Thread.sleep(1000);
				}

			}
			Main.progressIndicator.setVisible(false);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			getAtf().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (!getAtf().getText().equals("")) {
						getConvoBox().setText(
								getConvoBox().getText() + "You: "
										+ getAtf().getText() + "\n");
						setSendMessage(getAtf().getText());
						getAtf().clear();

						out.println(Main.username + ": " + getSendMessage());
					}
				}
			});

		
		
		
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void showRecieveMessage(String recieveMessage) {
		getConvoBox().setText(
				getConvoBox().getText() + recieveMessage + "\n");
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public TextArea getConvoBox() {
		return convoBox;
	}

	public void setConvoBox(TextArea convoBox) {
		this.convoBox = convoBox;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public TextField getAtf() {
		return atf;
	}

	public void setAtf(TextField atf) {
		this.atf = atf;
	}
}
