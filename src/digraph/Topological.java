package digraph;

import java.io.FileNotFoundException;

public class Topological {
	private Iterable<Integer> order;		// topological order.
	
	public Topological(Digraph G) {
		DirectedCycle cyclefinder = new DirectedCycle(G);
		if (!cyclefinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order() {
		return order;
	}
	
	public boolean isDAG() {
		return order==null;
	}
	
	public static void main (String[] args) throws FileNotFoundException {
		String filename = args[0];
		String separator = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, separator);
		
		Topological top = new Topological(sg.G());
		
		for (int v: top.order() ) {
			System.out.println(sg.name(v));
		}
	}
}
