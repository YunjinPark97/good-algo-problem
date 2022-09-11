import java.io.*;
import java.util.*;

public class Main {
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		makeSet(n);
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if (!union(u, v)) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}
	
	static void makeSet(int N) {
		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}

	static int findRoot(int a) {
		if (a == p[a]) return a;
		return p[a] = findRoot(p[a]);
	}

	static boolean union(int a, int b) {
		a = findRoot(a);
		b = findRoot(b);
		if (a == b) return false;
		p[b] = a;
		return true;
	}
}
