package shortestPath;

import java.io.IOException;

public class TestDijkstra {

	public static void main(String[] args) throws IOException {
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(args[0]);
		DijkstraSP sp = new DijkstraSP(G, 1);
		int[] vertices = {7,37,59,82,99,115,133,165,188,197};
		for(int v: vertices) {
			System.out.print((int)sp.distTo(v)+",");
		}
		System.out.println();
		System.out.println(sp.distTo(7)+" "+sp.pathTo(2));
		System.out.println(sp.distTo(37)+" "+sp.pathTo(37));
		System.out.println(sp.distTo(59)+" "+sp.pathTo(59));
		System.out.println(sp.distTo(82)+" "+sp.pathTo(82));
		System.out.println(sp.distTo(99)+" "+sp.pathTo(99));
		System.out.println(sp.distTo(115)+" "+sp.pathTo(115));
		System.out.println(sp.distTo(133)+" "+sp.pathTo(133));
		System.out.println(sp.distTo(165)+" "+sp.pathTo(165));
		System.out.println(sp.distTo(188)+" "+sp.pathTo(188));
		System.out.println(sp.distTo(197)+" "+sp.pathTo(197));
	}

}
