package edu.poc.stats.ipu;

public class Table {

    double[][] table;
    int numberOfColumn;
    int numberOfRow;
    double[] columnSum;
    double[] weight;
    //Constructor
    double[][] transpose;
    double[] constraint;

    public Table(double[][] data) {
        transpose = data;
        table = new double[data[0].length][data.length];
        numberOfColumn = data[0].length;
        numberOfRow = data.length;
        for (int i = 0; i < data.length; i++) {
            double[] ds = data[i];
            for (int j = 0; j < ds.length; j++) {
                double d = ds[j];
                table[j][i] = d;
            }
        }
        columnSum = new double[numberOfColumn];
        for (int i = 0; i < table.length; i++) {
            double[] ds = table[i];
            double sum = 0;
            for (int j = 0; j < ds.length; j++) {
                double d = ds[j];
                sum += d;
            }
            columnSum[i] = sum;
        }
        constraint = new double[Processing.constraint.length];
        System.arraycopy(Processing.constraint, 0, constraint, 0, Processing.constraint.length);
        initialiseWeight();
        process();
        prettyPrinting();
    }

    private void prettyPrinting() {
//        for (int i = 0; i < table.length; i++) {
//            double[] d = table[i];
//            for (int j = 0; j < d.length; j++) {
//                double e = d[j];
//                System.out.print(e + "\t");
//            }
//            System.out.println("");
//        }
        System.out.println("\n----------------------------");
        System.out.println("Column Sum");
        System.out.println("----------------------------");
        for (int i = 0; i < columnSum.length; i++) {
            System.out.print(columnSum[i] + "\n");
        }
        System.out.println("\n----------------------------");
        System.out.println("Row Sum");
        System.out.println("----------------------------");
        for (int i = 0; i < weight.length; i++) {
            System.out.print(weight[i] + "\n");
        }
        System.out.println("");
    }

    private void initialiseWeight() {
        weight = new double[numberOfRow];
        for (int i = 0; i < weight.length; i++) {
            weight[i] = 1;
        }
    }

    private void process() {
//    //start iteration
//        for (int i = 0; i < 1000; i++) {
//            iterate();
//            System.out.println("Iteration number\t"+i);
//          System.out.println("nu"+numberOfColumn);
//        }
        iterate();
        //System.out.println("Iteration number\t" + 1);
        for (int i = 0; i < columnSum.length; i++) {
            System.out.print(columnSum[i] + "\t");
        }
        System.out.println("");
        int iterationNumber = 2;
        while (converge()) {
            iterate();
            //System.out.println("Iteration number\t" + iterationNumber);
            for (int i = 0; i < columnSum.length; i++) {
                System.out.print(columnSum[i] + "\t");
            }
            System.out.println("");
            iterationNumber++;

        }
    }

    private void updateColumnSum() {
        for (int i = 0; i < table.length; i++) {
            double[] ds = table[i];
            double sum = 0;
            for (int j = 0; j < ds.length; j++) {
                double d = ds[j] * weight[j];
                sum += d;
            }
            columnSum[i] = sum;
        }
    }

    private void iterate() {
        for (int i = 0; i < columnSum.length; i++) {
            double _weight = constraint[i] / columnSum[i];
            //System.out.println("Weight\t" + _weight);
            for (int j = 0; j < numberOfRow; j++) {
                if (table[i][j] == 0) {
                } else {
                    weight[j] = weight[j] * _weight;
                }
            }
            updateColumnSum();
        }
    }

    private boolean converge() {
        for (int i = 0; i < numberOfColumn; i++) {
            double result = constraint[i] - columnSum[i];
            if (result < 0) {
                result = -result;
            }
            if (!(result <= 0.0)) {
                return true;
            }
        }
        return false;
    }
}
