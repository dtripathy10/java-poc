package edu.poc.tokenring;

public class Agent implements Runnable {

	private String data;
	private String tokenID = "";

	public Agent(String data) {
		this.data = data;
	}

	public void gotToken(String token) {
		// I am the owner, I will process the token now
		this.tokenID = token;
		while (!tokenID.isEmpty()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static Agent startAgenet(String data) {
		Agent agent = new Agent(data);
		Thread thread = new Thread(agent);
		thread.start();
		return agent;
	}

	@Override
	public void run() {
		while (true) {
			if (!tokenID.isEmpty()) {
				// do process
				System.out.println("data : " + data + " , Do processing token");
				GlobalData.numberOfAccess++;
				System.out.println("GlobalData.numberOfAccess : " + GlobalData.numberOfAccess);
				passToken();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void passToken() {
		this.tokenID = "";
	}
}
