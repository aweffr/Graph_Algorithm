package undirected;

import java.io.IOException;
import java.util.*;

public class BreadthFirstPaths {
	private Map<Integer, Boolean> marked;		// Is a shortest path to this vertex known?
	private Map<Integer, Integer> edgeTo;		// last vertex on known path to this vertex
	private final int s;						// source
	
	public BreadthFirstPaths(Graph g, int s) {
		marked = new HashMap<>(g.V());
		edgeTo = new HashMap<>(g.V());
		for (int v: g.vertices() ) {
			marked.put(v, false);
			edgeTo.put(v, null);
		}
		this.s = s;
		bfs(g, s);
	}
	
	private void bfs(Graph g, int s) {
		Deque<Integer> quene = new ArrayDeque<>();
		marked.put(s, true);			// Mark the source
		quene.add(s);					//   and put it on the quene.
		while ( !quene.isEmpty() ) {
			System.out.println(quene);	// Log
			int v = quene.remove();		// Remove next vertex from the quene.
			for (int w: g.adj(v) ) {
				if ( !marked.get(w) ) {
					edgeTo.put(w, v);
					marked.put(w, true);
					quene.add(w);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked.get(v);
	}
	
	public Iterable<Integer> pathTo(int v) {
		if ( !hasPathTo(v) ) return null;
		Deque<Integer> path = new ArrayDeque<>();
		for(int x=v; x!=s; x=edgeTo.get(x) ) {
			path.push(x);
		}
		return path;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Graph g = new Graph(System.in);
		int s = 0;
		BreadthFirstPaths bfp = new BreadthFirstPaths(g, s);
		for (int i=0; i<7; i++) {
			if (i==s) continue;
			String str = bfp.pathTo(i).toString().replace("[", "").replace("]", "").replace(", ", "->");
			System.out.println(s+" to "+i+" : "+s+"->"+str);
		}
	}

}
