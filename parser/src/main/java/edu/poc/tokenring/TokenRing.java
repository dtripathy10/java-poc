package edu.poc.tokenring;

import java.util.ArrayList;
import java.util.List;

public class TokenRing {

	public static void main(String[] args) {
		List<Agent> agentList = new ArrayList<Agent>();

		for (int i = 0; i < 10; i++) {
			agentList.add(Agent.startAgenet("" + i + ""));
		}

		int counter = 0;
		while (true) {
			agentList.get(counter % 10).gotToken("Token : " + "ID");
			counter++;
		}

	}

}
