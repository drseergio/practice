import java.util.Scanner;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

public class DFSPaths {
  private boolean[] marked;
  private int[] edgeTo;
  private int s;

  public DFSPaths(Graph g, int s) {
    marked = new boolean[g.V()];
    edgeTo = new int[g.V()];
    this.s = s;
    dfs(g, s);
  }

  private void dfs(Graph g, int v) {
    marked[v] = true;
    for (int w : g.adj(v)) if (!marked[w]) {
      edgeTo[w] = v;
      dfs(g, w);
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

    DFSPaths search = new DFSPaths(g, s);
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
