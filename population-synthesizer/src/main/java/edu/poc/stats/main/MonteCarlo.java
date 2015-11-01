
package edu.poc.stats.main;

import java.io.IOException;

import edu.poc.stats.montecarlo.Algo;

public class MonteCarlo {

    public static void main(String[] args) throws IOException {
        String ipu_matrix= "data/ipu_sheed.csv";
        String hhtype= "data/hhtype.csv";
        String persontype= "data/persontype.csv";
        String prob= "data/ipu_probabilities.csv";
        String tgt= "data/target.csv";
        String person_tgt= "data/person_target.csv";
        Algo algo = new Algo(ipu_matrix,hhtype,persontype,prob,tgt,person_tgt);
    }
}
