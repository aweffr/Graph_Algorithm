package undirected;

import java.io.IOException;
import java.util.*;

public class CC {
	private Map<Integer, Boolean> marked;
	private Map<Integer, Integer> id;
	private Map<Integer, SortedSet<Integer>> components;
	private int cnt;
	
	public CC(Graph g) {
		marked = new HashMap<>();
		id  = new HashMap<>();
		components = new HashMap<>();
		for (int s:g.vertices() ) {
			marked.put(s, false);		// Initialize the "marked" map.
		}
		for(int s: g.vertices() ) {
			if (!marked.get(s)) {
				dfs(g, s);
				cnt++;					// As the identifier to recognize the CC
			}
		}
		generateComponentSet();			// After the searching process complete, generate the set for components.
	}
	
	private void dfs(Graph g, int v) {
		marked.put(v, true);
		id.put(v,cnt);
		for(int w: g.adj(v)) {
			if ( !marked.get(w) )
				dfs(g, w);
		}
	}
	
	public boolean connected(int v, int w) {
		return id.get(v) == id.get(w);
	}
	
	public int id(int v) {
		return id.get(v);
	}
	
	public int count() {
		return cnt;
	}
	
	public void generateComponentSet() {
		for ( int v: id.keySet() ) {
			int ID = id.get(v);
			if ( !components.containsKey(ID) ) {
				components.put(ID, new TreeSet<>());	//Create the key for ID.
			}
			components.get(ID).add(v);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(cnt + " components\n");
		for (int ID: components.keySet()) {
			sb.append(ID+" "+components.get(ID)+"\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Graph g = new Graph(System.in);
		System.out.println(g);
		
		CC cc = new CC(g);
		System.out.println(cc);		
	}
}
