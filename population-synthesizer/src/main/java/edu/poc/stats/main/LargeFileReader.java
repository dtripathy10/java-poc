package edu.poc.stats.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LargeFileReader {

	private int currentIndex;
	public int sum;

	public LargeFileReader(String fileName) {
		readData(fileName);
		System.out.println("-----competed---------");
	}

	private void readData(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String sCurrentLine;
			currentIndex = 1;
			List<String[]> largeList = new ArrayList<>();
			while ((sCurrentLine = br.readLine()) != null) {
				String[] sep = sCurrentLine.split("\t");
				// for (int i = 0; i < sep.length-1; i++) {
				// String string = sep[i];
				// System.out.print(string + ",");
				// }
				// System.out.println(sep[sep.length-1]);
				if (new Integer(sep[0]) != currentIndex) {
					processLisr(largeList);
					largeList = new ArrayList<>();
					largeList.add(sep);
					currentIndex = new Integer(sep[0]);
				} else {
					largeList.add(sep);
				}
			}
			processLisr(largeList);
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		LargeFileReader main = new LargeFileReader("MANICOMBINATION.txt");
		System.out.println("\nSum\t" + main.sum);
	}

	private void processLisr(List<String[]> largeList) {
		int size = 0;
		for (String[] sep : largeList) {
			size = sep.length;
			break;
		}
		int[] arr = new int[size - 2];
		for (String[] sep : largeList) {
			for (int i = 2; i < sep.length; i++) {
				String string = sep[i];
				Integer value = new Integer(string);
				sum += value;
				arr[i - 2] = arr[i - 2] + value;
			}
		}
		System.out.print(currentIndex + "\t");
		for (int i = 0; i < arr.length; i++) {
			int j = arr[i];
			System.out.print(j + "\t");
		}
		System.out.println("");
	}
}
