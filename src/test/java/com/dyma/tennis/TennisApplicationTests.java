package com.dyma.tennis;

import com.dyma.tennis.repository.HealthCheckRepository;
import com.dyma.tennis.service.HealthCheckService;
import com.dyma.tennis.web.HealthCheckController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TennisApplicationTests {

	@Autowired
	private HealthCheckRepository healthCheckRepository;

	@Autowired
	private HealthCheckService healthCheckService;

	@Autowired
	private HealthCheckController healthCheckController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(healthCheckRepository);
		Assertions.assertNotNull(healthCheckController);
		Assertions.assertNotNull(healthCheckService);
	}

}
