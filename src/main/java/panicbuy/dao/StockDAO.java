package panicbuy.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import panicbuy.entity.Stock;

public interface StockDAO {
	
	/*
	 * 根据商品id减少库存数量
	 */
	int reduceNumberById(@Param("stockId")long stockId,@Param("killTime")Date killTime);
	
	Stock queryStockById(long stockId);
	//根据偏移量返回一个List<Stock>,便于多商品的分页操作，现在我们只有4件
	List<Stock> getStockList(@Param("offset")int offset,@Param("rowcount")int rowcount);
	
}