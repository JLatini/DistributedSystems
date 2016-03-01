package DistributedSystems;

import java.util.ArrayList;

public class History {

	private static History INSTANCE;
	private ArrayList<HistoryFragment> history = new ArrayList<HistoryFragment>();

	private History(){
		history.add(new HistoryFragment(Company.AAPL,0,0));
		history.add(new HistoryFragment(Company.IBM,0,0));
		history.add(new HistoryFragment(Company.MSFT,0,0));
		history.add(new HistoryFragment(Company.ORCL,0,0));
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

	static public History getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new History();
		}	
		return INSTANCE;

	}
}
