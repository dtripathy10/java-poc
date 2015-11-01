package edu.poc.parser.lexer;

import java.util.Iterator;

public class Tokenizer implements Iterator<Token> {

	private int position;
	private Token pastToken;
	private Token curToken;

	private char[] source;

	public Tokenizer(char[] source) {
		this.source = source;

	}

	private int getSourceSize() {
		return source.length;
	}

	public boolean hasNext() {
		findNextToken();
		if (curToken == null) {
			return false;
		}
		return true;
	}

	private void findNextToken() {

		if (position >= getSourceSize()) {
			pastToken = curToken;
			curToken = null;
		}

		StringBuilder bufferToken = new StringBuilder();
		int entryPosition = position;

		char curChar;

		// skip space character
		while (isSpace(curChar = source[position])) {
			position += 1;
			if (position >= getSourceSize()) {
				pastToken = curToken;
				curToken = null;
				return;
			}
		}
		while (isDigit(curChar = source[position])) {
			bufferToken.append(Character.toString(curChar));
			Token token = convertDigitToToken(bufferToken.toString());
			pastToken = curToken;
			curToken = token;
			position += 1;
			if (position >= getSourceSize()) {
				pastToken = curToken;
				curToken = null;
				return;
			}

		}

		while (isOperator(curChar = source[position])) {
			Token token = convertPlusOperatorToToken(source[position]);
			pastToken = curToken;
			curToken = token;
			position += 1;
			if (position >= getSourceSize()) {
				pastToken = curToken;
				curToken = null;
				return;
			}
		}

		if (position == entryPosition) {
			position += 1;
			findNextToken();
		}
	}

	private Token convertPlusOperatorToToken(char c) {
		return new PlusOperator();
	}

	private boolean isOperator(char c) {
		if (c == '+') {
			return true;
		}
		return false;
	}

	private boolean isSpace(char c) {
		if (c == ' ') {
			return true;
		}
		return false;
	}

	private Token convertDigitToToken(String bufferToken) {
		Integer intToken = new Integer(bufferToken);
		IntegerToken token = new IntegerToken();
		token.setLexeme(intToken);
		return token;
	}

	private boolean isDigit(char c) {
		if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8'
				|| c == '9') {
			return true;
		}
		return false;
	}

	public Token next() {
		return curToken;
	}

	public Token getPastToken() {
		return pastToken;
	}
}
