import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> nameToIdx = new HashMap<>();
		String[] idxToName = new String[N+1];
		String[] names = new String[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			String name = st.nextToken();
			nameToIdx.put(name, i);
			idxToName[i] = name;
			names[i-1] = name;
		}	
		Arrays.sort(names);
		
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		int M = Integer.parseInt(br.readLine());
		int[] indegree = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int child = nameToIdx.get(st.nextToken());
			int parent = nameToIdx.get(st.nextToken());
			graph.get(parent).add(child);
			indegree[child]++;
		}
		
		List<List<String>> children = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			children.add(new ArrayList<String>());
		}
		
		int cnt = 0;
		List<String> ancestors = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				cnt++;
				ancestors.add(idxToName[i]);
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int nxt: graph.get(cur)) {
				indegree[nxt]--;
				if (indegree[nxt] == 0) {
					children.get(cur).add(idxToName[nxt]);
					queue.offer(nxt);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(children.get(i));
		}
		Collections.sort(ancestors);
		
		sb.append(cnt+"\n");
		for (String ancestor: ancestors) {
			sb.append(ancestor+" ");
		}
		sb.append("\n");
		for (int i = 0; i < N; i++) {
			String name = names[i];
			sb.append(name+" ");
			sb.append(children.get(nameToIdx.get(name)).size()+" ");
			for (String child: children.get(nameToIdx.get(name))) {
				sb.append(child + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}