import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int cnt = 0;
		boolean[] rowVisited = new boolean[N], colVisited = new boolean[M];
		int rowCnt = 0, colCnt = 0;
		int[] rows = new int[N], cols = new int[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cnt++;
					if (!rowVisited[i]) {
						rowVisited[i] = true;
						rowCnt++;
					}
					if (!colVisited[j]) {
						colVisited[j] = true;
						colCnt++;
					}
					rows[i]++;
					cols[j]++;
				}
			}
		}
		
		if (cnt == 2*K) System.out.println(0);
		else {
			System.out.println(2*K - cnt);
			if (colCnt == 1) {
				int col = 0;
				for (int j = 0; j < M; j++) {
					if (cols[j] > 0) {
						col = j;
						break;
					}
				}
				int row = 0;
				for (int i = 0; i < N; i++) {
					if (map[i][col] == 1) {
						row = i;
						break;
					}
				}
				for (int i = row + (cnt - K); i - row - (cnt - K) < 2*K - cnt; i++) {
					System.out.println((i+1) + " " + (col+1));
				}
			}
			else if (rowCnt == 1) {
				int row = 0;
				for (int j = 0; j < N; j++) {
					if (rows[j] > 0) {
						row = j;
						break;
					}
				}
				int col = 0;
				for (int i = 0; i < M; i++) {
					if (map[row][i] == 1) {
						col = i;
						break;
					}
				}
				for (int i = col + (cnt - K); i - col - (cnt - K) < 2*K - cnt; i++) {
					System.out.println((row+1) + " " + (i+1));
				}
			}
			else {
				int row = 0;
				for (int i = 0; i < N; i++) {
					if (rows[i] > 1) {
						row = i;
						break;
					}
				}
				int col = 0;
				for (int j = 0; j < M; j++) {
					if (cols[j] > 1) {
						col = j;
						break;
					}
				}
				System.out.println((row+1) + " " + (col+1));
			}
		}
	}
}
