import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		int M = A.length(), N = B.length();
		Stack<String> letters = new Stack<>();
		
		int[][] dp = new int[M+1][N+1];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (A.charAt(i-1) == B.charAt(j-1)) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		sb.append(dp[M][N]+"\n");
		
		int row = M, col = N;
		while (row > 0 && col > 0) {
			if (dp[row][col] == dp[row-1][col]) {
				row--;
				continue;
			}
			if (dp[row][col] == dp[row][col-1]) {
				col--;
				continue;
			}
			letters.add(A.substring(row-1, row));
			row--;
			col--;
			continue;
		}
		while (!letters.isEmpty()) {
			sb.append(letters.pop());
		}
		
		System.out.println(sb);
	}

}
