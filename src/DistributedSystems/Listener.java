/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package DistributedSystems;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

// Trader with IQ

class Listener {

	static String line;
	static Socket socket;
	static BufferedReader fromServer;
	static DataOutputStream toServer;
	static UserInterface user = new UserInterface();
	static int companyIndex; 
	
    public static void main(String []args) throws JMSException, Exception {

        String user = env("ACTIVEMQ_USER", "admin");
        String password = env("ACTIVEMQ_PASSWORD", "password");
        String host = env("ACTIVEMQ_HOST", "localhost");
        int port = Integer.parseInt(env("ACTIVEMQ_PORT", "61616"));
        String destination = arg(args, 0, "event");

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://" + host + ":" + port);

        Connection connection = factory.createConnection(user, password);
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination dest = new ActiveMQTopic(destination);

        MessageConsumer consumer = session.createConsumer(dest);
        long start = System.currentTimeMillis();
        long count = 1;
        System.out.println("Waiting for messages...");
        while(true) {
            Message msg = consumer.receive();
            if( msg instanceof  TextMessage ) {
                String body = ((TextMessage) msg).getText();
                System.out.println(((TextMessage) msg).getText());
                if( "SHUTDOWN".equals(body)) {
                    long diff = System.currentTimeMillis() - start;
                    System.out.println(String.format("Received %d in %.2f seconds", count, (1.0*diff/1000.0)));
                    break;
                } else {
                    if( count != msg.getIntProperty("id") ) {
                        System.out.println("mismatch: "+count+"!="+msg.getIntProperty("id"));
                    }
                    count = msg.getIntProperty("id");

                    if( count == 0 ) {
                        start = System.currentTimeMillis();
                    }
                    if( count % 1000 == 0 ) {
                        System.out.println(String.format("Received %d messages.", count));
                    }
                    count ++;
                }

            } else {
                System.out.println("Unexpected message type: "+msg.getClass());
            }
        }
        connection.close();
        
        
        //Trader part
        
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
        
    }
    
    private static boolean sendRequest() throws IOException {
		boolean holdTheLine = true;          // Connection exists
		//Here code for trader
		String query = "";

		
		String companyName;
		
		Random rdm = new Random();
		//Adding random of Action (Buy / Sell)

		query = Action.Random();
		//Random of Company
		companyName = Company.Random();
		query += companyName;
		companyIndex = Integer.parseInt(companyName.replace(";", "").trim());
		
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

    private static String env(String key, String defaultValue) {
        String rc = System.getenv(key);
        if( rc== null )
            return defaultValue;
        return rc;
    }

    private static String arg(String []args, int index, String defaultValue) {
        if( index < args.length )
            return args[index];
        else
            return defaultValue;
    }
}