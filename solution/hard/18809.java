import java.io.*;
import java.util.*;

public class Main {
	static int M, N, G, R, K, ans, tempAns;
	static int[][] gaarden, tempGaarden;
	static int[] canGrow, reds, greens;
	static int[] choice;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		greens = new int[G];
		reds = new int[R];
		gaarden = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				gaarden[i][j] = Integer.parseInt(st.nextToken());
				if (gaarden[i][j] == 2) K++;
			}
		}
		
		canGrow = new int[K];
		int idx = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (gaarden[i][j] == 2) canGrow[idx++] = N*i + j;
			}
		}
		
		dfs(0, 0, 0);
		System.out.println(ans);
	}
	
	
	static void dfs(int redCnt, int greenCnt, int start) {
		if (R+G-redCnt-greenCnt > K - start) {
			return;
		}
		
		if (redCnt + greenCnt == R+G) {
			tempAns = 0;
			tempGaarden = copy();
			
			// 꽃: 5, G:3, R:4, 안 마른 G:-3, 안마른 R:-4
			for (int i = 0; i < G; i++) {
				tempGaarden[greens[i]/N][greens[i]%N] = 3;
			}
			for (int i = 0; i < R; i++) {
				tempGaarden[reds[i]/N][reds[i]%N] = 4;
			}
			visited = new boolean[M][N];
			bfs();
		}
		
		if (redCnt < R) {
			for (int i = start; i < K; i++) {
				reds[redCnt] = canGrow[i];
				dfs(redCnt+1, greenCnt, i+1);
			}
		}
		
		if (greenCnt < G) {
			for (int i = start; i < K; i++) {
				greens[greenCnt] = canGrow[i];
				dfs(redCnt, greenCnt+1, i+1);
			}
		}
	}
	
	
	static int[][] copy(){
		int[][] tempGaarden = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				tempGaarden[i][j] = gaarden[i][j]; 
			}
		}
		return tempGaarden;
	}
	
	
	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && (tempGaarden[i][j] == 3 || tempGaarden[i][j] == 4)) {
					visited[i][j] = true;
					queue.offer(new int[] {i, j, tempGaarden[i][j]});
				}
			}
		}
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] temp = queue.poll();
				int r = temp[0], c = temp[1], color = temp[2];
				if (tempGaarden[r][c] == 5) continue;
				tempGaarden[r][c] = color;
				visited[r][c] = true;
				queue.offer(new int[] {r, c, color});
			}
			size = queue.size();
			while (size-- > 0) {
				int[] temp = queue.poll();
				int r = temp[0], c = temp[1], color = temp[2];
				for (int dir = 0; dir < 4; dir++) {
					int nr = r + dr[dir], nc = c + dc[dir];
					if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
					if (visited[nr][nc]) continue;
					if (tempGaarden[nr][nc] == 0 || tempGaarden[nr][nc] >= 3) continue;
					
					if (tempGaarden[nr][nc] == 1 || tempGaarden[nr][nc] == 2) {
						tempGaarden[nr][nc] = -tempGaarden[r][c];
						queue.offer(new int[] {nr, nc, color});
					} else if (tempGaarden[nr][nc] - tempGaarden[r][c] == -7) {
						tempGaarden[nr][nc] = 5;
						tempAns++;
					}
				}
			}			
		}
		ans = Math.max(ans, tempAns);		
	}
}
