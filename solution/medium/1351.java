import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static Map<Long, Long> hMap = new HashMap<>();
	static long P, Q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());
		
		System.out.println(f(N));
	}
	
	public static long f(long N) {
		if (N == 0) return 1;
		
		if (hMap.containsKey(N)) return hMap.get(N);
		
		hMap.put(N, f(N/P) + f(N/Q));
		return hMap.get(N);
	}

}
