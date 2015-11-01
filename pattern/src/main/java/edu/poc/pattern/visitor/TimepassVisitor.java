package edu.poc.pattern.visitor;

public class TimepassVisitor implements Visitor{

	public void visit(Visitable visitable) {
		System.out.println(visitable);
	}

}
