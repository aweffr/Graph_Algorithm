package shortestPath;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSP {
	private Map<Integer, DirectedEdge> edgeTo;
	private Map<Integer, Double> distTo;
	private PriorityQueue<VetexEdgePair> pq;
	
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo =new HashMap<>();
		distTo = new HashMap<>();
		pq = new PriorityQueue<>();
		for (int v: G.vertices()) {
			distTo.put(v, Double.POSITIVE_INFINITY);
		}
		distTo.put(s, 0.0);
		edgeTo.put(s, null);
		pq.add(new VetexEdgePair(s, 0.0));
		while (!pq.isEmpty()) {
			relax(G, pq.poll());
		}
	}
	
	private void relax(EdgeWeightedDigraph G, VetexEdgePair vetexEdgePair) {
		int v = vetexEdgePair.vertexIndex();
		for (DirectedEdge e: G.adj(v) ) {
			int w = e.to();
			if ( distTo.get(w) > distTo.get(v) + e.weight() ) {
				distTo.put(w, distTo(v) + e.weight());
				edgeTo.put(w, e);
				pqNavi(pq, new VetexEdgePair(w, distTo.get(w)));
			}
		}
	}
	
	public double distTo(int v) {
		return distTo.get(v);
	}
	
	public boolean hasPathTo(int v) {
		return distTo(v) < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		if (!hasPathTo(v))	return null;
		Deque<DirectedEdge> path = new ArrayDeque<>();
		for (DirectedEdge e = edgeTo.get(v); e!=null; e=edgeTo.get(e.from())) {
			path.push(e);
		}
		return path;
	}
	
	private void pqNavi(PriorityQueue<VetexEdgePair> pq, VetexEdgePair newPair) {
		for (VetexEdgePair pair: pq) {
			if (pair.vertexIndex() == newPair.vertexIndex()) {
				pq.remove(pair);
				break;
			}
		}
		pq.add(newPair);
	}
}

class VetexEdgePair implements Comparable<VetexEdgePair> {
	private final int v;
	private double weight;
	
	public VetexEdgePair(int v, double weight) {
		this.v = v;
		this.weight = weight;
	}

	public int compareTo(VetexEdgePair o2) {
		VetexEdgePair o1 = this;
		if (o1.weight < o2.weight) {
			return -1;
		}
		else if (o1.weight == o2.weight){
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public int vertexIndex() {
		return v;
	}
	
	public double weight() {
		return weight;
	}
	
	public void changeWeight(double weight) {
		this.weight = weight;
	}
}
