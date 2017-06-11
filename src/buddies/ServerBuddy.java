//package buddies;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class ServerBuddy implements Runnable{
//
//	private String portNumber;
//	
//	public ServerBuddy(String portNumber){
//		this.portNumber = portNumber;
//	}
//	
//	@Override
//	public void run() {
//		ServerSocket serverSocket = null;
//
//		boolean listeningSocket = true;
//		try {
//			serverSocket = new ServerSocket(Integer.parseInt(portNumber));
//		} catch (IOException e) {
//			System.err.println("Could not listen on port: " + portNumber);
//		}
//
//		while (listeningSocket) {
//			Socket clientSocket = null;
//			try {
//				clientSocket = serverSocket.accept();
//			System.out.println("hi");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			ServerThreadBuddy stb = new ServerThreadBuddy(clientSocket);
//			stb.start();
//		}
//		try {
//			serverSocket.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//}
//
//package buddies;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class ServerBuddy implements Runnable{
//
//	private String portNumber;
//	
//	public ServerBuddy(String portNumber){
//		this.portNumber = portNumber;
//	}
//	
//	@Override
//	public void run() {
//		ServerSocket serverSocket = null;
//
//		boolean listeningSocket = true;
//		try {
//			serverSocket = new ServerSocket(Integer.parseInt(portNumber));
//		} catch (IOException e) {
//			System.err.println("Could not listen on port: " + portNumber);
//		}
//
//		while (listeningSocket) {
//			Socket clientSocket = null;
//			try {
//				clientSocket = serverSocket.accept();
//		System.out.println("ClientSocket successfully created.");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			ServerThreadBuddy stb = new ServerThreadBuddy(clientSocket);
//			stb.start();
//		}
//		try {
//			serverSocket.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//}
package buddies;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBuddy implements Runnable {
	private String portNumber;
	

	public ServerBuddy(String portNumber) {
		this.portNumber=portNumber;
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(
					Integer.parseInt(portNumber));
			while (true) {
				Socket socket = serverSocket.accept();
			ServerThreadBuddy stb = new ServerThreadBuddy(socket);
				stb.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
