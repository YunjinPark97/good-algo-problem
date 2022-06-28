// exchage argument에 따라 푸는 것이 정해
// http://www.secmem.org/blog/2019/11/16/Exchange-argument/

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int max = 0;
		Number[] nums = new Number[K];
    // K개를 주어진 원소 한 번씩 사용하고
    // 나머지 N-K개는 가장 값이 큰 원소를 사용한다.
		for (int i = 0; i < K; i++) {
			int num = Integer.parseInt(br.readLine());
			nums[i] = new Number(num, convert(num), 1);
			if (num > max) max = num;
		}
		
		for (int i = 0; i < K; i++) {
			if (nums[i].num == max) {
				nums[i].count += (N-K);
				break;
			}
		}
		Arrays.sort(nums);
		
		for (int i = K-1; i >= 0; i--) {
			for (int j = 0; j < nums[i].count; j++) {
				sb.append(nums[i].num+"");
			}
		}
		System.out.println(sb.toString());	
	}

	// num: 값, size: '실제로 붙일 때의 크기', count: 몇 번 사용되는 지
	static class Number implements Comparable<Number>{
		int num;
		long size;
		int count;
		
		public Number(int num, long size, int count) {
			this.num = num;
			this.size = size;
			this.count = count;
		}

		@Override
		public int compareTo(Number o) {
			return Long.compare(this.size, o.size);
		}
	}
	
  // 동등한 '크기'를 갖는 10자리 수로 변환
	static long convert(int num) {
		String sNum = num+"";
		long ret = 0;
		for (int i = 0; i < 10; i++) {
			ret *= 10;
			ret += (sNum.charAt((i%sNum.length()))-'0');
		}
		return ret;
	}
}
