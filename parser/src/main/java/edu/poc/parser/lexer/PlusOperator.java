package edu.poc.parser.lexer;
public class PlusOperator extends Operator {

	public Token left;
	public Token right;
	
	@Override
	public String toString() {
		return "{\"operator\" : +}";
	}

}
