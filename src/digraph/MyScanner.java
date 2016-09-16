package digraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MyScanner {
	BufferedReader br;
	StringTokenizer tk;
	
	public MyScanner(InputStream in) {
		br = new BufferedReader(new InputStreamReader(in));
		tk = new StringTokenizer("");
	}
	
	public MyScanner(String in) throws FileNotFoundException {
		br = new BufferedReader(new FileReader(in));
		tk = new StringTokenizer("");
	}
	
	public String next() throws IOException {
		while ( !tk.hasMoreTokens() ) {
			tk = new StringTokenizer(br.readLine());
		}
		return tk.nextToken();
	}
	
	public int nextInt() throws IOException, NumberFormatException {
		return Integer.parseInt(next());
	}
	
	public double nextDouble() throws IOException, NumberFormatException {
		return Double.parseDouble(next());
	}
}
