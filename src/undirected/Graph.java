package undirected;

import java.io.*;
import java.util.*;

public class Graph {
	private int V = 0;
	private int E = 0;
	private Map<Integer, SortedSet<Integer>> adj;
	
	public Graph(InputStream in) throws NumberFormatException, IOException {
		// read a graph from input stream.
		Reader.init(in);
		int V = Reader.nextInt();
		int E = Reader.nextInt();
		// Initialize the graph.
		this.V = V;
		this.E = 0;
		adj = new HashMap<>();
		for (int v=0; v < V; v++) {
			adj.put(v, new TreeSet<Integer>());
		}
		// Add edges from the inputStream.
		for (int i=0; i < E; i++) {
			int v = Reader.nextInt();
			int w = Reader.nextInt();
			this.addEdge(v, w);
		}
	}
	
	public Graph(int V) {
		// Create a V-vertex graph with no edges.
		this.V = V;
		this.E = 0;
		adj = new HashMap<>();	// adj is a map of vertix-list.
		for (int v=0; v < V; v++) {
			adj.put(v, new TreeSet<Integer>());
		}
	}
	
	public int V() {
		// number of vertices
		return this.V;
	}
	
	public int E() {
		// number of edges
		return this.E;
	}
	
	public void addEdge(int v, int w) {
		// add edge v-w to this graph
		adj.get(v).add(w);
		adj.get(w).add(v);
		E++;
	}
	
	public SortedSet<Integer> adj(int v) {
		// get the vertices adjacent to the Vertix v.
		return adj.get(v);
	}
	
	public Set<Integer> vertices() {
		return adj.keySet();
	}
	
	public int degree(int v) {
		// return how many vertices adjacent to the Vertix v.
		return adj.get(v).size();
	}
	
	public int maxDegree() {
		// compute maximum degree
		int max = 0;
		for (int v: adj.keySet()) {
			int size = adj.get(v).size();
			if (size > max) { max = size; }
		}
		return max;
	}
	
	public double avgDegree() {
		String out = String.format("%.2f", 2 * (E + 0.0) / V);
		return Double.parseDouble(out);
	}
	
	public int numberOfSelfLoops() {
		int cnt = 0;
		for (int v: adj.keySet()) {
			for (int w: adj.get(v)) {
				if (w==v) cnt++;
			}
		}
		return cnt;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(String.format("This undirect graph has %d edge(s) and %d vertice(s).\n", E, V));
		for (int v: adj.keySet()) {
			s.append(v+": "+adj.get(v).toString()+"\n");
		}
		return s.toString();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Graph g = new Graph(System.in);
		System.out.print(g);
	}

}
