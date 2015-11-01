package edu.poc.parser.lexer;
public class IntegerToken extends Token {
	private Integer lexeme;

	public Integer getLexeme() {
		return lexeme;
	}

	public void setLexeme(Integer lexeme) {
		this.lexeme = lexeme;
	}

	@Override
	public String toString() {
		return "{<constnt,int> = " + lexeme + "}";
	}

	
}
