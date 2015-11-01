package edu.poc.stats.ipu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Processing {
    /////file

    FileWriter outFile;
    PrintWriter out;
    public static int NO_OF_TYPE = 5;
    public static int NO_OF_ROW = 8;
    public static double[] constraint;
    public static double[][] data;

    public Processing(String sampleData, String targetData) {
        try {
            outFile = new FileWriter("result.csv");
            out = new PrintWriter(outFile);
        } catch (IOException ex) {
        }

        readData(sampleData, targetData);
        Table table = new Table(data);
        processData();
       // prettyPrinting();
        System.out.println("-----competed---------");
    }

    private void readData(String sampleData, String targetData) {
        //storing constraint data
        constraint = new double[NO_OF_TYPE];
        int index1 = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(targetData))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] sep = sCurrentLine.split(",");
                for (String string : sep) {
                    constraint[index1] = new Double(string);
                    index1++;
                }
            }
        } catch (IOException e) {
        }
        //storing data data
        data = new double[NO_OF_ROW][NO_OF_TYPE];
        int column = 0;
        int row = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(sampleData))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] sep = sCurrentLine.split(",");
                for (String string : sep) {
                    data[row][column] = new Double(string);
                    column++;
                }
                row++;
                column = 0;
            }
        } catch (IOException e) {
        }
    }

    private void processData() {
    }

    private void prettyPrinting() {
        for (int i = 0; i < data.length; i++) {
            double[] d = data[i];
            for (int j = 0; j < d.length; j++) {
                double e = d[j];
                System.out.print(e + "\t");
            }
            System.out.println("");
        }
    }
}
