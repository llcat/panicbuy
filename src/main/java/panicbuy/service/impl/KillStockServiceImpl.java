package panicbuy.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import panicbuy.dao.KillSuccessDAO;
import panicbuy.dao.StockDAO;
import panicbuy.dto.Exposer;
import panicbuy.dto.KillExecutionInfo;
import panicbuy.entity.Stock;
import panicbuy.exception.KillClosedException;
import panicbuy.exception.KillStockException;
import panicbuy.exception.RepeatKillException;
import panicbuy.service.KillInfoEnum;
import panicbuy.service.KillStockService;

@Service
public class KillStockServiceImpl implements KillStockService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StockDAO stockdao;
	@Autowired
	private KillSuccessDAO ksdao;
	
	private String salt = "s665ssskkiy$$$$#$s";
	public List<Stock> getStockList() {
		return stockdao.getStockList(0,4);
	}

	public Stock getById(long stockId) {
		return stockdao.queryStockById(stockId);
	}

	public Exposer exposeStartUrl(long stockId) {
		Stock stock = stockdao.queryStockById(stockId);
		if(stock == null){
			return new Exposer.ExposerBuilder(false)
					.stockId(stockId).build();
		}
		Date start = stock.getStartTime();
		Date end = stock.getEndTime();
		Date now = new Date();
		if(now.getTime()<start.getTime()||
				now.getTime()>end.getTime()){
			return new Exposer.ExposerBuilder(false).stockId(stockId)
					.start(start.getTime()).end(end.getTime())
					.now(now.getTime()).build();
		}
		String md5 = getMd5(stockId);
		return new Exposer.ExposerBuilder(true).stockId(stockId)
				.md5(md5).build();
	}

	private String getMd5(long stockId){
		String base = stockId+"/"+salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	@Transactional
	public KillExecutionInfo executeKill(long stockId, long userPhone, String md5)
			throws KillStockException, RepeatKillException, KillClosedException {
		if(md5==null||!md5.equals(getMd5(stockId))){
			throw new KillStockException("md5 validate is false");
		}
		try{
		Date now = new Date();
		int updatecount = stockdao.reduceNumberById(stockId, now);
		if(updatecount<=0){
			//减库存操作没成功，1.库存量为0 2.刚好时间过了
			throw new KillClosedException("kill is closed");
		}else{
			int insertcount = ksdao.insertKillSuccess(stockId, userPhone);
			if(insertcount<=0){
				throw new RepeatKillException("repeat kill action");
			}else{
				KillExecutionInfo kei = new 
						KillExecutionInfo
						(stockId,KillInfoEnum.SUCCESS,ksdao.getKillSuccessWithStockByUserPhone(userPhone));
				return kei;
			}
		}
		}catch(KillClosedException e1){
			throw e1;
		}catch(RepeatKillException e2){
			throw e2;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new KillStockException("Exception in executeKill"+
			e.getMessage());
		}
	}
	
	public static void main(String[] args){
		KillStockServiceImpl kssi = new KillStockServiceImpl();
		System.out.println(kssi.getMd5(1));
	}

}
