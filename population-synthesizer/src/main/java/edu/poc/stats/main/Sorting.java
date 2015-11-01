package edu.poc.stats.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sorting {

  List<List<Integer>> largeList = new ArrayList<>();
  List<List<Integer>> finalList = new ArrayList<>();

  public Sorting(String fileName) {
    readData(fileName);
    sort();
    print();
    System.out.println("-----competed---------");
  }

  private void readData(String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String sCurrentLine;
      while ((sCurrentLine = br.readLine()) != null) {
        String[] sep = sCurrentLine.split("\t");
        List<Integer> list_int = new ArrayList<>(sep.length);
        for (int i = 0; i < sep.length; i++) {
          int value = new Integer(sep[i]);
          list_int.add(i, value);
        }
        largeList.add(list_int);
      }
    } catch (IOException e) {
    }
  }

  public static void main(String[] args) {
    Sorting main = new Sorting("final HH+person.txt");
  }

  private void sort() {
    for (int index = 1; index < 186; index++) {
      for (int i = 0; i < largeList.size(); i++) {
        List<Integer> object = (List<Integer>) largeList.get(i);
        if (object.get(index) == 1) {
          finalList.add(object);
        }
      }
    }
  }

  private void print() {
    for (List<Integer> list : finalList) {
      for (Integer integer : list) {
        System.out.print(integer+"\t");
      }
      System.out.println("");
    }
  }
}
