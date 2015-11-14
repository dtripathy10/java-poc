package edu.poc.tokenring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class is thread safe.
 */
public class Parser {

	public static void main(String... strings) throws IOException {
		ArrayList k = new ArrayList();
		k.add(1);
		k.add(0, 2);
		System.out.println(k);

	}

	private File file;

	public synchronized void setFile(File f) {
		file = f;
	}

	public synchronized File getFile() {
		return file;
	}

	public String getContent() throws IOException {
		String output = "";
		try (FileInputStream i = new FileInputStream(file)) {
			int data;
			while ((data = i.read()) > 0) {
				output += (char) data;
				System.out.println((char) data);

			}
		}
		return output;
	}

	public String getContentWithoutUnicode() throws IOException {
		String output = "";
		try (FileInputStream i = new FileInputStream(file)) {

			int data;
			while ((data = i.read()) > 0) {
				if (data < 0x80) {
					output += (char) data;
				}
			}
		}
		return output;
	}

	public void saveContent(String content) throws IOException {
		try (FileOutputStream o = new FileOutputStream(file);) {
			for (int i = 0; i < content.length(); i += 1) {
				o.write(content.charAt(i));
			}
		}
	}
}