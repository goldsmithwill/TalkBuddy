package buddies;

public class FileSenderMain {
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
