import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
    // dist[i][j][k]: i에서 j로 k 이하의 점들을 거쳐 가는 최소 거리
		int[][][] dist = new int[N+1][N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (i == j) continue;
				dist[i][j][0] = ((temp == 0)? INF: temp);
			}
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j][k] = Math.min(dist[i][j][k-1], dist[i][k][k-1] + dist[k][j][k-1]);
				}
			}
		}
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (dist[s][e][C-1] == INF) sb.append(-1+"\n");
			else sb.append(dist[s][e][C-1]+"\n");
		}
		System.out.println(sb.toString());
	}
}
