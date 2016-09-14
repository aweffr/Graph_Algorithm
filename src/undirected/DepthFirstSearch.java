package undirected;

import java.io.IOException;
import java.util.*;

public class DepthFirstSearch {
	protected Map<Integer, Boolean> marked;
	protected int cnt;
	protected SortedSet<Integer> connectedVertices;
	
	public DepthFirstSearch(Graph g, int s) {
		// find vertices connected to a source vertex s.
		// s -> "source"
		connectedVertices = new TreeSet<>();
		marked = new HashMap<>();
		for (int v: g.vertices()) {
			// Initialize the "marked" map.
			marked.put(v, false);
		}
		this.dfs(g, s);
	}
	
	protected void dfs(Graph g, int v) {
		marked.put(v, true);
		connectedVertices.add(v);
		cnt++;
		for (int w: g.adj(v)) {
			if (!marked(w))	
				dfs(g, w);
		}
	}
	
	public boolean marked(int v) {
		// is v connected to s?
		return marked.get(v);
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
		DepthFirstSearch search = new DepthFirstSearch(g, s);
		System.out.printf("Now search the vertix %d in graph:\n", s);
		System.out.println("The reasult is:");
		System.out.println(search.connectedList());
	}
}
