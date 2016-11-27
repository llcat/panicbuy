package panicbuy.entity;

public class KillSuccess {
	private long killStockId;
	private long userPhone;
	private Stock stock;
	public long getKillStockId() {
		return killStockId;
	}
	public void setKillStockId(long killStockId) {
		this.killStockId = killStockId;
	}
	public long getUerPhone() {
		return userPhone;
	}
	public void setUerPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "KillSuccess "
				+ "[killStockId=" + killStockId + 
				", uerPhone=" + userPhone + 
				", stock=" + stock + "]";
	}
	
	
}