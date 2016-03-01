package DistributedSystems;

import java.net.*;
import java.net.*;

public class Broker {

	public static void main(String[] args) throws Exception {
		ServerSocket contactSocket = new ServerSocket(9999);
		System.out.println("Broker Activated");
		while(true){
			Socket client = contactSocket.accept();			
			System.out.println("Client :"+ client.getLocalAddress() + " online");
			new MatchingService(client).start();
		}
	}
}
