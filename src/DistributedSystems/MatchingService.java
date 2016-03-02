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
						Log.WriteLog("D:\\Test\\test.log", ord.toString());
						if(Action.values()[action] == Action.BUY)
						{
							Broker.buyOrders.add(ord);
						}
						else
						{
							Broker.sellOrders.add(ord);
						}
						
					}
					ArrayList<Order> cloneBuyOrders =(ArrayList<Order>) Broker.buyOrders.clone();
					ArrayList<Order> cloneSellOrders = (ArrayList<Order>) Broker.sellOrders.clone();
					matchOrders(cloneBuyOrders, cloneSellOrders);
				}
				
				fromClient.close(); toClient.close(); client.close();
				System.out.println("Thread ended: "+this);
			}
		} catch(Exception e){System.out.println(e);
	}
}


	private void matchOrders(ArrayList<Order> buyOrders, ArrayList<Order> sellOrders) {
		ArrayList<Order> sellToDelete = new ArrayList<Order>();
		ArrayList<Order> buyToDelete = new ArrayList<Order>();
		for(Order o : buyOrders)
		{
			for(Order o1 : sellOrders)
			{
				if(o.getCompany() == o1.getCompany() && o.getUnitPrice() >= o1.getUnitPrice())
				{
					System.out.println("Match!");
					buyToDelete.add(o);
					sellToDelete.add(o1);
					break;
				}
			}
		}
		
		for(Order o : buyToDelete)
		{
			for(Order _o : buyOrders){
				if(o==_o)
				{
					Broker.buyOrders.remove(_o);
				}
			}
		}
		for(Order o : sellToDelete)
		{
			for(Order _o : sellOrders){
				if(o==_o)
				{
					//Sell to verify : Only remove one instance, when it must remove every concerned ones..
					Broker.sellOrders.remove(_o);
				}
			}
		}
	}
}