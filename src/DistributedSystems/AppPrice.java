package DistributedSystems;

import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl; 
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class AppPrice {

	private static final int port = 8080;

	  public static void main (String [] args) {
	    try {

	      WebServer webServer = new WebServer(port);

	      XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
	      PropertyHandlerMapping phm = new PropertyHandlerMapping();

	      phm.addHandler( "Broker", Broker.class);
	      xmlRpcServer.setHandlerMapping(phm);

	     XmlRpcServerConfigImpl serverConfig =
	              (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();


	      webServer.start();

	      System.out.println("Web Server has been started..." );

	    } catch (Exception exception) {
	       System.err.println("JavaServer: " + exception);
	    }
	  }

}
