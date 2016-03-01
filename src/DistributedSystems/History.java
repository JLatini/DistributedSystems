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
	
	static public History getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new History();
		}	
		return INSTANCE;
		
	}
}
