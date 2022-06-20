import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		Deque<Integer> deque = new ArrayDeque<Integer>();

    // 맨 앞 자리부터, 앞 자리에 현재 수보다 작은 값이 있다면 제거함
		for (int i = 0; i < N; i++) {
			int num = str.charAt(i)-'0';
      // K개를 이미 다 제거했을 경우
			if (K == 0) {
				deque.offerLast(num);
				continue;
			}
      // 비어있는 경우
			if (deque.isEmpty()) deque.offerLast(num);
			else {
        // 앞 자리가 현재 수보다 작은 경우
				while (!deque.isEmpty() && deque.peekLast() < num && K > 0) {
					deque.pollLast();
					K--;
				}
				deque.offerLast(num);
			}
		}
		
    // K개가 없어지지 않았다면, 맨 뒤에서부터 수를 제거함
		while (K > 0) {
			deque.pollLast();
			K--;
		}
		
		while (!deque.isEmpty()) {
			sb.append(deque.pollFirst()+"");
		}
		System.out.println(sb.toString());
	}

}
