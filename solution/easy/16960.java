import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Integer>> switches = new ArrayList<>();
		switches.add(new ArrayList<>());
		int[] lamps = new int[M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			switches.add(new ArrayList<>());
			for (int j = 0; j < num; j++) {
				int temp = Integer.parseInt(st.nextToken());
				switches.get(i).add(temp);
				lamps[temp]++;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int lamp: switches.get(i)) {
				lamps[lamp]--;
			}
			boolean isAll = true;
			for (int j = 1; j <= M; j++) {
				if (lamps[j] < 1) {
					isAll = false;
					break;
				}
			}
			if (isAll) {
				System.out.println(1);
				return;
			}
			for (int lamp: switches.get(i)) {
				lamps[lamp]++;
			}
		}
		System.out.println(0);
	}
}
