package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Reader {
	private static BufferedReader br;
	private static StringTokenizer tk;
	
	public static void init(InputStream in) {
		br = new BufferedReader(new InputStreamReader(in));
		tk = new StringTokenizer("");
	}
	
	public static String next() throws IOException {
		while (!tk.hasMoreTokens()) {
			tk = new StringTokenizer(br.readLine());
		}
		return tk.nextToken();
	}
	
	public static int nextInt() throws NumberFormatException, IOException {
		return Integer.parseInt(next());
	}
	
	public static double nextDouble() throws NumberFormatException, IOException {
		return Double.parseDouble(next());
	}
}
