package digraph;
/*
 * A boolean array onStack[] to keep track of the vertices for which the recursive call has not completed.
 * When it finds an edge v->w to a vertex w that is on the stack, it has discovered a directed cycle,
 * which it can recover by following edgeTo[] links.
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class DirectedCycle {
	private Map<Integer, Boolean> marked;
	private Map<Integer, Integer> edgeTo;
	private Deque<Integer> cycle;
	private Map<Integer, Boolean> onStack;
	
	public DirectedCycle(Digraph G) {
		edgeTo = new HashMap<>();
		marked = new HashMap<>();
		for (int v: G.vertices() ) {
			if ( !marked(v) )
				dfs(G, v);
		}
	}
	
	private void dfs(Digraph G, int v) {
		onStack.put(v, true);
		marked.put(v, true);
		for (int w: G.adj(v)) {
			if ( this.hasCycle() ) {
				return;
			}
			else if (!marked(w)) {
				edgeTo.put(w, v);
				dfs(G, w);
			}
			else if ( onStack.get(w) ) {
				cycle = new ArrayDeque<Integer>();
				for (int x=v; x!=w; x=edgeTo.get(x))
					cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack.put(v, false);
	}
	
	private boolean marked(int v) {
		if ( !marked.containsKey(v) )
			marked.put(v, false);
		return marked.get(v);
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle() {
		return cycle;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
