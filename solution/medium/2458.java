import java.io.*;
import java.util.*;

/*
플로이드-워셜 알고리즘 시행 후 연결 되어 있는 두 점은 서로 키의 우열이 있다.
O(N^3)
*/
public class main {

	static final int INF = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
    // map[i][j]: i번째 학생이 j번째 학생보다 키가 작으면 INF보다 작음.
		int[][] map = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			map[a][b] = 1;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			int temp = 0;
			for (int j = 1; j <= N; j++) {
				if (map[i][j] < INF || map[j][i] < INF) temp++;
			}
			if (temp == N) ans++;
		}
		System.out.println(ans);
	}
}
