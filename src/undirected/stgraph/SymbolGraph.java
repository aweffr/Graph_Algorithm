package undirected.stgraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import undirected.Graph;

public class SymbolGraph {
	private Map<String, Integer> st;
	private Map<Integer, String> keys;
	private Graph G;
	
	public SymbolGraph(String stream, String sp) throws FileNotFoundException {
		st = new HashMap<>();
		keys = new HashMap<>();
		Scanner sc =new Scanner(new FileReader(stream)); 		// Build the index.
		while (sc.hasNextLine() ) {
			String[] a = sc.nextLine().split(sp);
			for (String s: a) {									// Every string map to a number.
				if (!st.containsKey(s))
					st.put(s, st.size());
			}
		}
		sc.close();
		for (String name: st.keySet()) {						// Initialize the Integer-String Map.
			keys.put(st.get(name), name);
		}
		G = new Graph(st.size());
		Scanner sc2 = new Scanner(new FileReader(stream));
		while ( sc2.hasNextLine() ) {
			String[] a = sc2.nextLine().split(sp);
			int v = st.get(a[0]);
			for (int i=1; i< a.length; i++) {
				G.addEdge(v, st.get(a[i]));
			}
		}
		sc2.close();
	}
	
	public boolean contains(String s) {
		return st.containsKey(s);
	}
	
	public int index (String s) {
		return st.get(s);
	}
	
	public String name(int v) {
		if ( !keys.containsKey(v) )
			throw new RuntimeException("index not exist!");
		return keys.get(v);
	}
	
	public Graph G() {
		return G;
	}
}
