package digraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Digraph {
	private int V;
	private int E;
	private Map<Integer, Set<Integer>> adj;
	
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = new HashMap<>();
	}
	
	public Digraph(InputStream in) throws NumberFormatException, IOException {
		MyScanner sc = new MyScanner(in);
		this.V = sc.nextInt();
		int E = sc.nextInt();
		adj = new HashMap<>();
		for (int i=0; i<E; i++) {
			int u=sc.nextInt(), v=sc.nextInt();
			addEdge(u, v);
		}
	}
	
	public Digraph(String in, String sp) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(in));
		adj = new HashMap<>();
		while( sc.hasNextLine() ) {
			String[] s = sc.nextLine().split(sp);
			int u=Integer.parseInt(s[0]), v=Integer.parseInt(s[1]);
			addEdge(u, v);
		}
		this.V = adj.size();
		sc.close();
	}
	
	public Digraph(String in) throws NumberFormatException, IOException {
		MyScanner sc = new MyScanner(in);
		this.V = sc.nextInt();
		int E = sc.nextInt();
		adj = new HashMap<>();
		for (int i=0; i<E; i++) {
			int u=sc.nextInt(), v=sc.nextInt();
			addEdge(u, v);
		}
	}
	
	public void addEdge(int u, int v) {
		if (!adj.containsKey(u))	adj.put(u, new HashSet<>());
		if (!adj.containsKey(v))	adj.put(v, new HashSet<>());
		adj.get(u).add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj.get(v);
	}
	
	public Iterable<Integer> vertices() {
		return adj.keySet();
	}
	
	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for (int u: this.vertices() )
			for (int v: this.adj.get(u))
				R.addEdge(v, u);
		return R;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("This graph is with %d vertices and %d edges.\n", V, E));
		for(int u: vertices()) {
			String temp = adj.get(u).toString().replace("[", "").replace("]", "").replace(", ", "->");
			sb.append(u+"-->"+temp+"\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Digraph tc = new Digraph(System.in);
		System.out.println(tc);
	}
}
