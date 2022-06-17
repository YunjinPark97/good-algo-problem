import java.io.*;
import java.util.*;

public class Main {
	static long[] histogram;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if (N == 0) break;
			
			histogram = new long[N];
			for (int i = 0; i < N; i++) {
				histogram[i] = Long.parseLong(st.nextToken());
			}
			
			System.out.println(f(0, N-1));
			
		}
	}
	
	static long f(int start, int end) {
		if (start >= end) return histogram[start];
		int mid = (start + end) / 2;
		long left = f(start, mid-1);
		long right = f(mid+1, end);
		
		long center = histogram[mid];
		int l = mid, r = mid;
		int width = 1;
		long height = histogram[mid];
		while (l >= start && r <= end) {
			if (l == start) {
				while (r < end) {
					r++;
					width++;
					height = Math.min(height, histogram[r]);
					center = Math.max(center, height*width);
				}
				break;
			}
			if (r == end) {
				while (l > start) {
					l--;
					width++;
					height = Math.min(height, histogram[l]);
					center = Math.max(center, height*width);
				}
				break;
			}
			
			if (histogram[l-1] >= histogram[r+1]) {
				width++;
				height = Math.min(height, histogram[--l]);
				center = Math.max(center, height*width);
			}
			else {
				width++;
				height = Math.min(height, histogram[++r]);
				center = Math.max(center, height*width);
			}
		}
		
		return Math.max(left, Math.max(center, right));
	}

}