package digraph;

import java.util.Map;
import java.util.Set;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class KosarajuSCC {
	private Map<Integer, Boolean> marked;	// reached vertices
	private Map<Integer, Integer> id;		// component identifiers
	private int cnt;						// number of strong compnents
	private List<Set<Integer>> strongCC;

	
	public KosarajuSCC(Digraph G) {
		marked = new HashMap<>();
		id = new HashMap<>();
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());
		for (int s: order.reversePost() ) {
			if (!marked(s)) {
				dfs(G, s);
				cnt++;
			}
		}
		generateStrongCC();
	}
	
	private void dfs(Digraph G, int v) {
		marked.put(v, true);
		id.put(v, cnt);
		for (int w: G.adj(v)) {
			if (!marked(w))	dfs(G, w);
		}
	}
	
	private boolean marked(int v) {
		if (!marked.containsKey(v))		marked.put(v, false);
		return marked.get(v);
	}
	
	private void generateStrongCC() {
		Map<Integer, Set<Integer>> temp = new HashMap<>();
		for (int v: id.keySet()) {
			int ID = id.get(v);
			if (!temp.containsKey(ID))	temp.put(ID, new HashSet<>());
			temp.get(ID).add(v);
		}
		
		strongCC = new ArrayList<Set<Integer>>();
		
		for(int ID: temp.keySet()) {
			strongCC.add(temp.get(ID));
		}
		Collections.sort(strongCC, new Comparator<Set<Integer>>() {
			@Override
			public int compare(Set<Integer> o1, Set<Integer> o2) {
				return o2.size() - o1.size();
			}
		});
	}
	
	public List<Set<Integer>> components() {
		return strongCC;
	}
	
	public boolean stronglyConnected(int v, int w) {
		return id.get(v) == id.get(w);
	}
	
	public int id(int v) {
		return id.get(v);
	}
	
	public int count() {
		return cnt;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(cnt +" components.\n");
		sb.append("Each size is:\n");
		for(int i=0; i<5; i++) {
			sb.append(components().get(i).size()+" ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// $java KosarajuSCC filename splitter
		Digraph G = new Digraph(args[0], args[1]);
		KosarajuSCC scc = new KosarajuSCC(G);
		System.out.print(scc);
	}

}
