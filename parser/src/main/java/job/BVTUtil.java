package job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class BVTUtil {

	/**
	 * @description Container object to capture BVT balance data of a given
	 *              date, and a semantic validation flag of the given balance
	 *              data
	 *
	 */
	static class BVTBalance {

		String data; // BVT balance data
		boolean valid; // semantic validation flag

		public BVTBalance(String data, boolean valid) {
			super();
			this.data = data;
			this.valid = valid;
		}

		public BVTBalance(String data) {
			this(data, false);
		}

		public String getData() {
			return data;
		}

		public boolean isValid() {
			return valid;
		}

		public void setValid(boolean valid) {
			this.valid = valid;
		}

		/**
		 * @logic set @param valid according to given validation :-
		 *        <ul>
		 *        <li>For Liquidity Pool Value Dated Balance is mandatory</li>
		 *        <li>For Interest Pool Interest Bearing Balance is mandatory</li>
		 *        </ul>
		 * @param poolTypes
		 *            contains GLP and/or GIP
		 */
		public boolean validate(List<String> poolTypes) {
			if (!data.equalsIgnoreCase("7")) {
				this.valid = true;
			}
			return this.valid;
		}

		@Override
		public String toString() {
			return "BVTData [data=" + data + ", valid=" + valid + "]";
		}

	}

	/**
	 * @logic BVT balance map contains balance according to date wise, and it is
	 *        sorted in descending order. If one balance record failed its
	 *        validation, all the descending balance map will be failed its
	 *        validation.
	 * 
	 * @param sortedBVTMap
	 *            sorted in descending order by date
	 * @param poolTypes
	 *            contains GLP and/or GIP
	 */
	public static void doValidaton(NavigableMap<java.util.Date, BVTBalance> sortedBVTMap, List<String> poolTypes) {

		if (sortedBVTMap.isEmpty()) {
			// no BVT record found
			return;
		}
		java.util.Date fromDate = sortedBVTMap.lastKey(); // sorted in
															// descending order
		java.util.Date toDate = sortedBVTMap.firstKey();

		List<java.util.Date> dateBetweens = getDateBetween(fromDate, toDate);
		for (java.util.Date date : dateBetweens) {
			BVTBalance bvtData = sortedBVTMap.get(date);
			if (bvtData == null) {
				// missing date, hence after all descendant will not be valid
				break;
			}
			boolean isValid = bvtData.validate(poolTypes);
			if (!isValid) {
				// all descendant record will not be valid
				break;
			}
		}

	}

	/**
	 * @param unsortedMap
	 *            contains balance data with key as date. Data is not present is
	 *            sorted order
	 * @return sorted balance map in descending order according to date
	 */
	public static NavigableMap<java.util.Date, BVTBalance> sortMap(Map<java.util.Date, BVTBalance> unsortedMap) {
		NavigableMap<java.util.Date, BVTBalance> sortedMap = new TreeMap<java.util.Date, BVTBalance>(
				new Comparator<java.util.Date>() {
					@Override
					public int compare(java.util.Date a, java.util.Date b) {
						return -a.compareTo(b);
					}
				});
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}

	/**
	 * @logic generate a date between fromDate to endDate with inclusion of both
	 *        dates
	 * @param fromDate
	 * @param toDate
	 * @constraint fromDate <= toDate
	 * @return list of date between fromDate to endDate with inclusion of both
	 *         dates.
	 */
	public static List<java.util.Date> getDateBetween(java.util.Date fromDate, java.util.Date toDate) {
		List<java.util.Date> dates = new ArrayList<java.util.Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate);
		dates.add(toDate);
		while (cal.getTime().after(fromDate)) {
			cal.add(Calendar.DATE, -1);
			dates.add(cal.getTime());
		}
		return dates;
	}

}
