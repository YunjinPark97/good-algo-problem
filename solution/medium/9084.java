import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int[] coins = new int[Integer.parseInt(br.readLine())];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < coins.length; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int[] dp = new int[M+1];
			dp[0] = 1;
			for (int i = 0; i < coins.length; i++) {
				for (int j = coins[i]; j < M+1; j++) {
					dp[j] += dp[j-coins[i]];
				}
			}
			System.out.println(dp[M]);
		}
	}

}
