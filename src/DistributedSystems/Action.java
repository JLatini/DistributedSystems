package DistributedSystems;

import java.util.*;
/**
 * Used to specify the operation inside the query sent to the broker
 * 
 *
 */
public enum Action {
	BUY, SELL;
	
	public static String Random()
	{
		Random rdm = new Random();
		return Integer.toString(rdm.nextInt(Action.values().length )) + ";";
	}
	
}
