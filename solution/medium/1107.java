import java.io.*;
import java.util.*;

public class Main {
	
	static int ans = 1000000;
	static boolean[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		nums = new boolean[10];
		Arrays.fill(nums, true);
		StringTokenizer st = null ;
		if (M > 0) st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			nums[Integer.parseInt(st.nextToken())] = false;
		}
		
		ans = Math.abs(N-100);
		for (int i = 0; i <= 9; i++) {
			if (!nums[i]) continue;
			dfs(i, N, 1);
		}
		System.out.println(ans);
	}
	
	static void dfs(int cur, int N, int len) {
		if (len >= 7) return;
		ans = Math.min(ans, Math.abs(N-cur) + len);
		for (int i = 0; i <= 9; i++) {
			if (!nums[i]) continue;
			dfs(10*cur+i, N, len+1);
		}
	}
}
