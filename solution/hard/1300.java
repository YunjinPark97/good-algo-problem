import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int target = Integer.parseInt(br.readLine());
		
		long start = 1;
		long end = 10_000_000_000L; // 100억 만번 더해도 long은 안 넘침
		while (start <= end) {
			long mid = (start + end) / 2;
			long temp = 0; // 10만 10만번 더하면 100억
			for (int i = 1; i <= N; i++) {
				temp += (mid/i >= N)? N: mid/i;
			}
			if (temp > target - 1) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		
		System.out.println(start);
	}

}