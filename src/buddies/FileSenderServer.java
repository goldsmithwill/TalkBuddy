package buddies;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileSenderServer implements Runnable {
	private String filePath;
	private String portNumber;
	

	public FileSenderServer(String filePath, String portNumber) {
		this.filePath=filePath;
		this.portNumber=portNumber;
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(
					Integer.parseInt(portNumber));
			File sendFile = new File(filePath);
			while (true) {
				Socket socket = serverSocket.accept();
				FileSenderServerThread fsst = new FileSenderServerThread(socket, sendFile);
				fsst.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
