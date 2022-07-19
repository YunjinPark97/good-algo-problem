import java.io.*;
import java.util.*;
 
public class Main {
    
    static List<Bridge> bridges;
    static int[] p;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static int M, N, islandNum = 0, ans = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 섬의 개수를 세며, 각 섬의 칸을 1~islandNum으로 마킹하여 구분한다.
        visited = new boolean[M][N];
        islandNum = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                islandNum++;
                map[i][j] = islandNum;
                visited[i][j] = true;
                bfs(i, j, islandNum);
            }
        }
        
        // 가능한 다리 모두 만들기
        bridges = new ArrayList<>();
        findBridges();
        Collections.sort(bridges);    
        
        makeSet(islandNum+1);
        // 모든 섬이 연결되면 다리의 최소 길이, 아니면 -1 출력
        System.out.println(kruskal(bridges)? ans: -1);
    }
    
    static void bfs(int i, int j, int num) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir], nc = c + dc[dir];
                if (!isIn(nr, nc)) continue;
                if (visited[nr][nc] || map[nr][nc] == 0) continue;
                map[nr][nc] = num;
                visited[nr][nc] = true;
                queue.offer(new int[] {nr, nc});
            }
        }
    }
    
    // 가능한 모든 다리를 찾는 method
    // M, N이 작아서 효율성은 살짝 무시해도 됨
    static void findBridges() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int r = i + dr[k], c = j + dc[k];
                        int length = 0;
                        while (isIn(r, c)) {
                            if (map[r][c] == map[i][j]) break;
                            else if (map[r][c] > 0) {
                                if (length > 1) {
                                    bridges.add(new Bridge(map[i][j], map[r][c], length));
                                }
                                break;
                            }
                            length++;
                            r += dr[k];
                            c += dc[k];
                        }
                    }
                }
            }
        }
    }
    
    // 크루스칼을 수행하여, 모든 섬이 연결되면 true, 아니면 false 리턴
    static boolean kruskal(List<Bridge> bridges) {
        int cnt = 0;
        for (Bridge b: bridges) {
            if (!union(b.from, b.to)) continue;
            cnt++;
            ans += b.dist;
            if (cnt == islandNum-1) return true;
        }
        return false;
    }
    
    // Bridge class
    static class Bridge implements Comparable<Bridge> {
        int from;
        int to;
        int dist;
        
        public Bridge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
 
        @Override
        public int compareTo(Bridge o) {
            return this.dist - o.dist;
        }
 
        @Override
        public String toString() {
            return "Bridge [from=" + from + ", to=" + to + ", dist=" + dist + "]";
        }
    }
    
    static boolean isIn(int r, int c) {
        return !(r < 0 || r >= M || c < 0 || c >= N);
    }
    
    // Union-Find 관련 methods
    static void makeSet(int N) {
        p = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            p[i] = i;
        }
    }
    static int findRoot(int a) {
        if (a == p[a])
            return a;
        return p[a] = findRoot(p[a]);
    }
    static boolean union(int a, int b) {
        a = findRoot(a);
        b = findRoot(b);
        if (a == b)
            return false;
        p[b] = a;
        return true;
    }
}