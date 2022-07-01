import java.io.*;
import java.util.*;

/*
시간 복잡도: O(N^2), 그러나 플로이드보다 오래 걸린다.
*/
public class main {

	static final int INF = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Integer>> superior = new ArrayList<>();
		List<List<Integer>> inferior = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			superior.add(new ArrayList<Integer>());
			inferior.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			superior.get(b).add(a);
			inferior.get(a).add(b);
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			int temp = 1;
			boolean[] visited = new boolean[N+1];
			Queue<Integer> queue = new ArrayDeque<>();
			queue.offer(i);
			visited[i] = true;
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (int nxt: inferior.get(cur)) {
					if (visited[nxt]) continue;
					temp++;
					visited[nxt] = true;
					queue.offer(nxt);
				}
			}
			visited = new boolean[N+1];
			queue.offer(i);
			visited[i] = true;
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (int nxt: superior.get(cur)) {
					if (visited[nxt]) continue;
					temp++;
					visited[nxt] = true;
					queue.offer(nxt);
				}
			}
			if (temp == N) ans++;
		}
		System.out.println(ans);
	}

}
