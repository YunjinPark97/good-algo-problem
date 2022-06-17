import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] ocean;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		BabyShark b = null;
		ocean = new int[N][N];
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ocean[i][j] = Integer.parseInt(st.nextToken());
				if (ocean[i][j] == 9) {
					b = new BabyShark(i, j, 2);
					ocean[i][j] = 0;
				}
			}
		}
		
		int ans = 0;
		int count = 0;
		while (true) {
			Graph g = new Graph(0, 0, Integer.MAX_VALUE);
			
			// BFS: Graph 클래스 기준 최단거리 하나 찾으면 됨.
			Graph tempG;
			boolean[][] visited = new boolean[N][N];
			int depth = 0;
			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(new int[] {b.row, b.col});
			visited[b.row][b.col] = true;

			while (!queue.isEmpty()) {
				int size = queue.size();
				if (g.dist < Integer.MAX_VALUE) break;
				depth++;
				while (size-- > 0) {
					int[] temp = queue.poll();
					int row = temp[0];
					int col = temp[1];
					for (int i = 0; i < 4; i++) {
						int nr = row + dr[i];
						int nc = col + dc[i];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if (ocean[nr][nc] > b.size || visited[nr][nc]) continue;
						visited[nr][nc] = true;
						queue.offer(new int[] {nr, nc});
						if (ocean[nr][nc] < b.size && ocean[nr][nc] > 0) {
							tempG = new Graph(nr, nc, depth); // depth: 구조적으로 이렇게 됨.
							if (g.compareTo(tempG) > 0) {
								g = new Graph(nr, nc, depth);
							}
						}
					}
				}
			}

			// graph가 갱신되지 않은 경우: 갈 수 있는 점이 없음, 종료
			if (g.dist == Integer.MAX_VALUE) break;
			
			// ans에 거리만큼 더해주고 물고기 먹었으니 그 자리는 0으로 초기화
			ans += g.dist;
			ocean[g.row][g.col] = 0;
			
			// count따라 size 늘릴지 안 늘릴지 결정, 아기상어 위치 바꿔주기
			if (++count == b.size) {
				b = new BabyShark(g.row, g.col, b.size + 1);
				count = 0;
			}
			else b = new BabyShark(g.row, g.col, b.size);
			
		}
		
		System.out.println(ans);
	}
	
	
	
	static class BabyShark{
		int row;
		int col;
		int size;
		
		public BabyShark(int row, int col, int size) {
			this.row = row;
			this.col = col;
			this.size = size;
		}
		
		void sing() {
			System.out.println("아기 상어 뚜루루뚜루~");
		}
	}
	
	
	static class Graph implements Comparable<Graph>{
		int row;
		int col;
		int dist;
		
		public Graph(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}

		@Override
		public int compareTo(Graph o) {
			if (this.dist == o.dist) {
				if (this.row == o.row) {
					return Integer.compare(this.col, o.col);
				}
				return Integer.compare(this.row, o.row);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}
	
}
