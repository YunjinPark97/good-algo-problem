import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int ans = N+1;
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0, end = 0, sum = nums[0];
		
		while (true) {
			if (sum >= S) {
				ans = Math.min(ans, end - start + 1);
				if (start == end) break;
				sum -= nums[start++];
			}
			else {
				end++;
				if (end >= N) break;
				sum += nums[end];
			}
		}
		
		System.out.println((ans>N)? 0: ans);
	}

}