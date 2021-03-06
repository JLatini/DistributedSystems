package DistributedSystems;

import java.net.*;
import java.util.ArrayList;
import java.net.*;
/**
 * Central server, do the matching operations between the buy and sell orders
 * 
 *
 */
public class Broker {
	public static ArrayList<Order> buyOrders = new ArrayList<Order>();
	public static ArrayList<Order> sellOrders = new ArrayList<Order>();	
	public static void main(String[] args) throws Exception {
		ServerSocket contactSocket = new ServerSocket(9999);
		System.out.println("Broker Activated");
		History.getInstance();
		while(true){
			Socket client = contactSocket.accept();			
			System.out.println("Client :"+ client.getRemoteSocketAddress() + " online");
			new MatchingService(client).start();
		}
	}
	
	public double basePrice(int i)
	{
		Company c = Company.values()[i];
		return c.getPrice();
	}
}
