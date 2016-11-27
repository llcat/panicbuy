package panicbuy.dao;
import org.apache.ibatis.annotations.Param;

import panicbuy.entity.KillSuccess;

public interface KillSuccessDAO {
	/*
	 * 插入商品id和预订电话号码
	 * return:
	 * 返回插入记录条数，1条或者是0条。
	 */
	int insertKillSuccess(@Param("killStockId")long killStockId,@Param("userPhone")long userPhone);
	
	KillSuccess getKillSuccessWithStockByUserPhone(long userPhone);
}