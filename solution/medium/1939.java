// BFS + 매개변수 탐색

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		final int INF = 1_000_000_007;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Edge>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph.get(A).add(new Edge(B, C));
			graph.get(B).add(new Edge(A, C));
		}
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int l = 1, r = 1_000_000_000;
		while (l <= r) {
			int mid = (l + r) / 2;
			boolean[] visited = new boolean[N+1];
			visited[s] = true;
			Queue<Integer> queue = new ArrayDeque<>();
			queue.offer(s);
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (Edge edge: graph.get(cur)) {
					if (visited[edge.loc] || edge.dist < mid) continue;
					queue.offer(edge.loc);
					visited[edge.loc] = true;
				}
			}
			if (visited[e]) l = mid + 1;
			else r = mid - 1;
		}
		System.out.println(r);
	}
	
	static class Edge {
		int loc;
		long dist;

		public Edge(int loc, long dist) {
			this.loc = loc;
			this.dist = dist;
		}
	}
}
