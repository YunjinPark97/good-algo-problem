import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		
		int[] nums = new int[N];
		int[] prev = new int[N];
		Arrays.fill(prev, -1);
		int[] res = new int[N+1]; // dp[1]일에 대한 최소값
		Arrays.fill(res, Integer.MAX_VALUE);
		int[] resIdx = new int[N+1];
		Arrays.fill(resIdx, -1);
		int maxIdx = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		res[1] = nums[0];
		resIdx[1] = 0;
		int size = 1;
		int ans = 1;
		for (int i = 1; i < N; i++) {
			int start = 1, end = size;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (res[mid] >= nums[i]) {
					end = mid - 1;
				}
				else {
					start = mid + 1;
				}
			}
			dp[i] = end + 1;
			if (end+1 > size) size++;
			if (nums[i] < res[end+1]) {
				res[end+1] = nums[i];
				resIdx[end+1] = i;
				prev[i] = resIdx[end];
			}
			if (ans < end + 1) {
				ans = end + 1;
				maxIdx = i;
			}
		}
		
		System.out.println(ans);
		
		List<Integer> list = new ArrayList<>();
		while (maxIdx != -1) {
			list.add(nums[maxIdx]);
			maxIdx = prev[maxIdx];
		}
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(list.size() - i - 1)+" ");
		}
		System.out.println(sb.toString());
	}

}