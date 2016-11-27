package panicbuy.service;

import java.util.List;

import panicbuy.dto.Exposer;
import panicbuy.dto.KillExecutionInfo;
import panicbuy.entity.Stock;
import panicbuy.exception.KillClosedException;
import panicbuy.exception.KillStockException;
import panicbuy.exception.RepeatKillException;

/*
 * 三个方面去设计服务接口
 * 方法定义的粒度
 * 合理的参数
 * 合理的返回类型
 */
public interface KillStockService {
	/**
	 * 查询参与活动的所有商品
	 * @return 
	 */
	List<Stock> getStockList();
	/**
	 * 根据商品id查询商品信息
	 * @param stockId
	 * @return
	 */
	Stock getById(long stockId);
	
	/**
	 * 当符合条件时才暴露秒杀接口，其实客户端会进行两次请求
	 * 第一次请求取得md5的值，在次请求executeKill才会进行实际秒杀操作
	 * 目的是为了防止有人提前猜出秒杀的接口url，使用程序进行模拟请求
	 * @param stockId
	 */
	Exposer exposeStartUrl(long stockId);
	
	/**
	 * @param stockId
	 * @param userPhone		
	 * @param md5    执行秒杀前要验证秒杀接口是否成功暴露
	 * @return	执行秒杀操作后返回的信息，秒杀成功，还是进行了重复秒杀，或者是商品
	 * 库存不足
	 * 根据不同返回情况抛出异常
	 */
	KillExecutionInfo executeKill(long stockId,long userPhone,String md5)
			throws KillStockException,RepeatKillException,KillClosedException;
	
	
}
