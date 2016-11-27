package panicbuy.dto;

import panicbuy.entity.KillSuccess;
import panicbuy.service.KillInfoEnum;

/**
 * @author ypl
 * 作为秒杀操作结束后返回给客户端的信息
 *
 */
public class KillExecutionInfo {
	private long stockId;
	private int state;
	private String stateInfo;
	private KillSuccess killsuc;
	
	//成功执行
	public KillExecutionInfo(long stockId,KillInfoEnum kie, KillSuccess killsuc) {
		this.stockId = stockId;
		this.state = kie.getState();
		this.stateInfo = kie.getStatinfo();
		this.killsuc = killsuc;
	}
	
	//执行失败
	public KillExecutionInfo(long stockId,KillInfoEnum kie ) {
		this.stockId = stockId;
		this.state = kie.getState();
		this.stateInfo = kie.getStatinfo();
	}



	/**
	 * @return the stockId
	 */
	public long getStockId() {
		return stockId;
	}
	/**
	 * @param stockId the stockId to set
	 */
	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return the stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}
	/**
	 * @param stateInfo the stateInfo to set
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	/**
	 * @return the killsuc
	 */
	public KillSuccess getKillsuc() {
		return killsuc;
	}
	/**
	 * @param killsuc the killsuc to set
	 */
	public void setKillsuc(KillSuccess killsuc) {
		this.killsuc = killsuc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KillExecutionInfo [stockId=" + stockId + ", state=" + state + ", stateInfo=" + stateInfo + ", killsuc="
				+ killsuc + "]";
	}
	
}
