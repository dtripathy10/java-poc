package edu.poc.pattern.visitor;

public interface Visitable {
	public void accept(Visitor visitor);
}
