//package buddies;
//
//import java.net.*;
//import java.io.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import javax.imageio.*;
//
//public class ImageSenderClient implements Runnable {
//	private Image image;
//	private BufferedImage bufferedImage;
//	private byte[] bytes;
//	private String hostName;
//	private String portNumber;
//	private String sendFile;
//
//	public ImageSenderClient(String hostName, String portNumber, String sendFile) {
//		this.hostName = hostName;
//		this.portNumber = portNumber;
//		this.sendFile = sendFile;
//	}
//
//	@Override
//	public void run() {
//
//		try {
//			Socket socket = null;
//
//					socket = new Socket(hostName, Integer.parseInt(portNumber));
//			
//		
//			bufferedImage = ImageIO.read(new File(sendFile));
//			ImageIO.write(bufferedImage, "JPG", socket.getOutputStream());
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
package buddies;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class ImageSenderClient implements Runnable {
	private String recieveFile;
	private String hostName;
	private String portNumber;

	public ImageSenderClient(String recieveFile, String hostName,
			String portNumber) {
		this.recieveFile = recieveFile;
		this.hostName = hostName;
		this.portNumber = portNumber;
	}

	@Override
	public void run() {
		// try {
		// Socket socket = null;
		// while (socket == null) {
		// try {
		// socket = new Socket(hostName, Integer.parseInt(portNumber));
		// } catch (IOException e) {
		// System.out.println("Connecting..");
		// Thread.sleep(1000);
		// }
		//
		// }
		// byte[] mybytearray = new byte[1024];
		// InputStream in = socket.getInputStream();
		// FileOutputStream fileOut = new FileOutputStream(recieveFile);
		// BufferedOutputStream out = new BufferedOutputStream(fileOut);
		// int bytesRead = in.read(mybytearray, 0, mybytearray.length);
		// out.write(mybytearray, 0, bytesRead);
		// out.close();
		// socket.close();
		// } catch (IOException | InterruptedException e) {
		// e.printStackTrace();
		// }
		while (true) {
			try {
				Socket socket = null;
				while (socket == null) {
					try {
						socket = new Socket(hostName,
								Integer.parseInt(portNumber));
					} catch (IOException e) {
						System.out.println("Connecting..");
						Thread.sleep(1000);
					}

				}
				BufferedImage img = ImageIO.read(ImageIO
						.createImageInputStream(socket.getInputStream()));

				ImageIO.write(img, "jpg", new File(recieveFile));

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
