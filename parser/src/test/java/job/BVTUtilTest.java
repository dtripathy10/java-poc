package job;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import job.BVTUtil.BVTBalance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BVTUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidate() {

	}

	@Test
	public void testDoValidaton() {
		Map<java.util.Date, BVTBalance> m = new HashMap<java.util.Date, BVTBalance>();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		NavigableMap<java.util.Date, BVTBalance> sortedBVTMap = BVTUtil.sortMap(m);
		List<String> poolTypes = new ArrayList<String>();
		poolTypes.add("GLP");
		poolTypes.add("GIP");
		BVTUtil.doValidaton(sortedBVTMap, poolTypes);
		for (Map.Entry<java.util.Date, BVTBalance> entry : sortedBVTMap.entrySet()) {
			System.out.println(formatter.format(entry.getKey()));
			System.out.println(entry.getValue());
			System.out.println("---------------------");
		}

		//
		m = new HashMap<java.util.Date, BVTBalance>();

		try {
			java.util.Date d1 = formatter.parse("29/08/2009");
			m.put(d1, new BVTBalance("1"));
			d1 = formatter.parse("27/08/2009");
			m.put(d1, new BVTBalance("2"));
			d1 = formatter.parse("26/08/2009");
			m.put(d1, new BVTBalance("3"));
			d1 = formatter.parse("28/08/2009");
			m.put(d1, new BVTBalance("4"));
			d1 = formatter.parse("01/06/2009");
			m.put(d1, new BVTBalance("5"));

			sortedBVTMap = BVTUtil.sortMap(m);

			BVTUtil.doValidaton(sortedBVTMap, poolTypes);
			for (Map.Entry<java.util.Date, BVTBalance> entry : sortedBVTMap.entrySet()) {
				System.out.println(formatter.format(entry.getKey()));
				System.out.println(entry.getValue());
				System.out.println("---------------------");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//
		m = new HashMap<java.util.Date, BVTBalance>();
		try {
			java.util.Date d1 = formatter.parse("27/08/2009");
			m.put(d1, new BVTBalance("1"));
			sortedBVTMap = BVTUtil.sortMap(m);
			BVTUtil.doValidaton(sortedBVTMap, poolTypes);
			for (Map.Entry<java.util.Date, BVTBalance> entry : sortedBVTMap.entrySet()) {
				System.out.println(formatter.format(entry.getKey()));
				System.out.println(entry.getValue());
				System.out.println("---------------++++-----");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSortMap() {
		Map<java.util.Date, BVTBalance> m = new HashMap<java.util.Date, BVTBalance>();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date d1 = formatter.parse("27/08/2010");
			m.put(d1, new BVTBalance("1"));
			d1 = formatter.parse("27/08/2009");
			m.put(d1, new BVTBalance("2"));
			d1 = formatter.parse("27/08/2410");
			m.put(d1, new BVTBalance("3"));
			d1 = formatter.parse("27/08/2610");
			m.put(d1, new BVTBalance("4"));
			d1 = formatter.parse("27/08/2710");
			m.put(d1, new BVTBalance("5"));

			for (Map.Entry<java.util.Date, BVTBalance> entry : BVTUtil.sortMap(m).entrySet()) {
				System.out.println(formatter.format(entry.getKey()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetDateBetween() {
		String str_date = "27/08/2010";
		String end_date = "28/08/2010";

		DateFormat formatter;

		formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			java.util.Date startDate = formatter.parse(str_date);
			java.util.Date endDate = formatter.parse(end_date);
			System.out.println(BVTUtil.getDateBetween(startDate, endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
