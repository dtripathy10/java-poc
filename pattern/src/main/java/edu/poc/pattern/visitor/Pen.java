package edu.poc.pattern.visitor;

public class Pen implements Visitable {

	public void accept(Visitor visitor) {
		System.out.println("Pen Visitable Item!!");
		visitor.visit(this);
	}

}
