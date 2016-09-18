package shortestPath;
/*
 * In fact the DirectedEdge implementation is simpler than the UndirectedEdge in MST problem
 * because the two vertices are distinguished.
 * It use w = e.to(), v = e.from(); to access a DirectedEdge's two vertices.
 */

public class DirectedEdge implements Comparable<DirectedEdge> {
	private final int v;
	private final int w;
	private final double weight;
	
	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
	
	public int from() {
		return v;
	}
	
	public int to() {
		return w;
	}
	
	public String toString() {
		return String.format("%d->%d %.2f", v, w, weight);
	}
	
	public int compareTo(DirectedEdge e2) {
		DirectedEdge e1 = this;
		return (int)(e1.weight - e2.weight);
	}
}
