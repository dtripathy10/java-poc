package edu.poc.pattern.utility.exceptionhndler;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Throwable: " + e.getMessage());
		System.out.println(t.toString());
	}
}
