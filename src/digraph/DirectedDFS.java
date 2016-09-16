package digraph;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * This implementation of depth-first search provides clients the ability to test which vertices
 * are reachable from a given vertex or a given set of vertices.
 */
public class DirectedDFS {
	private Map<Integer, Boolean> marked;
	private Set<Integer> reachableVertices;
	
	public DirectedDFS(Digraph G, int s) {
		marked = new HashMap<>();
		dfs(G, s);
		generateReachableVertices();
	}
	
	public DirectedDFS(Digraph G, Iterable<Integer> sources) {
		marked = new HashMap<>();
		for (int s: sources) {
			dfs(G, s);
		}
		generateReachableVertices();
	}
	
	public void dfs(Digraph G, int v) {
		marked.put(v, true);
		for (int w: G.adj(v)) 
			if (!marked.containsKey(w) || !marked.get(w)) {
				System.out.println("Now from "+v+" to "+w);
				dfs(G, w);
			}
	}
	
	public boolean marked(int v) {
		return (marked.containsKey(v) && marked.get(v));
	}
	
	private void generateReachableVertices() {
		reachableVertices = new HashSet<>();
		for (int v: marked.keySet() ) {
			if ( marked.get(v) )
				reachableVertices.add(v);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%d vertices are reachable in the Digraph.\n", reachableVertices.size()));
		sb.append(reachableVertices.toString()+"\n");
		return sb.toString();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Digraph G = new Digraph(args[0]);
		Set<Integer> sources = new HashSet<>();
		for (int i=1; i< args.length; i++)
			sources.add(Integer.parseInt(args[i]));
		DirectedDFS reachable = new DirectedDFS(G, sources);
		System.out.println(reachable);
	}
}
