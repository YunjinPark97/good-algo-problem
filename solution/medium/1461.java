import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer> pos = new ArrayList<>();
		List<Integer> neg = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			if (cur < 0) neg.add(-cur);
			else pos.add(cur);
		}
		Collections.sort(pos, Collections.reverseOrder());
		Collections.sort(neg, Collections.reverseOrder());
		
		int ans = 0;
		if (pos.isEmpty()) ans += neg.get(0);
		else if (neg.isEmpty()) ans += pos.get(0);
		else {
			ans += 2 * Math.min(pos.get(0), neg.get(0));
			ans += Math.max(pos.get(0), neg.get(0));

		}
		
		for (int i = M; i < pos.size(); i += M)	{
			ans += 2 * pos.get(i);
		}
		for (int i = M; i < neg.size(); i += M) {
			ans += 2 * neg.get(i);
		}
		System.out.println(ans);
	}

}
