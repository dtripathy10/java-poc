package edu.poc.struts2.action;

public class WelcomeUserAction {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String execute() {
		return "SUCCESS";
	}
}
