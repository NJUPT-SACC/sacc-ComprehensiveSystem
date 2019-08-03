package com.sacc.comprehensivesystem;

import com.sacc.comprehensivesystem.config.PrimaryDataBaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComprehensiveSystemApplicationTests {

	@Resource
	PrimaryDataBaseConfig config;

	@Test
	public void contextLoads() {
		System.out.println(config);
	}

}
