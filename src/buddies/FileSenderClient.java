package buddies;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class FileSenderClient implements Runnable {
	private String recieveFile;
	private String hostName;
	private String portNumber;

	public FileSenderClient(String recieveFile, String hostName,
			String portNumber) {
		this.recieveFile = recieveFile;
		this.hostName = hostName;
		this.portNumber = portNumber;
	}

	@Override
	public void run() {
		try {
			Socket socket = null;
			while (socket == null) {
				try {
					socket = new Socket(hostName, Integer.parseInt(portNumber));
				} catch (IOException e) {
					System.out.println("Connecting..");
					Thread.sleep(1000);
				}

			}
			byte[] mybytearray = new byte[1024];
			InputStream in = socket.getInputStream();
			FileOutputStream fileOut = new FileOutputStream(recieveFile);
			BufferedOutputStream out = new BufferedOutputStream(fileOut);
			int bytesRead = in.read(mybytearray, 0, mybytearray.length);
			out.write(mybytearray, 0, bytesRead);
			out.close();
			socket.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}