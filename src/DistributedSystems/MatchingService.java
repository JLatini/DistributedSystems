package DistributedSystems;

import java.io.*;
import java.net.*;

public class MatchingService extends Thread {
	Socket client;
	MatchingService(Socket client){this.client = client;}
	
	
	@Override
	public void run(){
		String query;
		String line;
		BufferedReader fromClient;
		DataOutputStream toClient;
		boolean connected = true;
		System.out.println("Thread started: "+this);
		try{
			fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			toClient = new DataOutputStream (client.getOutputStream());
			while(connected){
				line = fromClient.readLine();
				System.out.println("Received : "+line);
				if(line.equals(".")) connected=false;
				else
				{
					//Matching treatment
				}
				fromClient.close(); toClient.close(); client.close();
				System.out.println("Thread ended: "+this);
			}
		}catch(Exception e){System.out.println(e);
	}
}
}
