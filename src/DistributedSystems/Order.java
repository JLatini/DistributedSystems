package DistributedSystems;

public class Order {
	
	private Company company;
	private Action action;
	private int quantity;
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
	
	// Quantity
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	public Order(Action action, Company company, int quantity, double unitPrice, String emitter) {
		setCompany(company);
		setAction(action);
		setQuantity(quantity);
		setUnitPrice(unitPrice);
		setEmitter(emitter);
	}
	
	@Override
	public String toString() {
		return getAction() + " - " + getCompany() + " - " + getQuantity() + " - " + getUnitPrice() + " - " + getEmitter();
	}
}