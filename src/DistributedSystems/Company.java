package DistributedSystems;

import java.util.Random;

/**
 * Used to specify the company inside the query sent to the broker
 * 
 *
 */
public enum Company { AAPL, IBM, MSFT, ORCL;

	public static String Random()
	{
		Random rdm = new Random();
		return Integer.toString(rdm.nextInt(Company.values().length - 1)) + ";";
	}
	
	public double getPrice(){
		return History.getInstance().GetHighestBid(Company.valueOf(this.name()));
	}
	
	public static int fromString(String s)
	{
		for (Company c : values())
		{
			if( c.name().equals(s))
				return c.ordinal();
		}
		return -1;
	}
	

}
