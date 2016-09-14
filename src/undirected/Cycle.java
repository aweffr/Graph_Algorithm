package undirected;

import java.io.IOException;
import java.util.*;

public class Cycle {
	// is G acyclic? (assumes no self-loops or parallel edges)
	private Map<Integer, Boolean> marked;
	private boolean hasCycle;
	
	public Cycle(Graph g) {
		marked = new HashMap<>();
		for(int v: g.vertices() ) {
			marked.put(v, false);
		}
		for(int s: g.vertices() ) {
			if ( !marked.get(s) )
				dfs(g, s, s);
		}
	}
	
	private void dfs(Graph g, int v, int u) {
//		System.out.printf("dfs(g, %d, %d)\n", v, u);
		marked.put(v, true);
		for ( int w: g.adj(v) ) {
			if (!marked.get(w))
				dfs(g, w, v);
			else if (w!=u)
//				System.out.printf("Now w=%d, u=%d\n", w, u);
//				u is "where is v from"
//				So if w != u but w has been found, there shoule be more than one path to w, which means there is a cycle.
				hasCycle = true;
		}
	}
	
	public boolean hasCycle() {
		return hasCycle;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Graph g = new Graph(System.in);
		Cycle cy = new Cycle(g);
		System.out.println(cy.hasCycle());
	}
}
