package DistributedSystems;


import java.io.*;
import java.net.*;
import java.util.Random;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class Trader {

	static String line;
	static Socket socket;
	static BufferedReader fromServer;
	static DataOutputStream toServer;
	static UserInterface user = new UserInterface();

	public static void main(String[] args) throws Exception {

		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		config.setServerURL(new URL("http://127.0.0.1:8080/xmlrpc"));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);

		Object[] params = new Object[]{new Integer(1)};
		System.out.println("About to get results...(params[0] = " + params[0] +")");

		double result = (double) client.execute("Broker.basePrice", params);
		System.out.println("GetPrice = " + result );
		
		
		socket = new Socket("localhost", 9999);
		toServer = new DataOutputStream(     // Datastream FROM Server
				socket.getOutputStream());
		fromServer = new BufferedReader(     // Datastream TO Server
				new InputStreamReader(socket.getInputStream()));
		while (sendRequest()) {              // Send requests while connected
			if (fromServer!=null) {
				receiveResponse();                 // Process server's answer
			}
		}
		socket.close();
		toServer.close();
		fromServer.close();
	}

	private static boolean sendRequest() throws IOException {
		boolean holdTheLine = true;          // Connection exists
		//Here code for trader
		String query = "";

		Random rdm = new Random();
		//Adding random of Action (Buy / Sell)

		query = Action.Random();
		//Random of Company
		query += Company.Random();
		//Random Price rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		query += 1.0 + (1999.0 - 1.0) * rdm.nextDouble() +";";
		//Random Quantity
		query += Integer.toString(rdm.nextInt(499)+1) + ";";


		//user.output("Enter message for the Server, or end the session with . : ");
		toServer.writeBytes((line = query) + '\n');
		// toServer.writeBytes((line = user.input()) + '\n');
		if (line != null) {              // Does the user want to end the session?
			holdTheLine = false;
		}
		return holdTheLine;
	}

	private static void receiveResponse() throws IOException {
		user.output("Server answers: " +
				new String(fromServer.readLine()) + "\n");
	}

}
