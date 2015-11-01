package edu.poc.parser;

import java.util.ArrayList;
import java.util.List;

import edu.poc.parser.lexer.IntegerToken;
import edu.poc.parser.lexer.PlusOperator;
import edu.poc.parser.lexer.Token;
import edu.poc.parser.lexer.Tokenizer;

public class Parser {

	public void parse(char[] sourceFile) {
		List<Object> stack = new ArrayList<Object>();

		Tokenizer tokenizer = new Tokenizer(sourceFile);

		Expression exp = new Expression();
		stack.add(exp);
		while (tokenizer.hasNext()) {
			Token token = tokenizer.next();
			// stack.add(token);

			if (token instanceof PlusOperator) {
				PlusOperator plusToken = (PlusOperator) token;
				Object pop = stack.get(stack.size() - 1);
				if (pop instanceof IntegerToken) {
					// remove from stack
					stack.remove(stack.size() - 1);
					plusToken.left = (Token) pop;
				}
				pop = stack.get(stack.size() - 1);

				if (pop instanceof Expression) {
					Expression lexp = (Expression) pop;
					if (token == null) {
						System.exit(0);
					}
					lexp.operator = (PlusOperator) token;
					stack.add(token);
				}
			}

			if (token instanceof IntegerToken) {
				IntegerToken integerToken = (IntegerToken) token;
				Object pop = stack.get(stack.size() - 1);
				if (pop instanceof PlusOperator) {
					PlusOperator poperator = (PlusOperator) pop;
					stack.remove(stack.size() - 1);
					poperator.right = (Token) integerToken;
				} else {
					stack.add(token);
				}
			}
			System.out.println(token);
		}
		System.out.println(stack.size());
		for (int i = 0; i < stack.size(); i++) {
			PlusOperator expre = (PlusOperator) stack.get(1);
			System.out.println("+++++++++++++++++++++++++++++++++");
			System.out.println(((PlusOperator) expre).left);
			System.out.println(" + ");
			System.out.println(((PlusOperator) expre).right);

			System.out.println("+++++++++++++++++++++++++++++++++");

		}

	}
}
