package edu.poc.pattern.utility.exceptionhndler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.poc.pattern.utility.exceptionhndler.GlobalExceptionHandler;

public class GlobalExceptionHandlerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUncaughtException() {
		GlobalExceptionHandler handler = new GlobalExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(handler);
		throw new RuntimeException("Thrown from Main");
	}

}
