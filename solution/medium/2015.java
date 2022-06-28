import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] sum = new int[N];
		sum[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		long ans = 0;
		HashMap<Integer, Long> hMap = new HashMap<>();
    // 자기 자신이 K인 경우도 감안하기 위해 넣어준다.
		hMap.put(0, 1L);
    // i = m에서, hMap에는 sum[0], ..., sum[m-1]이 담겨 있음.
    // 따라서, sum[m] - K이 존재한다면
    // 적당한 l<m이 존재하여 sum[m] - K = sum[l], 즉
    // K = sum[m] - sum[l]이라는 뜻이다.
		for (int i = 0; i < N; i++) {
			if (hMap.containsKey(sum[i] - K)) {
				ans += hMap.get(sum[i] - K);
			}
			
			if (hMap.containsKey(sum[i])) {
				hMap.put(sum[i], hMap.get(sum[i]) + 1);
			} else {
				hMap.put(sum[i], 1L);
			}
		}
		System.out.println(ans);
	}
}
