package undirected;

import java.util.*;

public class TwoColor {
	private Map<Integer, Boolean> marked;
	private Map<Integer, Boolean> color;
	private boolean isTwoColorable = true;
	
	public TwoColor(Graph g) {
		marked = new HashMap<>();
		color = new HashMap<>();
		for (int v: g.vertices() ) {
			marked.put(v, false);
			color.put(v, false);
		}
		for (int s: g.vertices() ) {
			if ( !marked.get(s) ) {
				dfs(g, s);
			}
		}
	}
	
	public void dfs(Graph g, int v) {
		marked.put(v, true);
		for (int w: g.adj(v)) {
			if (!marked.get(w)) {
				color.put(w, !color.get(v));
				dfs(g, w);
			}
			else if (color.get(w) == color.get(v))
				isTwoColorable = false;
		}
	}
	
	public boolean isBipartite() {
		return isTwoColorable;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
