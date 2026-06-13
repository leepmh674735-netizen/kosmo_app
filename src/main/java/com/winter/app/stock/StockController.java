package com.winter.app.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.winter.app.test.Test;

@RestController
@RequestMapping("/stock")
public class StockController {

	private final Test test;
	
	private final StockService stockService;

	public StockController(Test test, StockService stockService) {
		this.test = test;
		this.stockService = stockService;
	}

	@GetMapping("/marketPrice")
	public String getMarketPrice() throws Exception {

		return stockService.getMemberPrice();
	}
}