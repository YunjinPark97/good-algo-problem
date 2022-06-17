import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited1, visited2;
	static int N;
	static int[][] chessboard;
	static int maxCountOdd = 0;
	static int maxCountEven = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		chessboard = new int[N][N];
		visited1 = new boolean[2*N-1];
		visited2 = new boolean[2*N-1];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				chessboard[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0, 0);
		dfs(0, 1, 0);
		System.out.println(maxCountEven + maxCountOdd);
	}

	static void dfs(int row, int col, int count) {
		int idx = 0;
		while (N*row + col + idx < N*N) {
			int nr = (N*row + col + idx) / N;
			int nc = (N*row + col + idx++) % N;
			if (Math.abs(nr+nc-row-col) % 2 == 1) continue;
			if (chessboard[nr][nc] == 0 || visited1[nr+nc] || visited2[N-1-nr+nc]) continue;
			visited1[nr+nc] = true;
			visited2[N-1-nr+nc] = true;
			dfs(nr, nc, count+1);
			visited1[nr+nc] = false;
			visited2[N-1-nr+nc] = false;
		}
		if ((row + col) % 2 == 0) maxCountEven = Math.max(count, maxCountEven);
		else if ((row + col) % 2 == 1) maxCountOdd = Math.max(count, maxCountOdd);
		return;
	}
}