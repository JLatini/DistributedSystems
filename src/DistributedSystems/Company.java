package DistributedSystems;

import java.util.Random;


public enum Company { AAPL, IBM, MSFT, ORCL;

	public static String Random()
	{
		Random rdm = new Random();
		return Integer.toString(rdm.nextInt(Company.values().length - 1)) + ";";
	}
	
	public double getPrice(){
		return 67.0 * Company.valueOf(this.name()).ordinal();
	}

}
