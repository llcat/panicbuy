/**
 * 
 */
package panicbuy.dto;

/**
 * 
 * @author ypl
 *
 */
public class Exposer {
    //是否暴露秒杀接口的标志位
	private final boolean exposed;
	//用于验证的md5字符串
	private final String md5;
	
	private final long stockId;
	//当前系统时间
	private final long now;
	//秒杀开始时间
	private final long start;
	//秒杀结束时间
	private final long end;
	//使用构建器，需要传输数据内容的选项由使用者决定
	private Exposer(ExposerBuilder eb){
		this.exposed = eb.exposed;
		this.stockId = eb.stockId;
		this.md5 = eb.md5;
		this.now = eb.now;
		this.start = eb.start;
		this.end = eb.end;
	}
	
	/**
	 * 
	 * @author ypl
	 *使用构建器给Exposer这个数据传输对象
	 *赋值，用以应对不同的业务场景，需要使用的值有
	 *具体业务需求决定，减少重叠构建器，保证线程安全
	 *并方便使用。
	 */
	public static class ExposerBuilder{
		private final boolean exposed;
		private long stockId = 0;
		private String md5 = null;
		private long now = 0;
		private long start = 0;
		private long end = 0;
		public ExposerBuilder(boolean exposed){
			this.exposed = exposed ;
		}
		public ExposerBuilder stockId(long val){
			stockId = val;
			return this;
		}
		public ExposerBuilder md5(String val){
			md5 = val;
			return this;
		}
		public ExposerBuilder now(long val){
			now = val;
			return this;
		}
		public ExposerBuilder start(long val){
			start = val;
			return this;
		}
		public ExposerBuilder end(long val){
			end = val;
			return this;
		}
		//构建方法
		public Exposer build(){
			return new Exposer(this);
		}
	}
	
	/**
	 * @return the stockId
	 */
	public long getStockId() {
		return stockId;
	}
	
	/**
	 * @return the exposed
	 */
	public boolean isExposed() {
		return exposed;
	}

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @return the now
	 */
	public long getNow() {
		return now;
	}

	/**
	 * @return the start
	 */
	public long getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public long getEnd() {
		return end;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exposer [exposed=" + exposed + ", md5=" + md5 + ", stockId=" + stockId + ", now=" + now + ", start="
				+ start + ", end=" + end + "]";
	}

}
