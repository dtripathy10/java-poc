package edu.poc.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VisistorpatternTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Book book = new Book();

		Pen pen = new Pen();

		List<Visitable> visitableItems = new ArrayList<Visitable>();
		visitableItems.add(book);
		visitableItems.add(pen);

		Visitor visitor = new TimepassVisitor();

		for (Visitable visitable : visitableItems) {
			visitable.accept(visitor);
		}
	}

}
