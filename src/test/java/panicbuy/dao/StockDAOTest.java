package panicbuy.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import panicbuy.entity.Stock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig/spring-DAO.xml")
public class StockDAOTest {
	
	@Autowired
	private StockDAO stockdao;
	@Test
	public void testReduceNumberById() {
		int flag = stockdao.reduceNumberById(4, new Date());
		System.out.println(flag);
	}

	@Test
	public void testQueryStockById() {
		Stock stock = stockdao.queryStockById(1);
		System.out.println(stock.getName());
	}

	@Test
	public void testGetStockList() {
		List<Stock> list = stockdao.getStockList(0, 4);
		for(Stock s : list)
			System.out.println(s);
	}

}
