import java.util.Scanner;
import java.io.File;

public class Cycle {
  private boolean[] marked;
  private boolean hasCycle;

  public Cycle(Graph g) {
    marked = new boolean[g.V()];
    for (int s = 0; s < g.V(); s++) {
      dfs(g, s, s);
    }
  }

  private void dfs(Graph g, int v, int u) {
    marked[v] = true;
    for (int w : g.adj(v)) {
      if (!marked[w]) dfs(g, w, v);
      else if (w != u) hasCycle = true;
    }
  }

  public boolean hasCycle() {
    return hasCycle;
  }

  public static void main(String[] args) throws Exception {
    Graph g = new Graph(new Scanner(new File("largeG.txt")));
    Cycle c = new Cycle(g);
    System.out.println("Is there a cycle: " + c.hasCycle());
  }
}
