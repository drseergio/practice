import java.util.Scanner;
import java.io.File;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;

public class BFSPaths {
  private boolean[] marked;
  private int[] edgeTo;
  private int s;

  public BFSPaths(Graph g, int s) {
    marked = new boolean[g.V()];
    edgeTo = new int[g.V()];
    this.s = s;
    bfs(g, s);
  }

  private void bfs(Graph g, int s) {
    Queue<Integer> queue = new LinkedList<Integer>();
    marked[s] = true;
    queue.add(s);

    while (!queue.isEmpty()) {
      int v = queue.remove();
      for (int w : g.adj(v)) if (!marked[w]) {
        marked[w] = true;
        edgeTo[w] = v;
        queue.add(w);
      }
    }
  }

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public Deque<Integer> pathTo(int v) {
    if (!hasPathTo(v)) return null;
    Deque<Integer> path = new LinkedList<Integer>();
    for (int x = v; x != s; x = edgeTo[x]) path.push(x);
    path.push(s);
    return path;
  }

  public static void main(String[] args) throws Exception {
    Graph g = new Graph(new Scanner(new File("tinyG.txt")));
    int s = Integer.parseInt(args[0]);

    BFSPaths search = new BFSPaths(g, s);
    for (int v = 0; v < g.V(); v++) {
      if (search.hasPathTo(v)) {
        System.out.print(s + " to " + v + ": ");
        for (int x : search.pathTo(v)) {
          if (x == s) System.out.print(x);
          else System.out.print("-" + x);
        }
        System.out.println();
      }
    }
  }
}
