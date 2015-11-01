package edu.poc.hibernate.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.poc.hibernate.entity.Stock;

public class StockRepositoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateStock() {
		StockRepository stockRepository = new StockRepository();
		Stock stock = new Stock();
		stock.setStockCode("471521");
		stock.setStockName("GENM21");
		stockRepository.createStock(stock);
		stock.setStockCode("471532");
		stock.setStockName("GENM32");
		stockRepository.createStock(stock);
		stock.setStockCode("471543");
		stock.setStockName("GENM43");
		stockRepository.createStock(stock);
	}

}
