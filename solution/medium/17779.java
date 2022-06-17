import java.io.*;
import java.util.*;

public class Main {

	static int ans = Integer.MAX_VALUE, N;
	static int[] areas; // 1씩 빼서 넣기
	static int[][] city = new int[N+1][N+1];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		city = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve();
		System.out.println(ans);
	}
	
	static void solve() {
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= y-1; d1++) {
					for (int d2 = 1; d2 <= Math.min(N-y, N-x-d1); d2++) {
						areas = new int[5];
						calc(x, y, d1, d2);
					}
				}
			}
		}
	}
	
	static void calc(int x, int y, int d1, int d2) {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {				
				if (r < x+d1) {
					if (c <= y && c < y-r+x) {
						areas[0] += city[r][c];
						continue;
					}
				} else {
					if (c < y-d1+d2 && c < y+r-x-2*d1) {
						areas[2] += city[r][c];
						continue;
					}
				}
				
				if (r <= x+d2) {
					if (c > y && c > y+r-x) {
						areas[1] += city[r][c];
						continue;
					}
				} else {
					if (c >= y-d1+d2 && c > y-r+x+2*d2) {
						areas[3] += city[r][c];
						continue;
					}
				}
				areas[4] += city[r][c];
			}
		}
		
		Arrays.sort(areas);
		ans = Math.min(ans, areas[4] - areas[0]);
	}
}