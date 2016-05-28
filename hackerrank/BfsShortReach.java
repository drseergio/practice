import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * BFS Short Reach:
 * https://www.hackerrank.com/challenges/bfsshortreach
 */
public class BfsShortReach {
  private static class Graph {
    private List[] adj;
    final int V;

    Graph(int V) {
      adj = new List[V];
      for (int i = 0; i < V; i++)
        adj[i] = new LinkedList<Integer>();
      this.V = V;
    }

    void addEdge(int v, int w) {
      adj[v].add(w);
      adj[w].add(v);
    }

    Iterable<Integer> adj(int v) {
      return adj[v];
    }
  }

  private static class Bfs {
    public static final int DEFAULT_DIST = 6;
    private boolean[] marked;
    private int[] distTo;

    Bfs(Graph g, int s) {
      marked = new boolean[g.V];
      distTo = new int[g.V];
      for (int i = 0; i < g.V; i++)
        distTo[i] = DEFAULT_DIST;
      distTo[s] = 0;
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(s);
      marked[s] = true;

      while (!q.isEmpty()) {
        int v = q.remove();
        for (int w : g.adj(v)) {
          if (!marked[w]) {
            distTo[w] += distTo[v];
            marked[w] = true;
            q.add(w);
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    for (int c = scanner.nextInt(); c > 0; c--) {
      int V = scanner.nextInt();
      int E = scanner.nextInt();
      Graph g = new Graph(V);
      for (int i = 0; i < E; i++) {
        g.addEdge(scanner.nextInt()-1, scanner.nextInt()-1);
      }
      int S = scanner.nextInt()-1;
      Bfs bfs = new Bfs(g, S);
      for (int v = 0; v < V; v++) {
        if (v == S) continue;
        if (!bfs.marked[v]) System.out.print("-1 ");
        else System.out.print(bfs.distTo[v] + " ");
      }
      System.out.println();
    }
    scanner.close();
  }
}
