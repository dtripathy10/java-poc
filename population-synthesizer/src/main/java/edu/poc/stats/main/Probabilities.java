package edu.poc.stats.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.poc.stats.sorting.Temp;

public class Probabilities {

    List<List<Integer>> ipumatrx = new ArrayList<>();
    List<Double> ipuList = new ArrayList<>();
    List<Integer> probabilitiesList = new ArrayList<>();
    List<Temp> tempList = new ArrayList<>();
    List<Double> cumulativeList = new ArrayList<>();
    List<Double> prob_cumulativeList = new ArrayList<>();

    public Probabilities(String fileName1, String fileName2) {
        readData(fileName1);
        readTarget(fileName2);
        process();
        calculate();
        calculate_fianl();
        print();
        System.out.println("-----competed---------");
    }

    private void readData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] sep = sCurrentLine.split(",");
                List<Integer> list_int = new ArrayList<>(sep.length);
                for (int i = 0; i < sep.length; i++) {
                    int value = new Integer(sep[i]);
                    list_int.add(i, value);
                }
                ipumatrx.add(list_int);
            }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        Probabilities main = new Probabilities("input.csv", "ipuweights.csv");
    }

    private void process() {
        int end = -1;
        int currentColumn = 0;

        for (int i = 0; i < ipumatrx.size(); i++) {

            List<Integer> object = (List<Integer>) ipumatrx.get(i);
            if (object.get(currentColumn) != 1) {
                Temp temp = new Temp();
                temp.type = currentColumn;
                temp.end = end;
                tempList.add(temp);
                currentColumn += 1;
            }
            end += 1;
        }
        Temp temp = new Temp();
        temp.type = currentColumn;
        temp.end = end;
        tempList.add(temp);

    }

    private void readTarget(String fileName2) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName2))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                double value = new Double(sCurrentLine.trim());
                ipuList.add(value);
            }
        } catch (IOException e) {
        }
    }

    private void print() {
        for (double list : prob_cumulativeList) {
            System.out.println(list);
        }
    }

    private void calculate() {
        int start = 0;
        for (int i = 0; i < tempList.size(); i++) {
            Temp temp = tempList.get(i);
            int end = temp.end;
            for (int j = start; j <= end; j++) {
                double value = ipuList.get(j);
                if (j != start) {
                    value += cumulativeList.get(j - 1);
                }
                cumulativeList.add(value);
            }
            start = end + 1;
        }
    }

    private void calculate_fianl() {
        int start = 0;
        for (int i = 0; i < tempList.size(); i++) {
            Temp temp = tempList.get(i);
            int end = temp.end;
            for (int j = start; j <= end; j++) {
                double value = cumulativeList.get(j) / cumulativeList.get(end);
                prob_cumulativeList.add(value);
            }
            start = end + 1;
        }
    }
}
