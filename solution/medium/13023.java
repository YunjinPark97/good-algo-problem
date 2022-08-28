import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static List<List<Integer>> graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		visited = new boolean[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for (int i = 0; i < N; i++) {
			if (dfs(i, 1)) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}
	
	static boolean dfs(int cur, int len) {
		if (len == 5) {
			return true;
		}
		
		visited[cur] = true;
		boolean flag = false;
		for (int nxt: graph.get(cur)) {
			if (visited[nxt]) continue;
			flag |= dfs(nxt, len+1);
		}
		visited[cur] = false;
		return flag;
	}
}
