package edu.poc.io;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FileReaderAgent implements Runnable {

	private volatile AtomicInteger instance = new AtomicInteger();

	@Override
	public void run() {
		System.out.println("Entering>>>" + Thread.currentThread().getName());
		int number = instance.incrementAndGet();
		System.out.println("I am in Run Method : " + number);

		Task task;
		//somehow get the data
		if(number % 2 == 0) {
			task = new Task1();
		}else {
			task = new Task2();
		}
		task.execute();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (number == 5) {
			System.out.println("Some Interesting event is occouring!!!" + number);
			System.out.println("OK");
		}
		System.out.println("Exit>>>" + Thread.currentThread().getName());
	}

	public static void start() {
		ExecutorService pool = Executors.newFixedThreadPool(5);
		FileReaderAgent fileReaderAgent = new FileReaderAgent();

		for (int i = 0; i < 100; i++) {
			pool.execute(fileReaderAgent);
		}

	}

}
