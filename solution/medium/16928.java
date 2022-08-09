import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = 101;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] ladders = new int[INF];
		int[] snakes = new int[INF];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ladders[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			snakes[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		int[] dist = new int[INF];
		Arrays.fill(dist, INF);
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		dist[1] = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= 6; i++) {
				int nxt = cur + i;
				if (nxt > 100) break;
				if (ladders[nxt] > 0) {
					if (dist[nxt] > dist[cur] + 1) dist[nxt] = dist[cur] + 1;
					nxt = ladders[nxt];
					if (dist[nxt] > dist[cur] + 1) {
						dist[nxt] = dist[cur] + 1;
						queue.offer(nxt);
					}
				} else if (snakes[nxt] > 0) {
					if (dist[nxt] > dist[cur] + 1) dist[nxt] = dist[cur] + 1;
					nxt = snakes[nxt];
					if (dist[nxt] > dist[cur] + 1) {
						dist[nxt] = dist[cur] + 1;
						queue.offer(nxt);
					}
				} else {
					if (dist[nxt] > dist[cur] + 1) {
						dist[nxt] = dist[cur] + 1;
						queue.offer(nxt);
					}
				}
			}
		}
		System.out.println(dist[100]);
	}
}
