package edu.poc.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParse() {
		Parser parser = new Parser();
		parser.parse(getSourceFile());
	}

	private char[] getSourceFile() {
		String source = "32 + 35 + 53";
		return source.toCharArray();
	}

}
