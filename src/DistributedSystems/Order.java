package DistributedSystems;
/**
 * Class used to store the data of buy and sell orders
 * 
 *
 */
public class Order {
	
	private Company company;
	private Action action;
	private double unitPrice;
	private String emitter;

	
	// Company
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	// Action
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	// Unit price
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	// Emitter
	public String getEmitter() {
		return emitter;
	}
	public void setEmitter(String emitter) {
		this.emitter = emitter;
	}
	
	public Order(Action action, Company company, double unitPrice, String emitter) {
		setCompany(company);
		setAction(action);
		setUnitPrice(unitPrice);
		setEmitter(emitter);
	}
	
	@Override
	public String toString() {
		return getEmitter() + ";" + getAction() + ";" + getCompany() + ";" + getUnitPrice();
	}
}