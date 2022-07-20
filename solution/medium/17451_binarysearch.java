import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		long l = 1, r = (long) 1e15;
		bs: while (l <= r) {
			long mid = (l+r)/2;
			long val = mid;
			if (mid < nums[0]) {
				l = mid + 1;
				continue bs;
			}
			for (int i = 0; i+1 < n; i++) {
				val -= (val % nums[i]);
				if (val < nums[i+1]) {
					l = mid + 1;
					continue bs;
				}
			}
			r = mid - 1;
		}
		System.out.println(l);
	}

}
