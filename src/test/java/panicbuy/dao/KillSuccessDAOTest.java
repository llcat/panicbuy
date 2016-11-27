package panicbuy.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import panicbuy.entity.KillSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig/spring-DAO.xml")
public class KillSuccessDAOTest {
	@Autowired
	private KillSuccessDAO ksdao;
	@Autowired
	private ComboPooledDataSource cp ;
	@Test
	public void testInsertKillSuccess() {
		int flag = ksdao.insertKillSuccess(1, 156291286);
		System.out.println(flag);
	}
	

	@Test
	public void testGetKillSuccessWithStockByUserPhone() {
		KillSuccess ks = ksdao.getKillSuccessWithStockByUserPhone(156291286);
		System.out.println(ks);
	}
	
	@Test
	public void testDatabaseConnecttion(){
		try{
		Connection conn =cp.getConnection();
		PreparedStatement ptst =conn.prepareStatement("Select * from stock;");
		ResultSet rs = ptst.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString(2));
		}
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
