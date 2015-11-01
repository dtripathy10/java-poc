package edu.poc.stats.montecarlo.household;

import java.util.ArrayList;
import java.util.List;

public class Intermediate {

    public List<Double> list = new ArrayList<>();
    public int start;

    @Override
    public String toString() {
        return "{start="+start+"size="+list.size()+"}";
    }
    
}
