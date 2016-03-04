package DistributedSystems;
/**
 * Small part of the history (1 history fragment per company)
 * 
 *
 */
public class HistoryFragment {

	private Company company;
	private double highestBid;
	private double lowestAsk;
	
	public HistoryFragment(Company c, double highestBid, double lowestAsk){
		this.company = c;
		this.highestBid = highestBid;
		this.lowestAsk = lowestAsk;
	}
	
	
	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public double getHighestBid() {
		return highestBid;
	}
	public void setHighestBid(double highestBid) {
		this.highestBid = highestBid;
	}
	public double getLowestAsk() {
		return lowestAsk;
	}
	public void setLowestAsk(double lowestAsk) {
		this.lowestAsk = lowestAsk;
	}
	
	
	
}
