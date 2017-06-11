package buddies;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileSenderServerThread extends Thread {

	private Socket socket = null;

	private File sendFile;

	public FileSenderServerThread(Socket socket, File sendFile) {
		super("FileSenderServerThread");
		this.socket = socket;
		this.sendFile = sendFile;
	}

	public void run() {
		try {
			byte[] mybytearray = new byte[(int) sendFile.length()];
			System.out.println(sendFile.length());
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(sendFile));
			in.read(mybytearray, 0, mybytearray.length);
			OutputStream out = socket.getOutputStream();
			
			out.write(mybytearray, 0, mybytearray.length);
			out.flush();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}