package DistributedSystems;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

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
			toClient = new DataOutputStream(client.getOutputStream());
			
			ArrayList<Order> orders = new ArrayList<Order>();
			
			while (connected) {
				line = fromClient.readLine();
				System.out.println("Received : "+line);
				if(line.equals(".")) connected=false;
				else
				{
					//Matching treatment
					
					String[] mvt = line.split(";");	// Get different parts of the movement
					
					int action = Integer.parseInt(mvt[0]);			// Buy/Sell
					int company = Integer.parseInt(mvt[1]);			// Company
					double unitPrice = Double.parseDouble(mvt[2]);	// Price paid
					int quantity = Integer.parseInt(mvt[3]);		// Quantity
										
					// Add each order 
					for (int i=0;i<quantity;i++) {
						Order ord = new Order(Action.values()[action],
											Company.values()[company],
											unitPrice,
											client.getLocalAddress().toString());
						// System.out.println(ord.toString());
						orders.add(ord);
					}
				}
				
				fromClient.close(); toClient.close(); client.close();
				System.out.println("Thread ended: "+this);
			}
		} catch(Exception e){System.out.println(e);
	}
}
}
