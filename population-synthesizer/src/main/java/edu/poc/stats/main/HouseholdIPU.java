
package edu.poc.stats.main;

import edu.poc.stats.household.io.DataModel;

public class HouseholdIPU {

    public static void main(String[] args) {
        DataModel dm = new DataModel("input/household/sample_data.csv","input/household/target_data.csv");
        dm.printStatisticalData();
        dm.printMatrix();
    }
}
