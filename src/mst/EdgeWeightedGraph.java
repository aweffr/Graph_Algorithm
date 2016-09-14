package mst;
import java.util.*;
import java.io.*;

public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private Map<Integer, Set<Edge>> adj;
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = new HashMap<>();
	}
	
	public EdgeWeightedGraph(InputStream in) throws NumberFormatException, IOException {
		Reader.init(in);
		this.V = Reader.nextInt();
		int E = Reader.nextInt();
		adj = new HashMap<>();
		for (int i=0; i<E; i++) {
			Edge e = new Edge(Reader.nextInt(), Reader.nextInt(), Reader.nextDouble());
			addEdge(e);
		}
	}
	
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		if ( !adj.containsKey(v) )		adj.put(v, new HashSet<>());
		if ( !adj.containsKey(w) ) 		adj.put(w, new HashSet<>());
		adj.get(v).add(e);
		adj.get(w).add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v) {
		return adj.get(v);
	}
	
	public Set<Edge> edges() {
		Set<Edge> out = new HashSet<>(E);
		for ( int v: adj.keySet() ) {
			for (Edge e: adj.get(v)) {
				// MST should not contain self-loops.
				if ( !e.isSelfLoop() )	out.add(e);
			}
		}
		return out;
	}
	
	public int V() { return V;}
	public int E() { return E;}
	public Set<Integer> vertices() {
		return adj.keySet();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int v: adj.keySet() ) {
			sb.append("Vertex v: ");
			for(Edge e: adj.get(v) ) {
				sb.append(e.toString()+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		EdgeWeightedGraph g = new EdgeWeightedGraph(System.in);
		System.out.println(g);
		System.out.println(new TreeSet<Edge>(g.edges()));
	}

}
