package digraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolDigraph {
	private Map<String, Integer> st;
	private Map<Integer, String> keys;
	private Digraph G;
	
	public SymbolDigraph(String stream, String sp) throws FileNotFoundException {
		st = new HashMap<>();
		keys = new HashMap<>();
		Scanner in = new Scanner(new FileReader(stream));
		while (in.hasNextLine() ) {
			String[] a = in.nextLine().split(sp);
			for (int i=0; i<a.length; i++) {
				if( !st.containsKey(a[i]) )
					st.put(a[i], st.size());
			}
		}
		for (String key: st.keySet()) {
			keys.put(st.get(key), key);
		}
		in.close();
		
		G = new Digraph(st.size());
		in = new Scanner(new FileReader(stream));
		while( in.hasNextLine() ) {
			String[] a = in.nextLine().split(sp);
			int v = st.get(a[0]);
			for (int i=1; i<a.length; i++)
				G.addEdge(v, st.get(a[i]));
		}
		in.close();
	}
	
	public boolean contains(String s) {
		return st.containsKey(s);
	}
	
	public int index(String s) {
		return st.get(s);
	}
	
	public String name(int v) {
		return keys.get(v);
	}
	
	public Digraph G() {
		return G;
	}
}
