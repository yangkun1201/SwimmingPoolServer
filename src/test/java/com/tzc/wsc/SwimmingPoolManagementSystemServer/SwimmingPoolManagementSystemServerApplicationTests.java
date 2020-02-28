package com.tzc.wsc.SwimmingPoolManagementSystemServer;

import com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo.User;
import com.tzc.wsc.SwimmingPoolManagementSystemServer.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SwimmingPoolManagementSystemServerApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void test(){
		String picName = "a.jpg";
		picName = String.format("%d_%s",System.currentTimeMillis(),picName);
		System.out.print(picName);
	}


}
