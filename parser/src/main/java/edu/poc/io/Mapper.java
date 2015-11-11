package edu.poc.io;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Mapper {
	public List<String> map(String data) {
		StringTokenizer st = new StringTokenizer(data, ",");
		List<String> datas = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String ele = st.nextToken();
			if (!ele.isEmpty() && ele.contains("a")) {
				datas.add(ele);
			}
		}
		return datas;
	}
}
