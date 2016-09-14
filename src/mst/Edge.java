package mst;


public class Edge implements Comparable<Edge> {
	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
	
	public int either() {
		// When neither vertex is known, use either to access one vertex.
		return v;
	}
	
	public int other(int vertex) {
		// When one vertex of this edge is known, use this to access another edge.
		if		(vertex == v) return w;
		else if	(vertex == w) return v;
		else throw new RuntimeException("Inconsistent edge");
	}
	
	public int compareTo(Edge that) {
		if		(this.weight() < that.weight() )	return -1;
		else if	(this.weight() > that.weight() )	return 1;
		else										return 0;
	}
	
	public boolean isSelfLoop() {
		return v==w;
	}
	
	public String toString() {
		return String.format("%d-%d %.2f", v, w, weight);
	}
}
