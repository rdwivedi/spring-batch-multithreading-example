package com.ezcode.quantvalley.batch.model;

/**
 * The Class FxMarketEvent.
 * 
 * @author rdwivedi
 */
public class FxMarketEvent {
	
    private String stock;
    private String time;
    private String price;
    private String shares;
    
    public FxMarketEvent() {
    }

	public FxMarketEvent(String stock, String time, String price, String shares) {
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
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getShares() {
		return shares;
	}
	
	public void setShares(String shares) {
		this.shares = shares;
	}

	@Override
	public String toString() {
		return "FxMarketEvent [stock=" + stock + ", time=" + time + ", price=" + price + ", shares=" + shares + "]";
	}
}
