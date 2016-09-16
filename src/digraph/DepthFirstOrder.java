package digraph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class DepthFirstOrder {
	private Map<Integer, Boolean> marked;
	
	private Deque<Integer> pre;					// vertices in pre-order.(Queue)
	private Deque<Integer> post;				// vertices in post-order.(Queue)
	private Deque<Integer> reversePost;			// vertices in reverse post-order.(Stack)
	
	public DepthFirstOrder(Digraph G) {
		marked = new HashMap<>();
		pre = new ArrayDeque<>();
		post = new ArrayDeque<>();
		reversePost = new ArrayDeque<>();
		
		for (int v: G.vertices()) {
			if (!marked(v))
				dfs(G,v);
		}
	}
	
	private void dfs(Digraph G, int v) {
		pre.addLast(v);
		marked.put(v, true);
		for (int w: G.adj(v)){
			if (!marked(w))
				dfs(G, w);
		}
		post.addLast(v);
		reversePost.push(v);
	}
	
	private boolean marked(int v) {
		if (!marked.containsKey(v))		marked.put(v, false);
		return marked.get(v);
	}
	
	public Iterable<Integer> pre() {
		return pre;
	}
	public Iterable<Integer> post() {
		return post;
	}
	public Iterable<Integer> reversePost() {
		return reversePost;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
