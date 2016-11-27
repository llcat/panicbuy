package panicbuy.service;

public enum KillInfoEnum {

	SUCCESS(1,"秒杀成功"),
	END(0,"秒杀结束"),
	REREAT_KILL(-1,"重复秒杀"),
	INNER_ERROR(-2,"系统异常"),
	DATA_MODIFIED(-3,"验证信息被修改");
	
	 private int state;
	 private String statinfo;
	 
	private KillInfoEnum(int state, String statinfo) {
		this.state = state;
		this.statinfo = statinfo;
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
	 * @return the statinfo
	 */
	public String getStatinfo() {
		return statinfo;
	}

	/**
	 * @param statinfo the statinfo to set
	 */
	public void setStatinfo(String statinfo) {
		this.statinfo = statinfo;
	}
	
	public static KillInfoEnum stateof(int index){
		for(KillInfoEnum e :values()){
			if(e.getState()==index){
				return e;
			}
		}
		return null;
	}
	 
}
