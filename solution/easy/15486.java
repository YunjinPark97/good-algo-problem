import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] days = new int[N+1];
		int[] pays = new int[N+1];
		int[] dp = new int[N+2];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			pays[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = N; i > 0; i--) {
			if (i + days[i] > N+1) {
				dp[i] = dp[i+1];
			} else {
				dp[i] = Math.max(dp[i+1], pays[i] + dp[i+days[i]]);
			}
		}
		System.out.println(dp[1]);
	}
}