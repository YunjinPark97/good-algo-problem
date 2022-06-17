import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] divide, pop;
	static List<List<Integer>> graph;
	static boolean[] visited, choices;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		divide = new int[(1<<N)]; // 각 조합별 인구수를 기록(불가능: 0)
		pop = new int[N+1];
		for (int i = 1; i <= N; i++) {
			pop[i] = Integer.parseInt(st.nextToken()); 
		}
		
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int J = Integer.parseInt(st.nextToken());
			for (int j = 0; j < J; j++) {
				graph.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		makeChoice();
		minDiff();
	}
	
	// 1. 2^N - 2개의 조합을 선택(모두 선택 & 모두 안 선택은 제외)
	static void makeChoice() {
		for (int i = 1; i < (1<<N)-1; i++) {
			int temp = i;
			visited = new boolean[N+1];
			choices = new boolean[N+1];
			for (int j = 1; j <= N; j++) {
				choices[j] = ((temp & 1) == 1);
				temp >>= 1;
			}
			
			for (int j = 1; j <= N; j++) {
				if (choices[j]) {
					bfs(j, i);
					break;
				}
			}
		}
		
	}
	
	// 2. 선택한 조합끼리 연결할 수 있는지 확인
	static void bfs(int start, int comb) {
		Queue<Integer> queue = new ArrayDeque<>();
		visited[start] = true;
		queue.offer(start);
		while (!queue.isEmpty()) {
			int from = queue.poll();
			for (int to: graph.get(from)) {
				if (visited[to] || !choices[to]) continue;
				visited[to] = true;
				queue.offer(to);
			}
		}
		// 조합의 모든 원소에 방문할 수 없다면 기록하지 않음.
		for (int i = 1; i <= N; i++) {
			if (choices[i] && !visited[i]) {
				return;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (choices[i]) divide[comb] += pop[i];
		}
	}
	
	// 3. 최소 차이를 구한다. ans가 갱신되지 않으면 해가 없음: -1 출력    
	static void minDiff() {
		for (int i = 1; i < (1<<(N-1)); i++) {
			int idx1 = i, idx2 = (1<<N) - 1- i;
			if (divide[idx1] > 0 && divide[idx2] > 0) {
				ans = Math.min(ans, Math.abs(divide[idx1] - divide[idx2]));
			}
		}
		System.out.println(ans==Integer.MAX_VALUE? -1: ans);
	}
}
