package edu.poc.stats.population.io;

import static edu.poc.stats.population.io.TYPE.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataModel {

   //person array
   List<Person> persons = new ArrayList<>();
   /////file
   FileWriter outFile;
   PrintWriter out;
///////////////////////////////////////////////////////////////////////////////////////////////
   //sample data (4 dimensional array)
   private double[][][][] sampleData = new double[GENDER][WORKER][AGE][EDUCATION];
   //sample data average
   private double[] avg_sample_gender = new double[GENDER];
   private double[] avg_sample_education = new double[EDUCATION];
   private double[] avg_sample_karma = new double[WORKER];
   private double[] avg_sample_age = new double[AGE];
   //target data average
   private double[] avg_target_gender = new double[GENDER];
   private double[] avg_target_education = new double[EDUCATION];
   private double[] avg_target_karma = new double[WORKER];
   private double[] avg_target_age = new double[AGE];
/////////////////////////////////////////////////////////////////////////////////////////////////
   //copy sample data (4 dimensional array) for temporary operation
   private double[][][][] temp_sampleData = new double[GENDER][WORKER][AGE][EDUCATION];
   //sample data average
   private double[] temp_avg_sample_gender = new double[GENDER];
   private double[] temp_avg_sample_education = new double[EDUCATION];
   private double[] temp_avg_sample_karma = new double[WORKER];
   private double[] temp_avg_sample_age = new double[AGE];

   //////////////////////////////////////////////////////////////////////////////////////////////////
   //constructor
   public DataModel(String sampleData, String targetData) {
      try {
         /*reading data  and initialise it into following data:-
          * 1-sample data (4 dimensional array)
          * 2-sample data average
          * 3-target data average
          */
         outFile = new FileWriter("output/population/result.csv");
         out = new PrintWriter(outFile);
      } catch (IOException ex) {
         Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
      }

      readData(sampleData, targetData);
      copyData();
      processData();
      saveToArray();
      //suffle the persons array
      Collections.shuffle(persons);
      normalPrinting();
      //prettyPrinting();
      closeResources();
      //printStatisticalData();
      System.out.println("-----competed---------");
   }

   private void processData() {
      for (int counter = 0; counter < 150000; counter++) {
         /////////////////////////////----------Gender-----------////////////////////////
         //calculating gender factor
         double[] genderfactor = new double[GENDER];
         temp_avg_sample_gender = new double[GENDER];
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     //gender gender
                     temp_avg_sample_gender[i] = temp_avg_sample_gender[i] + temp_sampleData[i][j][k][l];
                  }
               }

            }
         }
         for (int i = 0; i < GENDER; i++) {
            genderfactor[i] = avg_target_gender[i] / temp_avg_sample_gender[i];
         }
         //multiply the propertion factor
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     temp_sampleData[i][j][k][l] = temp_sampleData[i][j][k][l] * genderfactor[i];
                  }
               }

            }
         }

         /////////////////////////////----------Age-----------////////////////////////
         //calculating age factor
         double[] agefactor = new double[AGE];
         temp_avg_sample_age = new double[AGE];
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     //gender age
                     temp_avg_sample_age[k] = temp_avg_sample_age[k] + temp_sampleData[i][j][k][l];
                  }
               }

            }
         }
         for (int i = 0; i < AGE; i++) {
            agefactor[i] = avg_target_age[i] / temp_avg_sample_age[i];
         }
         //multiply the propertion factor
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     temp_sampleData[i][j][k][l] = temp_sampleData[i][j][k][l] * agefactor[k];
                  }
               }

            }
         }
         ////////////////////////////////////////////////////////////////////////////
         /////////////////////////////----------Karma-----------////////////////////////
         //calculating karma factor
         double[] karmafactor = new double[WORKER];
         temp_avg_sample_karma = new double[WORKER];
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     //gender karma
                     temp_avg_sample_karma[j] = temp_avg_sample_karma[j] + temp_sampleData[i][j][k][l];
                  }
               }

            }
         }
         for (int i = 0; i < WORKER; i++) {
            karmafactor[i] = avg_target_karma[i] / temp_avg_sample_karma[i];
         }
         //multiply the propertion factor
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     temp_sampleData[i][j][k][l] = temp_sampleData[i][j][k][l] * karmafactor[j];
                  }
               }

            }
         }

         ////////////////////////////////////////////////////////////
         /////////////////////////////----------Education-----------////////////////////////
         //calculating education factor
         double[] educationfactor = new double[EDUCATION];
         temp_avg_sample_education = new double[EDUCATION];
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     //education
                     temp_avg_sample_education[l] = temp_avg_sample_education[l] + temp_sampleData[i][j][k][l];
                  }
               }

            }
         }
         for (int i = 0; i < EDUCATION; i++) {
            educationfactor[i] = avg_target_education[i] / temp_avg_sample_education[i];
         }
         //multiply the propertion factor
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     temp_sampleData[i][j][k][l] = temp_sampleData[i][j][k][l] * educationfactor[l];
                  }
               }

            }
         }
      }

   }

   public void printData() {
       int kk=0;
      for (int i = 0; i < temp_sampleData.length; i++) {
         double[][][] dseses = temp_sampleData[i];
         for (int j = 0; j < dseses.length; j++) {
            double[][] dses = dseses[j];
            for (int k = 0; k < dses.length; k++) {
               double[] ds = dses[k];
               for (int l = 0; l < ds.length; l++) {
                  double d = ds[l];
                  
                  printMale(i, j, k, l, d);
                  kk++;
                  if(kk == 4) {
                      System.out.println("");
                      kk = 0;
                  }
               }
            }

         }
      }
   }
   boolean check = false;

   //printing for male
   private void printMale(int i, int j, int k, int l, double d) {
      //System.out.println("data[" + i + "][" + j + "][" + k + "][" + l + "]\t" + d);
      System.out.print(d + "\t");
   }

   public void printStatisticalData() {
      temp_avg_sample_gender = new double[GENDER];
      temp_avg_sample_karma = new double[WORKER];
      temp_avg_sample_education = new double[EDUCATION];
      temp_avg_sample_age = new double[AGE];
      for (int i = 0; i < GENDER; i++) {
         for (int j = 0; j < WORKER; j++) {
            for (int k = 0; k < AGE; k++) {
               for (int l = 0; l < EDUCATION; l++) {
                  //gender sum
                  temp_avg_sample_gender[i] = temp_avg_sample_gender[i] + temp_sampleData[i][j][k][l];
                  //gender karma
                  temp_avg_sample_karma[j] = temp_avg_sample_karma[j] + temp_sampleData[i][j][k][l];
                  //gender education
                  temp_avg_sample_education[l] = temp_avg_sample_education[l] + temp_sampleData[i][j][k][l];
                  //gender age
                  temp_avg_sample_age[k] = temp_avg_sample_age[k] + temp_sampleData[i][j][k][l];
               }
            }

         }
      }
      System.out.println("----------Sample average Data-------------------------");
      System.out.println("Total Number of Male\t" + c(temp_avg_sample_gender[0]));
      System.out.println("Total Number of FeMale\t" + c(temp_avg_sample_gender[1]));
      System.out.println("Total Number of Literate\t" + c(temp_avg_sample_education[0]));
      System.out.println("Total Number of IlLiterate\t" + c(temp_avg_sample_education[1]));
      System.out.println("Total Number of Worker\t" + c(temp_avg_sample_karma[0]));
      System.out.println("Total Number of Non worker\t" + c(temp_avg_sample_karma[1]));
      for (double d : temp_avg_sample_age) {
         System.out.println("Age group _ serial\t" + c(d));
      }

   }

   private String c(double ldouble) {
      return ((new BigDecimal(Double.toString(ldouble))).toPlainString());
   }

   private void readData(String sampleData, String targetData) {

      //storing target data
      double[] temp1 = new double[GENDER + EDUCATION + WORKER + AGE];
      int index1 = 0;
      try (BufferedReader br = new BufferedReader(new FileReader(targetData))) {

         String sCurrentLine;

         while ((sCurrentLine = br.readLine()) != null) {
            String[] sep = sCurrentLine.split(",");
            for (String string : sep) {
               temp1[index1] = new Double(string);
              
               index1++;
            }
         }
         index1 = 0;
         //reading gender
         for (int i = 0; i < GENDER; i++) {
            avg_target_gender[i] = temp1[index1];
            index1++;
         }
         //reading education
         for (int i = 0; i < EDUCATION; i++) {
            avg_target_education[i] = temp1[index1];
            index1++;
         }
         //reading karma
         for (int i = 0; i < WORKER; i++) {
            avg_target_karma[i] = temp1[index1];
            index1++;
         }

         //reading age
         for (int i = 0; i < AGE; i++) {
            avg_target_age[i] = temp1[index1];
            index1++;
         }

      } catch (IOException e) {
      }




      //sampledata processing
      double[] temp = new double[GENDER * EDUCATION * WORKER * AGE];
      int index = 0;
      try (BufferedReader br = new BufferedReader(new FileReader(sampleData))) {

         String sCurrentLine;

         while ((sCurrentLine = br.readLine()) != null) {
            String[] sep = sCurrentLine.split(",");
            for (String string : sep) {
               temp[index] = new Double(string);
               index++;
            }
         }
         index = 0;
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     this.sampleData[i][j][k][l] = temp[index];
                     index++;
                  }
               }

            }
         }
         temp = null;
         for (int i = 0; i < GENDER; i++) {
            for (int j = 0; j < WORKER; j++) {
               for (int k = 0; k < AGE; k++) {
                  for (int l = 0; l < EDUCATION; l++) {
                     //gender sum
                     avg_sample_gender[i] = avg_sample_gender[i] + this.sampleData[i][j][k][l];
                     //gender karma
                     avg_sample_karma[j] = avg_sample_karma[j] + this.sampleData[i][j][k][l];
                     //gender education
                     avg_sample_education[l] = avg_sample_education[l] + this.sampleData[i][j][k][l];
                     //gender age
                     avg_sample_age[k] = avg_sample_age[k] + this.sampleData[i][j][k][l];
                  }
               }

            }
         }


      } catch (IOException e) {
      }
   }

   private void copyData() {
      for (int i = 0; i < GENDER; i++) {
         for (int j = 0; j < WORKER; j++) {
            for (int k = 0; k < AGE; k++) {
               System.arraycopy(this.sampleData[i][j][k], 0, this.temp_sampleData[i][j][k], 0, EDUCATION);
            }

         }
      }
      System.arraycopy(avg_sample_gender, 0, temp_avg_sample_gender, 0, GENDER);
      System.arraycopy(avg_sample_education, 0, temp_avg_sample_education, 0, EDUCATION);
      System.arraycopy(avg_sample_karma, 0, temp_avg_sample_karma, 0, WORKER);
      System.arraycopy(avg_sample_age, 0, temp_avg_sample_age, 0, AGE);

   }

   private void prettyPrinting() {
      for (int i = 0; i < GENDER; i++) {
         for (int j = 0; j < WORKER; j++) {
            for (int k = 0; k < AGE; k++) {
               for (int l = 0; l < EDUCATION; l++) {
                  long data = Math.round(temp_sampleData[i][j][k][l]);
                  print(i, j, k, l, data);
               }
            }

         }
      }
   }
   boolean header = false;

   private void print(int i, int j, int k, int l, long data) {
      if (!header) {
         out.println("GENDER,KARMA,AGE,EDUCATION");
         header = true;
      }
      for (int m = 0; m < data; m++) {
         //male
         switch (i) {
            case 0:
               out.print("male,");
               break;
            case 1:
               out.print("female,");
               break;
         }
         //karma
         switch (j) {
            case 0:
               out.print("worker,");
               break;
            case 1:
               out.print("non-worker,");
               break;
         }
         //age
         switch (k) {
            case 0:
               out.print("0_9,");
               break;
            case 1:
               out.print("10_14,");
               break;
            case 2:
               out.print("15_19,");
               break;
            case 3:
               out.print("20_24,");
               break;
            case 4:
               out.print("25_29,");
               break;
            case 5:
               out.print("30_34,");
               break;
            case 6:
               out.print("35_39,");
               break;
            case 7:
               out.print("40_44,");
               break;
            case 8:
               out.print("45_49,");
               break;
            case 9:
               out.print("50_54,");
               break;
            case 10:
               out.print("55_59,");
               break;
            case 11:
               out.print("60_100,");
               break;
           }
         //education
         switch (l) {
            case 0:
               out.println("literate");
               break;
            case 1:
               out.println("illetrate");
               break;
         }

      }

   }

   private void closeResources() {
      out.close();
   }

   private void saveToArray() {
      for (int i = 0; i < GENDER; i++) {
         for (int j = 0; j < WORKER; j++) {
            for (int k = 0; k < AGE; k++) {
               for (int l = 0; l < EDUCATION; l++) {
                  long data = Math.round(temp_sampleData[i][j][k][l]);
                  print1(i, j, k, l, data);
               }
            }

         }
      }
   }

   private void print1(int i, int j, int k, int l, long data) {

      for (int m = 0; m < data; m++) {
         Person p = new Person();
         //male
         switch (i) {
            case 0:
               p.gender = "male";
               break;
            case 1:
               p.gender = "female";
               break;
         }
         //karma
         switch (j) {
            case 0:
               p.karma = "worker";
               break;
            case 1:
               p.karma = "non-worker";
               break;
         }
         //age
         switch (k) {
            case 0:
               p.age = "0_9";
               break;
            case 1:
               p.age = "10_14";
               break;
            case 2:
               p.age = "15_19";
               break;
            case 3:
               p.age = "20_24";
               break;
            case 4:
               p.age = "25_29";
               break;
            case 5:
               p.age = "30_34";
               break;
            case 6:
               p.age = "35_39";
               break;
            case 7:
               p.age = "40_44";
               break;
            case 8:
               p.age = "45_49";
               break;
            case 9:
               p.age = "50_54";
               break;
            case 10:
               p.age = "55_59";
               break;
            case 11:
               p.age = "60_100";
               break;
           }
         //education
         switch (l) {
            case 0:
               p.education = "literate";
               break;
            case 1:
               p.education = "illetrate";
               break;
         }
         persons.add(p);
      }

   }

   private void normalPrinting() {
      out.println("Gender,Education,Karma,Age");
      for (Person person : persons) {
         out.print(person.gender + ",");
         out.print(person.education + ",");
         out.print(person.karma + ",");
         out.print(person.age + "\n");

      }
   }
}
