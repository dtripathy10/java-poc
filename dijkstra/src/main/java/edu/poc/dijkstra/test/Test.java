package edu.poc.dijkstra.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.poc.dijkstra.engine.DijkstraAlgorithm;
import edu.poc.dijkstra.model.Edge;
import edu.poc.dijkstra.model.Graph;
import edu.poc.dijkstra.model.Vertex;

public class Test {

	private List<Vertex> nodes;
	private List<Edge> edges;

	public static void main(String[] args) {
		Test test = new Test();
		test.run();
	}

	public void run() {
		nodes = new ArrayList<Vertex>();
		// read node file and store into nodes array list
		readNodeFile("nodes.csv");
		edges = new ArrayList<Edge>();

		readEdgeFile("edges.csv");

		// Lets check from location Loc_1 to Loc_10
		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		for (int i = 0; i < nodes.size(); i++) {
			dijkstra.execute(nodes.get(i));
			for (int j = 0; j < nodes.size(); j++) {
				LinkedList<Vertex> path = dijkstra.getPath(nodes.get(j));
				// if path == null, print 0
				if (path == null) {
					System.out.print("0\t");
				} else {
					System.out.print(getDistance(path) + "\t");
				}
			}
			System.out.println("");
		}

	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo - 1), nodes.get(destLocNo - 1), duration);
		edges.add(lane);
	}

	private void readNodeFile(String peString) {
		try (BufferedReader br = new BufferedReader(new FileReader(peString))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] sep = sCurrentLine.split(",");
				Vertex location = new Vertex(sep[0], sep[1]);
				nodes.add(location);
			}
		} catch (IOException e) {
		}
	}

	private void readEdgeFile(String edgescsv) {
		try (BufferedReader br = new BufferedReader(new FileReader(edgescsv))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] sep = sCurrentLine.split(",");
				addLane(sep[0], new Integer(sep[1]), new Integer(sep[2]), new Integer(sep[3]));
			}
		} catch (IOException e) {
		}
	}

	private int getDistance(LinkedList<Vertex> path) {
		int sum = 0;
		Vertex start = path.get(0);
		for (int i = 1; i < path.size(); i++) {
			Vertex end = path.get(i);
			sum = sum + findWeight(start, end);
			start = end;
		}
		return sum;
	}

	private int findWeight(Vertex start, Vertex end) {
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			Vertex src = edge.getSource();
			Vertex dest = edge.getDestination();
			if ((src.getId().equals(start.getId())) && (dest.getId().equals(end.getId()))) {
				return edge.getWeight();
			}
		}
		System.exit(0);
		return 54353553;
	}
}
