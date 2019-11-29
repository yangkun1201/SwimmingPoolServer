package com.tzc.wsc.SwimmingPoolManagementSystemServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SwimmingPoolManagementSystemServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwimmingPoolManagementSystemServerApplication.class, args);

	}

}
