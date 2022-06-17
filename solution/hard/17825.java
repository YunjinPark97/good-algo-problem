import java.io.*;
import java.util.*;

public class Main {
	static List<int[]> route;
	
	public static void main(String[] args) throws IOException{
		int[] route0 = {-1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0};
		int[] route1 = {10, 13, 16, 19, 25, 30, 35, 40, 0};
		int[] route2 = {20, 22, 24, 25, 30, 35, 40, 0};
		int[] route3 = {30, 28, 27, 26, 25, 30, 35, 40, 0};
		route = new ArrayList<>();
		route.add(route0);
		route.add(route1);
		route.add(route2);
		route.add(route3);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] moves = new int[10];
		for (int i = 0; i < 10; i++) {
			moves[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		outer: for (int i = 0; i < (1<<18); i++) {
			List<Unit> units = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				units.add(new Unit());
			}
			int choose = i;
			int temp = 0;
			for (int j = 0; j < 10; j++) {
				Unit curUnit = (j == 0)? units.get(0): units.get(choose & ((1<<2)-1));
				if (curUnit.isEnd) continue outer; 
				
				curUnit.location += moves[j];
				if ((curUnit.location == 5 || curUnit.location == 10 || curUnit.location == 15) && curUnit.route == 0) {
					curUnit.route += (curUnit.location / 5);
					curUnit.location = 0;
				}
				
				if ((curUnit.route == 0 && curUnit.location > 20) || (curUnit.route == 2 && curUnit.location > 6) 
						|| (curUnit.route % 2 == 1 && curUnit.location > 7)) {
					curUnit.isEnd = true;
					curUnit.location = route.get(curUnit.route).length-1; // 길이 제한
				}
				
				if (!curUnit.isEnd) {
					for (int k = 0; k < 4; k++) {
						if (j == 0) continue; 
						if (k == (choose & ((1<<2)-1))) continue;
						if (isSameLocation(curUnit, units.get(k))) continue outer;
					}
				}

				temp += route.get(curUnit.route)[curUnit.location];
				if (j > 0) choose>>=2;
			}
			
			ans = Math.max(temp, ans);
		}
		
		System.out.println(ans);
	}
	
	static class Unit {
		int location;
		int route;
		boolean isEnd;
		
		public Unit() {
			location = 0;
			route = 0;
			isEnd = false;
		}
	}
 
	static boolean isSameLocation(Unit u1, Unit u2) {
		if (route.get(u1.route)[u1.location] == 25 || route.get(u1.route)[u1.location] == 35 
			|| route.get(u1.route)[u1.location] == 40) {
			return route.get(u1.route)[u1.location] == route.get(u2.route)[u2.location];
		} else if (route.get(u1.route)[u1.location] == 30 && route.get(u2.route)[u2.location] == 30) {
			return (u1.location == 0 && u2.location == 0) || (u1.location > 0 && u2.location > 0);
		}
		return (u1.route == u2.route && u1.location == u2.location);
	}
}
