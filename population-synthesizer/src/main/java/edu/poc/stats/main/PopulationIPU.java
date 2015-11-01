
package edu.poc.stats.main;

import edu.poc.stats.population.io.DataModel;

public class PopulationIPU {

    public static void main(String[] args) {
        DataModel dm = new DataModel("input/population/sample_data.csv","input/population/target_data.csv");
        dm.printData();
        dm.printStatisticalData();
    }
}
