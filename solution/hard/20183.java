import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		
		List<List<Edge>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		long before = System.currentTimeMillis();
		for (int i = 0; i < M; i++ ) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Edge(v, dist));
			graph.get(v).add(new Edge(u, dist));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		int start = 1;
		int end = 1_000_000_000;
		int ans = INF;
		while (start <= end) {
			int mid = (start + end) / 2; // overflow 발생 안 함.
			long[] d = new long[N+1];
			Arrays.fill(d, 600_000_000_000_000L);
			pq.offer(new Edge(A, 0));
			d[A] = 0;
			while (!pq.isEmpty()) {
				Edge cur = pq.poll();
				int curLoc = cur.loc;
				long curDist = cur.dist;
				if (d[curLoc] < curDist) continue;
				for (Edge nxt: graph.get(curLoc)) {
					if (nxt.dist > mid) continue;
					long nxtDist = curDist + nxt.dist;
					int nxtLoc = nxt.loc;
					if (d[nxtLoc] > nxtDist) {
						d[nxtLoc] = nxtDist;
						pq.offer(new Edge(nxtLoc, nxtDist));
					}
				}
			}
			if (d[B] > C) {
				start = mid + 1;
			} else {
				ans = mid;
				end = mid - 1;
			}
		}
		System.out.println((ans==INF)? -1: ans);
	}
	
	static class Edge implements Comparable<Edge> {
		int loc;
		long dist;

		public Edge(int loc, long dist) {
			this.loc = loc;
			this.dist = dist;
		}

		public int compareTo(Edge o) {
			return Long.compare(this.dist, o.dist);
		}
	}
}
