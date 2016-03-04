package DistributedSystems;

import java.util.ArrayList;

/**
 * Used to store the lowest ask and the highest bid of every stock
 * 
 *
 */
public class History {

	private static History INSTANCE;
	private static ArrayList<HistoryFragment> history = new ArrayList<HistoryFragment>();

	private History(){
		history = new ArrayList<HistoryFragment>();
		history.add(new HistoryFragment(Company.AAPL,0,Integer.MAX_VALUE));
		history.add(new HistoryFragment(Company.IBM,0,Integer.MAX_VALUE));
		history.add(new HistoryFragment(Company.MSFT,0,Integer.MAX_VALUE));
		history.add(new HistoryFragment(Company.ORCL,0,Integer.MAX_VALUE));
	}

	public void UpdateValueLowestAsk(Company c, double lowestAsk){
		for (HistoryFragment hf : history) {
			if(hf.getCompany()==c){
				hf.setLowestAsk(lowestAsk);
			}
		}
	}

	public void UpdateValueHighestBid(Company c, double highestBid){
		for (HistoryFragment hf : history) {
			if(hf.getCompany()==c){
				hf.setHighestBid(highestBid);
			}
		}
	}

	public void CheckHighestBid(Order o){
		//If the client wants to buy
		if(o.getAction() == Action.SELL)
		{
			for(HistoryFragment h : history)
			{
				//check if it's the good company
				if(h.getCompany() == o.getCompany())
				{
					//check if the price is higher
					if(o.getUnitPrice() > h.getHighestBid()){
						h.setHighestBid(o.getUnitPrice());
					}
				}
			}
		}

	}

	public void CheckLowestAsk(Order o){
		//If the client wants to buy
		if(o.getAction() == Action.BUY)
		{
			for(HistoryFragment h : history)
			{
				//check if it's the good company
				if(h.getCompany() == o.getCompany())
				{
					//check if the price is lower
					if(o.getUnitPrice() < h.getLowestAsk()){
						h.setLowestAsk(o.getUnitPrice());
					}
				}
			}
		}

	}
	
	public double GetLowerAsk(Company c)
	{
		double price = 0.0;
		for(HistoryFragment h : history)
		{
			if(c.name() == h.getCompany().name())
			{
				price = h.getLowestAsk();
			}
		}
		return price;
		
	}
	
	public double GetHighestBid(Company c)
	{
		double price = 0.0;
		for(HistoryFragment h : history)
		{
			if(c.name() == h.getCompany().name())
			{
				price = h.getHighestBid();
			}
		}
		return price;
		
	}

	static public History getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new History();
		}	
		return INSTANCE;

	}
}
