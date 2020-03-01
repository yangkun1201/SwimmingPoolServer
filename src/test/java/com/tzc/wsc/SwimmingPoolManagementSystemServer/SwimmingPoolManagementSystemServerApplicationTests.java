package com.tzc.wsc.SwimmingPoolManagementSystemServer;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.ExchangeRecordsRepository;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SwimmingPoolManagementSystemServerApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExchangeRecordsRepository exchangeRecordsRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void test(){
		List<Object[]> data = exchangeRecordsRepository.getExchangeRecordsByUserId(1,0,8);
		for(Object[] item:data){
			System.out.print(item[0]+" ");
			System.out.print(item[1]+" ");
			System.out.print(item[2]+" ");
			System.out.print(item[3]+" ");
			System.out.println();
		}

	}


}
