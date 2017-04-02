package com.ezcode.quantvalley.batch.model;

/**
 * The Class Trade.
 * 
 * @author rdwivedi
 */
public class Trade {

	private String stock;
	private String time;
	private double price;
	private long shares;

	public Trade(String stock, String time, double price, long shares) {
		super();
		this.stock = stock;
		this.time = time;
		this.price = price;
		this.shares = shares;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getShares() {
		return shares;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}

	@Override
	public String toString() {
		return "Trade [stock=" + stock + ", time=" + time + ", price=" + price + ", shares=" + shares + "]";
	}
}
