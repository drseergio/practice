import java.util.Scanner;
import java.io.File;

public class DFS {
  private boolean[] marked;
  private int count;

  public DFS(Graph g, int s) {
    marked = new boolean[g.V()];
    dfs(g, s);
  }

  private void dfs(Graph g, int v) {
    marked[v] = true;
    count++;
    for (int w : g.adj(v)) {
      if (!marked[w]) dfs(g, w);
    }
  }

  public boolean marked(int v) {
    return marked[v];
  }

  public int count() {
    return count;
  }

  public static void main(String[] args) throws Exception {
    Graph g = new Graph(new Scanner(new File("tinyG.txt")));
    DFS dfs = new DFS(g, Integer.parseInt(args[0]));
    for (int i = 0; i < g.V(); i++) {
      if (dfs.marked(i)) System.out.print(i + " ");
    }
    System.out.println();
    System.out.println("Count: " + dfs.count());
  }
}
