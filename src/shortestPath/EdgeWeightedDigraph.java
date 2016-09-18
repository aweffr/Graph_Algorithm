package shortestPath;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;


/*
 * Self-loops and parallel edges are allowed.
 */
public class EdgeWeightedDigraph {
	private int V;
	private int E;
	private Map<Integer, Set<DirectedEdge>> adj;
	
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = new HashMap<>();
	}
	
	public EdgeWeightedDigraph(String In) throws IOException {
		adj = new HashMap<>();
		Scanner sc = new Scanner(new FileReader(In));
		StringTokenizer tk = new StringTokenizer("");
		while (sc.hasNextLine()) {
			tk = new StringTokenizer(sc.nextLine().replace(",", " "));
			int v = Integer.parseInt(tk.nextToken());
			while ( tk.hasMoreTokens() ) {
				int w = Integer.parseInt(tk.nextToken());
				double weight = Double.parseDouble(tk.nextToken());
				addEdge(new DirectedEdge(v, w, weight));
			}
		}
		V = adj.size();
		sc.close();
	}
	
	public int V() { return V;	}
	public int E() { return E; }
	
	public void addEdge(DirectedEdge e) {
		if (!adj.containsKey(e.from()))
			adj.put(e.from(), new HashSet<>());
		adj.get(e.from()).add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v) {
		return adj.get(v);
	}
	
	public Iterable<DirectedEdge> edges() {
		Set<DirectedEdge> out = new HashSet<>();
		for (Set<DirectedEdge> set: adj.values()) {
			out.addAll(set);
		}
		return out;
	}
	
	public Iterable<Integer> vertices() {
		return adj.keySet();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("This graph contains %d vertices and %d edges.\n", V, E));
		int cnt = 0;
		for (Entry<Integer, Set<DirectedEdge>> entry: adj.entrySet()) {
			String edges = entry.getValue().toString().replace("[", "").replaceAll("]", "");
			sb.append(entry.getKey()+" "+edges+"\n");
			cnt++;
			if (cnt > 5)
				break;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(args[0]);
		ArrayList<DirectedEdge> v1Edges = new ArrayList<>();
		for(DirectedEdge e: g.adj(1)) {
			v1Edges.add(e);
		}
		Collections.sort(v1Edges, Comparator.reverseOrder());
		System.out.println(v1Edges);
		System.out.println(g);
	}
	
}
