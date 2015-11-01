package edu.poc.stats.household.io;

import static edu.poc.stats.household.io.TYPE.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataModel {

	double sum = 0;
	// ///file
	FileWriter outFile;
	PrintWriter out;
	// /////////////////////////////////////////////////////////////////////////////////////////////
	// sample data (7 dimensional array)
	private double[][][][][][][] sampleData = new double[MARRIED][WORKER][HHOWNERSHIP][VEHICLEOWNERSHIP][HHSIZE][NUMBEROFROOM][LESSTHANFIVE];
	// sample data average
	private double[] avg_sample_married = new double[MARRIED];
	private double[] avg_sample_worker = new double[WORKER];
	private double[] avg_sample_hhownership = new double[HHOWNERSHIP];
	private double[] avg_sample_vehicleownership = new double[VEHICLEOWNERSHIP];
	private double[] avg_sample_hhsize = new double[HHSIZE];
	private double[] avg_sample_numberofroom = new double[NUMBEROFROOM];
	private double[] avg_sample_lessthanfive = new double[LESSTHANFIVE];
	// target data average
	private double[] avg_target_married = new double[MARRIED];
	private double[] avg_target_worker = new double[WORKER];
	private double[] avg_target_hhownership = new double[HHOWNERSHIP];
	private double[] avg_target_vehicleownership = new double[VEHICLEOWNERSHIP];
	private double[] avg_target_hhsize = new double[HHSIZE];
	private double[] avg_target_numberofroom = new double[NUMBEROFROOM];
	private double[] avg_target_lessthanfive = new double[LESSTHANFIVE];
	// ///////////////////////////////////////////////////////////////////////////////////////////////
	// copy sample data (7 dimensional array) for temporary operation
	private double[][][][][][][] temp_sampleData = new double[MARRIED][WORKER][HHOWNERSHIP][VEHICLEOWNERSHIP][HHSIZE][NUMBEROFROOM][LESSTHANFIVE];
	// sample data average
	private double[] temp_avg_sample_married = new double[MARRIED];
	private double[] temp_avg_sample_worker = new double[WORKER];
	private double[] temp_avg_sample_hhownership = new double[HHOWNERSHIP];
	private double[] temp_avg_sample_vehicleownership = new double[VEHICLEOWNERSHIP];
	private double[] temp_avg_sample_hhsize = new double[HHSIZE];
	private double[] temp_avg_sample_numberofroom = new double[NUMBEROFROOM];
	private double[] temp_avg_sample_lessthanfive = new double[LESSTHANFIVE];

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// constructor
	public DataModel(String sampleData, String targetData) {
		try {
			/*
			 * reading data and initialise it into following data:- 1-sample
			 * data (7 dimensional array) 2-sample data average 3-target data
			 * average
			 */
			outFile = new FileWriter("output/household/result.csv");
			out = new PrintWriter(outFile);
		} catch (IOException ex) {
			Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
		}

		readData(sampleData, targetData);
		copyData();
		processData();
		prettyPrinting();
		sum();
		System.out.println("sum \t" + sum);
		closeResources();
		printStatisticalData();
		System.out.println("-----competed---------");
	}

	private void processData() {
		for (int counter = 0; counter < 1000; counter++) {
			// ///////////////////////////----------MARRIED-----------////////////////////////
			// calculating marriage factor
			double[] marriagefactor = new double[MARRIED];
			temp_avg_sample_married = new double[MARRIED];
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// marriage factor
										temp_avg_sample_married[i] = temp_avg_sample_married[i]
												+ temp_sampleData[i][j][k][l][m][n][o];
									}
								}
							}

						}
					}

				}
			}
			for (int i = 0; i < MARRIED; i++) {
				marriagefactor[i] = avg_target_married[i] / temp_avg_sample_married[i];
			}
			// multiply the propertion factor
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// marriage factor
										temp_sampleData[i][j][k][l][m][n][o] = temp_sampleData[i][j][k][l][m][n][o]
												* marriagefactor[i];
									}
								}
							}

						}
					}

				}
			}

			// ///////////////////////////----------WORKER-----------////////////////////////
			// calculating worker factor
			double[] workerfactor = new double[WORKER];
			temp_avg_sample_worker = new double[WORKER];
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// worker factor
										temp_avg_sample_worker[j] = temp_avg_sample_worker[j]
												+ temp_sampleData[i][j][k][l][m][n][o];
									}
								}
							}

						}
					}

				}
			}
			for (int i = 0; i < WORKER; i++) {
				workerfactor[i] = avg_target_worker[i] / temp_avg_sample_worker[i];
			}
			// multiply the propertion factor
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// worker factor
										temp_sampleData[i][j][k][l][m][n][o] = temp_sampleData[i][j][k][l][m][n][o]
												* workerfactor[j];
									}
								}
							}

						}
					}

				}
			}
			// ///////////////////////////----------HHSIZE-----------////////////////////////
			// calculating HHSIZE factor
			double[] hhsizefactor = new double[HHSIZE];
			temp_avg_sample_hhsize = new double[HHSIZE];
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// HHSIZE factor
										temp_avg_sample_hhsize[m] = temp_avg_sample_hhsize[m]
												+ temp_sampleData[i][j][k][l][m][n][o];
									}
								}
							}

						}
					}

				}
			}
			for (int i = 0; i < HHSIZE; i++) {
				hhsizefactor[i] = avg_target_hhsize[i] / temp_avg_sample_hhsize[i];
			}
			// multiply the propertion factor
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// HHSIZE factor
										temp_sampleData[i][j][k][l][m][n][o] = temp_sampleData[i][j][k][l][m][n][o]
												* hhsizefactor[m];
									}
								}
							}

						}
					}

				}
			}
			// ///////////////////////////----------HHOWNERSHIP-----------////////////////////////
			// calculating [HHOWNERSHIP] factor
			double[] hhownershipfactor = new double[HHOWNERSHIP];
			temp_avg_sample_hhownership = new double[HHOWNERSHIP];
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// HHOWNERSHIP factor
										temp_avg_sample_hhownership[k] = temp_avg_sample_hhownership[k]
												+ temp_sampleData[i][j][k][l][m][n][o];
									}
								}
							}

						}
					}

				}
			}
			for (int i = 0; i < HHOWNERSHIP; i++) {
				hhownershipfactor[i] = avg_target_hhownership[i] / temp_avg_sample_hhownership[i];
			}
			// multiply the propertion factor
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// HHOWNERSHIP factor
										temp_sampleData[i][j][k][l][m][n][o] = temp_sampleData[i][j][k][l][m][n][o]
												* hhownershipfactor[k];
									}
								}
							}

						}
					}

				}
			}
			// ///////////////////////////----------NUMBEROFROOM-----------////////////////////////
			// calculating NUMBEROFROOM factor
			double[] numberofroomfactor = new double[NUMBEROFROOM];
			temp_avg_sample_numberofroom = new double[NUMBEROFROOM];
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// NUMBEROFROOM factor
										temp_avg_sample_numberofroom[n] = temp_avg_sample_numberofroom[n]
												+ temp_sampleData[i][j][k][l][m][n][o];
									}
								}
							}

						}
					}

				}
			}
			for (int i = 0; i < NUMBEROFROOM; i++) {
				numberofroomfactor[i] = avg_target_numberofroom[i] / temp_avg_sample_numberofroom[i];
			}
			// multiply the propertion factor
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// NUMBEROFROOM factor
										temp_sampleData[i][j][k][l][m][n][o] = temp_sampleData[i][j][k][l][m][n][o]
												* numberofroomfactor[n];
									}
								}
							}

						}
					}

				}
			}
			// ///////////////////////////----------VEHICLEOWNERSHIP-----------////////////////////////
			// calculating VEHICLEOWNERSHIP factor
			double[] vehicleownershipfactor = new double[VEHICLEOWNERSHIP];
			temp_avg_sample_vehicleownership = new double[VEHICLEOWNERSHIP];
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// VEHICLEOWNERSHIP factor
										temp_avg_sample_vehicleownership[l] = temp_avg_sample_vehicleownership[l]
												+ temp_sampleData[i][j][k][l][m][n][o];
									}
								}
							}

						}
					}

				}
			}
			for (int i = 0; i < VEHICLEOWNERSHIP; i++) {
				vehicleownershipfactor[i] = avg_target_vehicleownership[i] / temp_avg_sample_vehicleownership[i];
			}
			// multiply the propertion factor
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// VEHICLEOWNERSHIP factor
										temp_sampleData[i][j][k][l][m][n][o] = temp_sampleData[i][j][k][l][m][n][o]
												* vehicleownershipfactor[l];
									}
								}
							}

						}
					}

				}
			}

			// ///////////////////////////----------LESSTHANFIVE-----------////////////////////////
			// calculating LESSTHANFIVE factor
			double[] lessthanfivefactor = new double[LESSTHANFIVE];
			temp_avg_sample_lessthanfive = new double[LESSTHANFIVE];
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// LESSTHANFIVE factor
										temp_avg_sample_lessthanfive[o] = temp_avg_sample_lessthanfive[o]
												+ temp_sampleData[i][j][k][l][m][n][o];
									}
								}
							}

						}
					}

				}
			}
			for (int i = 0; i < LESSTHANFIVE; i++) {
				lessthanfivefactor[i] = avg_target_lessthanfive[i] / temp_avg_sample_lessthanfive[i];
			}
			// multiply the propertion factor
			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// LESSTHANFIVE factor
										temp_sampleData[i][j][k][l][m][n][o] = temp_sampleData[i][j][k][l][m][n][o]
												* lessthanfivefactor[o];
									}
								}
							}

						}
					}

				}
			}

		}

	}

	boolean check = false;
	long column = 1;

	public void printMatrix() {
		for (int i = 0; i < MARRIED; i++) {
			for (int j = 0; j < WORKER; j++) {
				for (int k = 0; k < HHOWNERSHIP; k++) {
					for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
						for (int m = 0; m < HHSIZE; m++) {
							for (int n = 0; n < NUMBEROFROOM; n++) {
								for (int o = 0; o < LESSTHANFIVE; o++) {
									System.out.print(temp_sampleData[i][j][k][l][m][n][o]);
									if (column != 144) {
										System.out.print(",");
										column++;

									} else {
										column = 1;
										System.out.print("\n");
									}

								}
							}
						}

					}
				}
			}
		}
	}

	public void printStatisticalData() {
		temp_avg_sample_married = new double[MARRIED];
		temp_avg_sample_worker = new double[WORKER];
		temp_avg_sample_hhownership = new double[HHOWNERSHIP];
		temp_avg_sample_vehicleownership = new double[VEHICLEOWNERSHIP];
		temp_avg_sample_hhsize = new double[HHSIZE];
		temp_avg_sample_numberofroom = new double[NUMBEROFROOM];
		temp_avg_sample_lessthanfive = new double[LESSTHANFIVE];
		for (int i = 0; i < MARRIED; i++) {
			for (int j = 0; j < WORKER; j++) {
				for (int k = 0; k < HHOWNERSHIP; k++) {
					for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
						for (int m = 0; m < HHSIZE; m++) {
							for (int n = 0; n < NUMBEROFROOM; n++) {
								for (int o = 0; o < LESSTHANFIVE; o++) {
									// gender MARRIED
									temp_avg_sample_married[i] = temp_avg_sample_married[i]
											+ temp_sampleData[i][j][k][l][m][n][o];
									// gender WORKER
									temp_avg_sample_worker[j] = temp_avg_sample_worker[j]
											+ temp_sampleData[i][j][k][l][m][n][o];
									// gender HHOWNERSHIP
									temp_avg_sample_hhownership[k] = temp_avg_sample_hhownership[k]
											+ temp_sampleData[i][j][k][l][m][n][o];
									// gender VEHICLEOWNERSHIP
									temp_avg_sample_vehicleownership[l] = temp_avg_sample_vehicleownership[l]
											+ temp_sampleData[i][j][k][l][m][n][o];
									// gender HHSIZE
									temp_avg_sample_hhsize[m] = temp_avg_sample_hhsize[m]
											+ temp_sampleData[i][j][k][l][m][n][o];
									// gender NUMBEROFROOM
									temp_avg_sample_numberofroom[n] = temp_avg_sample_numberofroom[n]
											+ temp_sampleData[i][j][k][l][m][n][o];
									// gender LESSTHANFIVE
									temp_avg_sample_lessthanfive[o] = temp_avg_sample_lessthanfive[o]
											+ temp_sampleData[i][j][k][l][m][n][o];
								}
							}
						}

					}
				}

			}
		}

		System.out.println("----------Sample average Data-------------------------");
		System.out.println("MARRIED DATA");
		for (double d : temp_avg_sample_married) {
			System.out.println("married\t" + c(d));
		}
		System.out.println("WORKER DATA");
		for (double d : temp_avg_sample_worker) {
			System.out.println("WORKER\t" + c(d));
		}
		System.out.println("HHOWNERSHIP DATA");
		for (double d : temp_avg_sample_hhownership) {
			System.out.println("HHOWNERSHIP\t" + c(d));
		}
		System.out.println("VEHICLEOWNERSHIP DATA");
		for (double d : temp_avg_sample_vehicleownership) {
			System.out.println("VEHICLEOWNERSHIP\t" + c(d));
		}
		System.out.println("HHSIZE DATA");
		for (double d : temp_avg_sample_hhsize) {
			System.out.println("HHSIZE\t" + c(d));
		}
		System.out.println("NUMBEROFROOM DATA");
		for (double d : temp_avg_sample_numberofroom) {
			System.out.println("NUMBEROFROOM\t" + c(d));
		}
		System.out.println("LESSTHANFIVE DATA");
		for (double d : temp_avg_sample_lessthanfive) {
			System.out.println("LESSTHANFIVE\t" + c(d));
		}

	}

	private String c(double ldouble) {
		// return new Double(ldouble).toString();
		return ((new BigDecimal(Double.toString(ldouble))).toPlainString());
	}

	private void readData(String sampleData, String targetData) {

		// storing target data
		double[] temp1 = new double[MARRIED + WORKER + HHOWNERSHIP + VEHICLEOWNERSHIP + HHSIZE + NUMBEROFROOM
				+ LESSTHANFIVE];
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
			// reading married
			for (int i = 0; i < MARRIED; i++) {
				avg_target_married[i] = temp1[index1];
				index1++;
			}
			// reading worker
			for (int i = 0; i < WORKER; i++) {
				avg_target_worker[i] = temp1[index1];
				index1++;
			}
			// reading HHOWNERSHIP
			for (int i = 0; i < HHOWNERSHIP; i++) {
				avg_target_hhownership[i] = temp1[index1];
				index1++;
			}
			// reading VEHICLEOWNERSHIP
			for (int i = 0; i < VEHICLEOWNERSHIP; i++) {
				avg_target_vehicleownership[i] = temp1[index1];
				index1++;
			}
			// reading HHSIZE
			for (int i = 0; i < HHSIZE; i++) {
				avg_target_hhsize[i] = temp1[index1];
				index1++;
			}
			// reading NUMBEROFROOM
			for (int i = 0; i < NUMBEROFROOM; i++) {
				avg_target_numberofroom[i] = temp1[index1];
				index1++;
			}
			// reading LESSTHANFIVE
			for (int i = 0; i < LESSTHANFIVE; i++) {
				avg_target_lessthanfive[i] = temp1[index1];
				index1++;
			}

		} catch (IOException e) {
		}

		// sampledata processing
		double[] temp = new double[MARRIED * WORKER * HHOWNERSHIP * VEHICLEOWNERSHIP * HHSIZE * NUMBEROFROOM
				* LESSTHANFIVE];
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

			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										this.sampleData[i][j][k][l][m][n][o] = temp[index];
										index++;
									}
								}
							}

						}
					}

				}
			}
			temp = null;

			for (int i = 0; i < MARRIED; i++) {
				for (int j = 0; j < WORKER; j++) {
					for (int k = 0; k < HHOWNERSHIP; k++) {
						for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
							for (int m = 0; m < HHSIZE; m++) {
								for (int n = 0; n < NUMBEROFROOM; n++) {
									for (int o = 0; o < LESSTHANFIVE; o++) {
										// gender MARRIED
										avg_sample_married[i] = avg_sample_married[i]
												+ this.sampleData[i][j][k][l][m][n][o];
										// gender WORKER
										avg_sample_worker[j] = avg_sample_worker[j]
												+ this.sampleData[i][j][k][l][m][n][o];
										// gender HHOWNERSHIP
										avg_sample_hhownership[k] = avg_sample_hhownership[k]
												+ this.sampleData[i][j][k][l][m][n][o];
										// gender VEHICLEOWNERSHIP
										avg_sample_vehicleownership[l] = avg_sample_vehicleownership[l]
												+ this.sampleData[i][j][k][l][m][n][o];
										// gender HHSIZE
										avg_sample_hhsize[m] = avg_sample_hhsize[m]
												+ this.sampleData[i][j][k][l][m][n][o];
										// gender NUMBEROFROOM
										avg_sample_numberofroom[n] = avg_sample_numberofroom[n]
												+ this.sampleData[i][j][k][l][m][n][o];
										// gender LESSTHANFIVE
										avg_sample_lessthanfive[o] = avg_sample_lessthanfive[o]
												+ this.sampleData[i][j][k][l][m][n][o];
									}
								}
							}

						}
					}

				}
			}
		} catch (IOException e) {
		}
	}

	private void copyData() {
		for (int i = 0; i < MARRIED; i++) {
			for (int j = 0; j < WORKER; j++) {
				for (int k = 0; k < HHOWNERSHIP; k++) {
					for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
						for (int m = 0; m < HHSIZE; m++) {
							for (int n = 0; n < NUMBEROFROOM; n++) {
								System.arraycopy(this.sampleData[i][j][k][l][m][n], 0,
										this.temp_sampleData[i][j][k][l][m][n], 0, LESSTHANFIVE);
							}
						}
					}
				}

			}
		}

		System.arraycopy(avg_sample_married, 0, temp_avg_sample_married, 0, MARRIED);
		System.arraycopy(avg_sample_worker, 0, temp_avg_sample_worker, 0, WORKER);
		System.arraycopy(avg_sample_hhownership, 0, temp_avg_sample_hhownership, 0, HHOWNERSHIP);
		System.arraycopy(avg_sample_vehicleownership, 0, temp_avg_sample_vehicleownership, 0, VEHICLEOWNERSHIP);
		System.arraycopy(avg_sample_hhsize, 0, temp_avg_sample_hhsize, 0, HHSIZE);
		System.arraycopy(avg_sample_numberofroom, 0, temp_avg_sample_numberofroom, 0, NUMBEROFROOM);
		System.arraycopy(avg_sample_lessthanfive, 0, temp_avg_sample_lessthanfive, 0, LESSTHANFIVE);

	}

	private void prettyPrinting() {
		for (int i = 0; i < MARRIED; i++) {
			for (int j = 0; j < WORKER; j++) {
				for (int k = 0; k < HHOWNERSHIP; k++) {
					for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
						for (int m = 0; m < HHSIZE; m++) {
							for (int n = 0; n < NUMBEROFROOM; n++) {
								for (int o = 0; o < LESSTHANFIVE; o++) {
									double data = temp_sampleData[i][j][k][l][m][n][o];
									print(i, j, k, l, m, n, o, data);
								}
							}
						}

					}
				}

			}
		}
	}

	private void closeResources() {
		out.close();
	}

	boolean header = false;

	private void print(int i, int j, int k, int l, int m, int n, int o, double data) {
		if (!header) {
			out.println("MARRIED,WORKER,HHOWNERSHIP,VEHICLEOWNERSHIP,HHSIZE,NUMBEROFROOM,LESSTHANFIVE");
			header = true;
		}
		for (int temp = 0; temp < data; temp++) {
			// married
			switch (i) {
			case 0:
				out.print("None,");
				break;
			case 1:
				out.print("1,");
				break;
			case 2:
				out.print("2,");
				break;
			case 3:
				out.print("3,");
				break;
			case 4:
				out.print("4,");
				break;
			case 5:
				out.print("5,");
				break;
			}

			// worker
			switch (j) {
			case 0:
				out.print("None,");
				break;
			case 1:
				out.print("1,");
				break;
			case 2:
				out.print("2,");
				break;
			case 3:
				out.print("3,");
				break;
			case 4:
				out.print("4,");
				break;
			case 5:
				out.print("5,");
				break;

			}

			// HHOWNERSHIP
			switch (k) {
			case 0:
				out.print("1,");
				break;
			case 1:
				out.print("2,");
				break;
			case 2:
				out.print("3,");
				break;
			case 3:
				out.print("4,");
				break;
			}

			// vehicleownership
			switch (l) {
			case 0:
				out.print("0,");
				break;
			case 1:
				out.print("1,");
				break;
			}

			// HHSIZE
			switch (m) {
			case 0:
				out.print("1,");
				break;
			case 1:
				out.print("2,");
				break;
			case 2:
				out.print("3,");
				break;
			case 3:
				out.print("4,");
				break;
			case 4:
				out.print("5,");
				break;
			case 5:
				out.print("6,");
				break;
			}

			// Numberofroom
			switch (n) {
			case 0:
				out.print("1,");
				break;
			case 1:
				out.print("2,");
				break;
			case 2:
				out.print("3,");
				break;
			case 3:
				out.print("4,");
				break;
			case 4:
				out.print("5,");
				break;
			case 5:
				out.print("6,");
				break;

			}

			// LESSTHANFIVE
			switch (o) {
			case 0:
				out.println("0,");
				break;
			case 1:
				out.println("1,");
				break;
			case 2:
				out.println("2,");
				break;
			case 3:
				out.println("3,");
				break;

			}

		}
	}

	private void sum() {
		sum = 0;
		for (int i = 0; i < MARRIED; i++) {
			for (int j = 0; j < WORKER; j++) {
				for (int k = 0; k < HHOWNERSHIP; k++) {
					for (int l = 0; l < VEHICLEOWNERSHIP; l++) {
						for (int m = 0; m < HHSIZE; m++) {
							for (int n = 0; n < NUMBEROFROOM; n++) {
								for (int o = 0; o < LESSTHANFIVE; o++) {
									double data = temp_sampleData[i][j][k][l][m][n][o];
									sum += data;
								}
							}
						}

					}
				}

			}
		}
	}
}
