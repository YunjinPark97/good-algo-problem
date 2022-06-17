import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N+M; i++) {
			graph.add(new ArrayList<Integer>());
		}
		boolean[] visited = new boolean[N+1+M];
		int[] distance = new int[N+1+M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				int temp = Integer.parseInt(st.nextToken());
				graph.get(temp).add(N+i+1);
				graph.get(N+i+1).add(temp);
			}
		}
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(1);
		visited[1] = true;
		distance[1] = 1;
		while (!queue.isEmpty()) {
			int from = queue.poll();
			for (int to: graph.get(from)) {
				if (visited[to]) continue;
				visited[to] = true;
				distance[to] = distance[from]+1;
				queue.offer(to);
			}
		}		
		System.out.println(visited[N]? distance[N]/2 + 1: -1);
	}

}