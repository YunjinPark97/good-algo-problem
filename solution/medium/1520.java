import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static int[][] dp;
	static int M, N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dfs(0, 0));
	}
	
	
	static int dfs(int r, int c) {
		if (dp[r][c] != -1) return dp[r][c];
		dp[r][c] = 0;
		
		if (r == M-1 && c == N-1) {
			dp[r][c] = 1;
			return dp[r][c];
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i], nc = c + dc[i];
			if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
			if (map[r][c] > map[nr][nc]) {
				dp[r][c] += dfs(nr, nc);
			}	
		}
        
		return dp[r][c];
	}
}