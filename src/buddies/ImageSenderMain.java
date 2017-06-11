//package buddies;
//
//public class ImageSenderMain {
//	public static void main(String[] args) {
//		String sendFile = "/Users/willgoldsmith/Downloads/flag-banner1.jpg";
//		String recieveFile = "/Users/willgoldsmith/Desktop/flag-banner1.jpg";
//		String serverPort = "3003";
//		String clientPort = "3000";
//		String hostName = "192.168.1.166";
//		ImageSenderServer server = new ImageSenderServer(serverPort,
//				recieveFile);
//		ImageSenderClient client = new ImageSenderClient(hostName, clientPort,
//				sendFile);
//		new Thread(server).start();
//		new Thread(client).start();
//	}
//}
package buddies;

public class ImageSenderMain {
	public static void main(String[] args) {
		String sendFile = "/Users/willgoldsmith/Downloads/flag-banner1.jpg";
		String recieveFile = "/Users/willgoldsmith/Desktop/flag-banner1.jpg";
		String serverPort = "3003";
		String clientPort = "3003";
		String hostName = "10.100.44.155";
		FileSenderServer server = new FileSenderServer(sendFile, serverPort);
		FileSenderClient client = new FileSenderClient(recieveFile, hostName,
				clientPort);
		new Thread(server).start();
		new Thread(client).start();
	}
}
