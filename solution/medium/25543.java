import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<int[]> points = new ArrayList<>();
		int[] first = new int[N], second = new int[N], third = new int[N], fourth = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points.add(new int[] {x, y});
		}
		
		Collections.sort(points, (o1, o2)-> {
			return o1[0] - o2[0];
		});
		Arrays.fill(first, 1);
		Arrays.fill(second, 1);
		Arrays.fill(third, 1);
		Arrays.fill(fourth, 1);

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (points.get(i)[0] > points.get(j)[0] && points.get(i)[1] > points.get(j)[1]) {
					third[i] = Math.max(third[j] + 1, third[i]);
				}
				if (points.get(i)[0] > points.get(j)[0] && points.get(i)[1] < points.get(j)[1]) {
					second[i] = Math.max(second[j] + 1, second[i]);
				}
			}
		}
		for (int i = N-2; i >= 0; i--) {
			for (int j = N-1; j > i; j--) {
				if (points.get(i)[0] < points.get(j)[0] && points.get(i)[1] < points.get(j)[1]) {
					first[i] = Math.max(first[j] + 1, first[i]);
				}
				if (points.get(i)[0] < points.get(j)[0] && points.get(i)[1] > points.get(j)[1]) {
					fourth[i] = Math.max(fourth[j] + 1, fourth[i]);
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (first[i] > 1 && second[i] > 1 && third[i] > 1 && fourth[i] > 1) {
				int temp = first[i] + second[i] + third[i] + fourth[i];
				if (temp > max) {
					max = temp;
				}
			}
		}
		System.out.println((max == 0)? -1: N - max + 3);
	}
}
