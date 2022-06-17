import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int depth = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			queue.offer(temp);
			visited[temp] = true;
		}
		while (!queue.isEmpty()) {
			//System.out.println(Arrays.toString(visited));
			int size = queue.size();
			depth++;
			while(size-- > 0) {
				int temp = queue.poll();
				int x = 1;
				while (x <= N) {
					if ((x^temp) <= N && !visited[x^temp]) {
						visited[x^temp] = true;
						queue.offer(x^temp);
					}
					x =  x<<1;
				}
			}
		}
		
		System.out.println(depth-1);
		
	}

}