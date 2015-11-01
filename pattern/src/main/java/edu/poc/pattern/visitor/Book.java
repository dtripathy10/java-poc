package edu.poc.pattern.visitor;

public class Book implements Visitable {

	
	public void accept(Visitor visitor) {
		System.out.println("Book Visitable Item!!");
		visitor.visit(this);
	}

}
