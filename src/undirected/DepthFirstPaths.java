package undirected;

import java.util.*;
import java.io.*;

public class DepthFirstPaths {
	private Map<Integer, Boolean> marked;
	private Map<Integer, Integer> edgeTo;		//last vertex on known path to this vertex
	private SortedSet<Integer> connectedVertices;
	private final int s;						//source
	private int cnt;
	
	public DepthFirstPaths(Graph g, int s) {
		connectedVertices = new TreeSet<>();
		marked = new HashMap<>();
		for (int v: g.vertices()) {				// Initialize the "marked" map.
			marked.put(v, false);
		}
		edgeTo = new HashMap<>();
		for (int v: g.vertices()) {
			edgeTo.put(v, null);
		}
		this.s = s;
		dfs(g, s);
	}
	
	private void dfs(Graph g, int v) {
		marked.put(v, true);
		connectedVertices.add(v);
		for (int w: g.adj(v)) {
			if ( !marked.get(w) ) {
				edgeTo.put(w, v);
				dfs(g, w);
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked.get(v);
	}
	
	public Iterable<Integer> pathTo(int v) {
		if ( !hasPathTo(v) ) return null;
		Deque<Integer> out = new ArrayDeque<>();
		for ( int x=v; x!= s; x=edgeTo.get(x) ) {
			out.push(x);
		}
		return out;		
	}
	
	public int count() {
		// how many vertices are connected to s?
		return cnt;
	}
	
	public SortedSet<Integer> connectedList() {
		return connectedVertices;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Graph g = new Graph(System.in);
		int s = 0;
		DepthFirstPaths dfp = new DepthFirstPaths(g, s);
		for (int i=0; i<6; i++) {
			if (i==s) continue;
			String str = dfp.pathTo(i).toString().replace("[", "").replace("]", "").replace(", ", "->");
			System.out.println(s+" to "+i+" : "+s+"->"+str);
		}
	}
}
