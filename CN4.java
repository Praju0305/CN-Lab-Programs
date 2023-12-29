//Bellman ford

import java.util.Arrays;
import java.util.Scanner;

public class CN4 {
	static int N;
	static int[][] cost;

	static void bellmanFord(int src) {
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		for (int i = 0; i < N; i++)
			for (int u = 0; u < N; u++)
				for (int v = 0; v < N; v++)
					if (dist[u] != Integer.MAX_VALUE && dist[u] + cost[u][v] < dist[v])
						dist[v] = dist[u] + cost[u][v];
		for (int u = 0; u < N; u++)
			for (int v = 0; v < N; v++)
				if (dist[u] != Integer.MAX_VALUE && dist[u] + cost[u][v] < dist[v]) {
					System.out.println("Negative weight cycle detected.");
					return;
				}
		System.out.println("Vertex\tDistance from source");
		for (int i = 0; i < N; i++)
			System.out.println(i + "\t" + dist[i]);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of vertices : ");
		N = in.nextInt();
		cost = new int[N][N];
		System.out.println("Enter the cost matrix : ");
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				cost[i][j] = in.nextInt();
		System.out.print("Enter the source vertex : ");
		int src = in.nextInt();
		bellmanFord(src);

	}
}
