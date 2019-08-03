package com.sacc.ComprehensiveSystem;

import com.sacc.ComprehensiveSystem.config.PrimaryDataBaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
