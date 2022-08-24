import java.io.*;
import java.util.*;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		String str = br.readLine();
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph.get(s).add(e);
			graph.get(e).add(s);
		}
		int[] parents = new int[N+1];
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		int last = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			int max = 'a' - 1;
			List<Integer> nxts = new ArrayList<>();
			while (size-- > 0) {
				int cur = queue.poll();
				for (int nxt: graph.get(cur)) {
					if (parents[nxt] > 0 || nxt == 1) continue;
					parents[nxt] = cur;
					if ((int) str.charAt(nxt-1) > max) {
						last = nxt;
						max = str.charAt(nxt-1);
						nxts.clear();
						nxts.add(nxt);
					} else if ((int) str.charAt(nxt-1) == max) {
						nxts.add(nxt);
					}
				}
			}
			for (int nxt: nxts) {
				queue.offer(nxt);
			}
		}
		
		while (last != 0) {
			sb.append(str.charAt(last-1));
			last = parents[last];
		}
		System.out.println(sb.reverse().toString());
	}
}
