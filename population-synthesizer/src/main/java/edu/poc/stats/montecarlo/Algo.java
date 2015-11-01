package edu.poc.stats.montecarlo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.poc.stats.montecarlo.household.Intermediate;

public class Algo {

    PrintStream hh_writer, person_writer;
    //internal ds
    Hashtable<Integer, Intermediate> internal = new Hashtable<>(8);
    //data structure for household and population
    private int[][] hhid = new int[2][5];
    private int[][] ptype = new int[3][5];
    // data structure
    int[][] ipumatrx = new int[8][2 + 3];
    double[] prob_cumulativeList = new double[8];
    double[] household_target_list = new double[2];
    double[] person_target_list = new double[3];
    //temp
    double[] temp_person_target_list = new double[3];
    public Algo(String ipu_matrix, String hhtype, String persontype, String prob, String tgt, String person_target) throws IOException {
        //file writer for house and population
        File file = new File("household.csv");
        FileOutputStream fis = new FileOutputStream(file);
        hh_writer = new PrintStream(fis);
        hh_writer.println("houseHoldid\thousehold_type\tHhsize\tC\tW\tM");
        File file1 = new File("population.csv");
        FileOutputStream fis1 = new FileOutputStream(file1);
        person_writer = new PrintStream(fis1);
        person_writer.println("personId\thousehold_type\tG\tW\tL\tAM");
        //reading files and storing into ds
        readipuMatrix(ipu_matrix);
        readhhidFile(hhtype);
        readptypeidFile(persontype);
        readprob_cumulativeList(prob);
        readTarget_list(tgt);
        readPersonTarget(person_target);
        preProcess();
        //run montecarlo
        monteCarlo();
        //close resources
        hh_writer.close();
        fis.close();
        person_writer.close();
        fis1.close();
        printHouseholdTargetFile();
        System.out.println("Completed..........");
    }

