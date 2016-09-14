package mst;

import java.util.*;

/*
 * This implementation is a lazy approach because 
 * it leaves ineligible edges in the pq until those edges poll out.
 */

public class LazyPrimMST {
	private Map<Integer, Boolean> marked;	// MST vertices
	private Deque<Edge> mst;				// MST edges
	private PriorityQueue<Edge> pq;			// crossing (and ineligible) edges
	
	public LazyPrimMST(EdgeWeightedGraph g) {
		pq = new PriorityQueue<>();
		marked = new HashMap<>();
		mst =  new ArrayDeque<>();
		for (int v: g.vertices()) {
			marked.put(v, false);
		}
		
		visit(g, 0);								// Assume g is connected.
		while( !pq.isEmpty() ) {
			Edge e = pq.poll();						// Get lowest-weight edge from pq.
			int v = e.either(), w = e.other(v);
			if ( marked.get(v) && marked.get(w) )
				continue;							// Skip if ineligible.
			mst.add(e);
			if ( !marked.get(v) )					// add vertex to tree.
				visit(g, v);
			if ( !marked.get(w) )
				visit(g, w);
		}
	}
	
	private void visit(EdgeWeightedGraph g, int v) {
		// Mark v and add to pq all edges from v to unmarked vertices.
		marked.put(v, true);
		for ( Edge e: g.adj(v) ) {
			if ( !marked.get(e.other(v)) )
				pq.add(e);
		}
	}
	
	public Iterable<Edge> edges() {
		// All of the MST edges.
		return mst;
	}
	
	public double weight() {
		// Weight of MST.
		double out = 0;
		for (Edge e: mst) {
			out += e.weight();
		}
		return out;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
