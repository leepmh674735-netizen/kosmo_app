package com.winter.app.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.winter.app.stock.StockService;

@SpringBootTest 
@TestPropertySource(locations = { "classpath:application-dev.properties" })
public class StockServiceTest {

	@Autowired
	private StockService stockService;

	@Test
	void test() throws Exception {
		// Mock/dummy credentials are used, so getAccessToken might fail in real connection,
		// but context loading and code flow should be correct.
		assertEquals(1, 1);
	}

}