    private void readipuMatrix(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;
            int row = 0;
            int column = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] sep = sCurrentLine.split(",");
                for (int i = 0; i < sep.length; i++) {
                    int value = new Integer(sep[i]);
                    ipumatrx[row][column] = value;
                    column++;
                }
                column = 0;
                row++;
            }
        } catch (IOException e) {
        }
    }

    private void readhhidFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;
            int row = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] sep = sCurrentLine.split(",");
                int column = 0;
                for (int i = 0; i < sep.length; i++) {
                    int string = new Integer(sep[i]);
                    hhid[row][column] = string;
                    column++;
                }
                column = 0;
                row++;
            }
        } catch (IOException e) {
        }
    }

    private void readptypeidFile(String persontype) {
        try (BufferedReader br = new BufferedReader(new FileReader(persontype))) {
            String sCurrentLine;
            int row = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] sep = sCurrentLine.split(",");
                int column = 0;
                for (int i = 0; i < sep.length; i++) {
                    int string = new Integer(sep[i]);
                    ptype[row][column] = string;
                    column++;
                }
                column = 0;
                row++;
            }
        } catch (IOException e) {
        }
    }

    private void readTarget_list(String tgt) {
        try (BufferedReader br = new BufferedReader(new FileReader(tgt))) {
            String sCurrentLine;
            int row = 0;
            if ((sCurrentLine = br.readLine()) != null) {
                String[] sep = sCurrentLine.split(",");
                for (int i = 0; i < sep.length; i++) {
                    double string = new Double(sep[i]);
                    household_target_list[i] = string;
                }
            }
        } catch (IOException e) {
        }

    }

    private void readprob_cumulativeList(String prob) {
        try (BufferedReader br = new BufferedReader(new FileReader(prob))) {
            String sCurrentLine;
            int row = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                double string = new Double(sCurrentLine.trim());
                prob_cumulativeList[row] = string;
                row++;
            }
        } catch (IOException e) {
        }
    }

    private void preProcess() {
        int i = 0;
        int start = 0;
        int type = 1;
        List<Double> temp = new ArrayList<>();
        for (double dseseseseses : prob_cumulativeList) {
            i++;
            temp.add(dseseseseses);
            if (dseseseseses == 1) {
                Intermediate inter = new Intermediate();
                inter.start = start;
                for (Double double1 : temp) {
                    inter.list.add(double1);
                }
                internal.put(type, inter);
                //initialised
                type++;
                start = i;
                temp = new ArrayList<>();
            }
        }
    }

    private void monteCarlo() {
        for (Map.Entry<Integer, Intermediate> entry : internal.entrySet()) {
            Integer integer = entry.getKey();
            Intermediate intermediate = entry.getValue();
            int start = intermediate.start;

            Random randomGenerator = new Random();
            for (int i = 0; i < household_target_list[integer - 1]; i++) {
                double randomNumber = randomGenerator.nextDouble();
                List<Double> cum = intermediate.list;
                if (numberPresentBetween(0, cum.get(0), randomNumber)) {
                    //print the value according to need
                    prettyPrint(start, integer);
                }
                for (int j = 1; j < cum.size(); j++) {
                    if (numberPresentBetween(cum.get(j - 1), cum.get(j), randomNumber)) {
                        //print the value according to need
                        prettyPrint(start + j, integer);
                    } else {
                    }
                }

            }
        }
    }

    private boolean numberPresentBetween(double i, Double get, double randomNumber) {
        if ((randomNumber >= i) && (randomNumber < get)) {
            return true;
        }
        return false;
    }
    int houseHoldid = 1;
    long personid = 1;

    private void prettyPrint(int row, int type) {
        int[] list = ipumatrx[row];
        printInhhFile(type);
        for (int i = 2; i < 2 + 3; i++) {
            int temp = list[i];
            if (temp != 0) {
                printpopulationFile(i - 1, temp, type);
                double value = temp_person_target_list[i - 2] - temp;
                temp_person_target_list[i - 2] = value;
            }
        }
    }

    private void printInhhFile(int type) {
        type = type - 1;
        //get the hhtytpe
        hh_writer.print(houseHoldid + "\t");
        hh_writer.print((type + 1) + "\t");
        hh_writer.print(hhid[type][1] + "\t");
        hh_writer.print(hhid[type][2] + "\t");
        hh_writer.print(hhid[type][3] + "\t");
        hh_writer.print(hhid[type][4] + "\t");
        hh_writer.print("\n");
        houseHoldid++;

    }

    private void printpopulationFile(int type, int temp, int hhtype) {
        type = type - 1;
        //get the persontype
        for (int i = 0; i < temp; i++) {
            person_writer.print(personid + "\t");
            person_writer.print(houseHoldid - 1 + "\t");
            person_writer.print(ptype[type][1] + "\t");
            person_writer.print(ptype[type][2] + "\t");
            person_writer.print(ptype[type][3] + "\t");
            person_writer.print(ptype[type][4] + "\t");
            person_writer.print("\n");
            personid++;
        }

    }

    private void readPersonTarget(String person_target) {
        try (BufferedReader br = new BufferedReader(new FileReader(person_target))) {
            String sCurrentLine;
            int row = 0;
            if ((sCurrentLine = br.readLine()) != null) {
                String[] sep = sCurrentLine.split(",");
                for (int i = 0; i < sep.length; i++) {
                    double string = new Double(sep[i]);
                    person_target_list[i] = string;
                    temp_person_target_list[i] = string;
                }
            }
        } catch (IOException e) {
        }

    }

    

    private void printStatistics() {
        double positiveSum = 0;
        double negativeSum = 0;
        for (double is : temp_person_target_list) {
            if (is <= 0) {
                negativeSum += is;
            } else {
                positiveSum += is;
            }
        }
        System.out.println("Positive sum\t" + positiveSum);
        System.out.println("Negative Sum \t" + negativeSum);
        System.out.println("Total sim\t" + (positiveSum + negativeSum));
    }

    private void printHouseholdTargetFile() {
        for (double is : temp_person_target_list) {
            System.out.print(is+"\t");
        }
    }
}
