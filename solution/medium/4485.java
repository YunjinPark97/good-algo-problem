import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 0;
		int N;
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			t++;
			int[][] dp = new int[N][N];
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				Arrays.fill(dp[i], Integer.MAX_VALUE);
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp;
				}
			}
		
			Queue<Point> queue = new ArrayDeque<>();
			dp[0][0] = map[0][0];
			queue.offer(new Point(0, 0, dp[0][0]));
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				int r = p.x;
				int c = p.y;
				int dist = p.dist;
				if (dist > dp[r][c]) continue;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i], nc = c + dc[i];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (dp[nr][nc] <= map[nr][nc] + dp[r][c]) continue;
					dp[nr][nc] = map[nr][nc] + dp[r][c];
					queue.offer(new Point(nr, nc, map[nr][nc] + dp[r][c]));
				}
			}

			System.out.printf("Problem "+t+": "+dp[N-1][N-1]+"\n");
		}
	}
	

	static class Point{
		int x;
		int y;
		int dist;
		
		public Point(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

}