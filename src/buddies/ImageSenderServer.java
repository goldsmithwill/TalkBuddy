//package buddies;
//
//import java.net.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
//import javax.imageio.ImageIO;
//
//public class ImageSenderServer implements Runnable {
//	private ServerSocket serverSocket;
//	private Socket socket;
//	private String portNumber;
//	private String recieveFile;
//
//	public ImageSenderServer(String portNumber, String recieveFile) {
//		this.portNumber = portNumber;
//		this.recieveFile = recieveFile;
//	}
//
//	public void run() {
//		while (true) {
//			try {
//				serverSocket = new ServerSocket(Integer.parseInt(portNumber));
//				socket = serverSocket.accept();
//
//				BufferedImage img = ImageIO.read(ImageIO
//						.createImageInputStream(socket.getInputStream()));
//
//				ImageIO.write(img, "jpg", new File(recieveFile));
//
//			} catch (IOException e) {
//				e.printStackTrace();
//				break;
//			}
//		}
//	}
//}
package buddies;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class ImageSenderServer implements Runnable {
	private String filePath;
	private String portNumber;
	

	public ImageSenderServer(String filePath, String portNumber) {
		this.filePath=filePath;
		this.portNumber=portNumber;
	}

	@Override
	public void run() {
//		try {
//			ServerSocket serverSocket = new ServerSocket(
//					Integer.parseInt(portNumber));
//			File sendFile = new File(filePath);
//			while (true) {
//				Socket socket = serverSocket.accept();
//				FileSenderServerThread fsst = new FileSenderServerThread(socket, sendFile);
//				fsst.start();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		try {
			ServerSocket serverSocket = new ServerSocket(
				Integer.parseInt(portNumber));
				Socket socket = serverSocket.accept();
			
		
			BufferedImage bufferedImage = ImageIO.read(new File(filePath));
			ImageIO.write(bufferedImage, "JPG", socket.getOutputStream());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
