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
		User user = User.builder()
				.username("小明")
				.phone("13589377824")
				.password("123456")
				.gender(0)
				.type(0)
				.build();
		userRepository.save(user);
	}


}
