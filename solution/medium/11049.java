import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N][2];
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken()); // row
			matrix[i][1] = Integer.parseInt(st.nextToken()); // col
		}
		
		for (int j = 1; j < N; j++) {
			for (int i = 0; i < N-j; i++) {
				int temp = Integer.MAX_VALUE;
				for (int k = 0; k < j; k++) {
					temp = Math.min(temp, dp[i][i+k] + dp[i+k+1][i+j] + matrix[i][0] * matrix[i+k][1] * matrix[i+j][1]);
				}
				dp[i][i+j] = temp;
			}
		}
		System.out.println(dp[0][N-1]);
	}

}