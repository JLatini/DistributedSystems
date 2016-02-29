package DistributedSystems;

import java.net.*;
import java.net.*;

public class Broker {

	public static void main(String[] args) throws Exception {
		ServerSocket contactSocket = new ServerSocket(9999);
		while(true){
			Socket client = contactSocket.accept();
			System.out.println("Broker online");
			new MatchingService(client).start();
		}
	}
}
