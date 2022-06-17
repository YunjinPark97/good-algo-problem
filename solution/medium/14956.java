import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] answer = Hilbert(n, m);
		System.out.println(answer[0]+" "+answer[1]);
	}
	

	static int[] Hilbert(int size, int walk) { 
		if (size == 2) {
			if (walk == 1) return new int[] {1, 1};
			if (walk == 2) return new int[] {1, 2};
			if (walk == 3) return new int[] {2, 2};
			if (walk == 4) return new int[] {2, 1};
		}
		
		int area = size*size/4;
		if (walk <= area) {
			int[] temp = Hilbert(size/2, walk);
			return new int[] {temp[1], temp[0]};
		}
		if (walk <= 2*area) {
			int[] temp = Hilbert(size/2, walk - area);
			return new int[] {temp[0], size/2+temp[1]};
		}
		if (walk <= 3*area) {
			int[] temp = Hilbert(size/2, walk - 2*area);
			return new int[] {size/2+temp[0], size/2+temp[1]};
		}
		if (walk <= 4*area) {
			int[] temp = Hilbert(size/2, walk - 3*area);
			return new int[] {size - temp[1] + 1, size/2 - temp[0] + 1};
		}
		return new int[] {0};
	}
}