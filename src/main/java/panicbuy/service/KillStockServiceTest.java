package panicbuy.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import panicbuy.dto.Exposer;
import panicbuy.dto.KillExecutionInfo;
import panicbuy.entity.Stock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:springconfig/spring-DAO.xml",
	"classpath:springconfig/spring-service.xml"
})
public class KillStockServiceTest {

	@Autowired
	private KillStockService kss;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testGetStockList() {
		List<Stock> list = kss.getStockList();
		logger.info("info{}", list);
	}

	@Test
	public void testGetById() {
		Stock stock = kss.getById(1);
		logger.info("stock{}",stock);
	}

	@Test
	public void testExposeStartUrl() {
		Exposer exposer = kss.exposeStartUrl(0);
		logger.info("exposer{}",exposer);
		Exposer exposer1 = kss.exposeStartUrl(1);
		logger.info("exposer{}",exposer1);
		Exposer exposer2 = kss.exposeStartUrl(4);
		logger.info("exposer{}",exposer2);
	}

	@Test
	public void testExecuteKill() {
		Exposer exposer = kss.exposeStartUrl(4);
		try{
		KillExecutionInfo kei = kss.executeKill(4, 1736537899, exposer.getMd5());
		logger.info("kei{}", kei);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}
