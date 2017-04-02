package com.ezcode.quantvalley.batch.model;

/**
 * The Class StockPriceDetails.
 *
 * @author rdwivedi
 */
public class StockVolume {

	private String stock;
	private long volume;

	public StockVolume(String stock, long volume) {
		super();
		this.stock = stock;
		this.volume = volume;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "StockVolume [stock=" + stock + ", volume=" + volume + "]";
	}

}
