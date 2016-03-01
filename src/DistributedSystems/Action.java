package DistributedSystems;

import java.util.*;

public enum Action {
	BUY, SELL;
	
	public static String Random()
	{
		Random rdm = new Random();
		return Integer.toString(rdm.nextInt(Action.values().length -1)) + ";";
	}
	
}
