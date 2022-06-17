import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static int ans = Integer.MAX_VALUE;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] maze;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		maze = new char[M][N];
		for (int i = 0; i < M; i++) {
			maze[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (maze[i][j] == '0') {
					maze[i][j] = '.';
					bfs(i, j, 0);
				}
			}
		}
	
		System.out.println(ans==Integer.MAX_VALUE? -1: ans);
	}
	
	
	static void bfs(int i, int j, int curKeys) {
		boolean[][][] visited = new boolean[M][N][64];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j, curKeys});
		visited[i][j][curKeys] = true;
		int dist = 0;
		while (!queue.isEmpty()) {
			dist++;
			int size = queue.size();
			while (size-- > 0) {
				int[] temp = queue.poll();
				int r = temp[0];
				int c = temp[1];
				int keys = temp[2];
				for (int dir = 0; dir < 4; dir++) {
					int nr = r + dr[dir], nc = c + dc[dir];
					if (nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc][keys]) continue;
					if (maze[nr][nc] == '#') continue;
					if (maze[nr][nc] >= 'A' && maze[nr][nc] <= 'F') {
						if ((keys & (1<<(maze[nr][nc]-'A'))) == 0) continue;
						visited[nr][nc][keys] = true;
						queue.offer(new int[] {nr, nc, keys});
					}
					if (maze[nr][nc] == '.') {
						visited[nr][nc][keys] = true;
						queue.offer(new int[] {nr, nc, keys});
					}
					if (maze[nr][nc] >= 'a' && maze[nr][nc] <= 'f') {
						if ((curKeys & (1<<(maze[nr][nc]-'a'))) > 0) {
							visited[nr][nc][keys] = true;
							queue.offer(new int[] {nr, nc, keys});
						} else {
							visited[nr][nc][keys] = true;
							visited[nr][nc][keys|(1<<(maze[nr][nc]-'a'))] = true;
							queue.offer(new int[] {nr, nc, keys|(1<<(maze[nr][nc]-'a'))});
						}
					}
					if (maze[nr][nc] == '1') {
						ans = Math.min(ans, dist);
						return;
					}
				}
			}
		}
	}

}
