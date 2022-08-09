import java.io.*;
import java.util.*;

public class Main {

	static char[][] board;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int holeR, holeC;
	static int M, N;
	static int ans = 100;
	static int[][][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new char[M][N];
		visited = new int[M][N][M][N];
		int redR = 0, redC = 0, blueR = 0, blueC = 0;
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = str.charAt(j);
				if (board[i][j] == 'R') {
					redR = i;
					redC = j;
				}
				if (board[i][j] == 'B') {
					blueR = i;
					blueC = j;
				}
				if (board[i][j] == 'O') {
					holeR = i;
					holeC = j;
				}
			}
		}
		
		dfs(0, redR, redC, blueR, blueC, 4);
		System.out.println((ans == 100)? -1: ans);
	}
	
	static void dfs(int cnt, int rR, int rC, int bR, int bC, int prevDir) {
		if (visited[rR][rC][bR][bC] < cnt - 100) return;
		visited[rR][rC][bR][bC] = cnt - 100;
		
		if (rR == holeR && rC == holeC && (rR != bR || rC != bC)) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if (cnt == 100 || (rR == bR && rC == bC) || (bR == holeR && bC == holeC)) {
			return;
		}
		
		for (int dir = 0; dir < 4; dir++) {
			if (prevDir == dir) continue;
			int[] newR = new int[] {1, 1}, newB = new int[] {1, 1};
			if ((dir == 0 && rR < bR) || (dir == 1 && rC < bC) || (dir == 2 && rR > bR) || (dir == 3 && rC > bC)) {
				newR = move(rR, rC, dir, 'R');
				newB = move(bR, bC, dir, 'B');
			} else {
				newB = move(bR, bC, dir, 'B');
				newR = move(rR, rC, dir, 'R');
			}
			
			dfs(cnt+1, newR[0], newR[1], newB[0], newB[1], dir);
			
			if (newR[0] != holeR || newR[1] != holeC) board[newR[0]][newR[1]] = '.';
			if (newB[0] != holeR || newB[1] != holeC) board[newB[0]][newB[1]] = '.';
			board[rR][rC] = 'R';
			board[bR][bC] = 'B';
		}
	}
	
	static int[] move(int r, int c, int dir, char color) {
		if (dir == 0) {
			for (int i = r-1; i >= 0; i--) {
				if (!(board[i][c] == '.' || board[i][c] == 'O')) {
					board[r][c] = '.';
					board[i+1][c] = color;
					return new int[] {i+1, c};
				} else if (board[i][c] == 'O') {
					board[r][c] = '.';
					return new int[] {i, c};
				} 
			}
			return new int[] {1, c};
		} else if (dir == 1) {
			for (int j = c-1; j >= 0; j--) {
				if (!(board[r][j] == '.' || board[r][j] == 'O')) {
					board[r][c] = '.';
					board[r][j+1] = color;
					return new int[] {r, j+1};
				} else if (board[r][j] == 'O') {
					board[r][c] = '.';
					return new int[] {r, j};
				} 
			}
			return new int[] {r, 1};
		} else if (dir == 2) {
			for (int i = r+1; i < M; i++) {
				if (!(board[i][c] == '.' || board[i][c] == 'O')) {
					board[r][c] = '.';
					board[i-1][c] = color;
					return new int[] {i-1, c};
				} else if (board[i][c] == 'O') {
					board[r][c] = '.';
					return new int[] {i, c};
				} 
			}
			return new int[] {M-2, c};
		} else {
			for (int j = c+1; j < N; j++) {
				if (!(board[r][j] == '.' || board[r][j] == 'O')) {
					board[r][c] = '.';
					board[r][j-1] = color;
					return new int[] {r, j-1};
				} else if (board[r][j] == 'O') {
					board[r][c] = '.';
					return new int[] {r, j};
				} 
			}
			return new int[] {r, N-2};
		}
	}
}
