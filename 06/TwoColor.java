import java.util.Scanner;
import java.io.File;

public class TwoColor {
  private boolean[] marked;
  private boolean[] color;
  private boolean isTwoColorable = true;

  public TwoColor(Graph g) {
    marked = new boolean[g.V()];
    color = new boolean[g.V()];
    for (int s = 0; s < g.V(); s++) if (!marked[s]) {
      dfs(g, s);
    }
  }

  private void dfs(Graph g, int v) {
    marked[v] = true;
    for (int w : g.adj(v)) {
      if (!marked[w]) {
        color[w] = !color[v];
        dfs(g, w);
      } else if (color[w] == color[v]) {
        isTwoColorable = false;
      }
    }
  }

  public boolean isBipartite() {
    return isTwoColorable;
  }

  public static void main(String[] args) throws Exception {
    Graph g = new Graph(new Scanner(new File("tinyG.txt")));
    TwoColor c = new TwoColor(g);
    System.out.println("Is two-coloring possible: " + c.isBipartite());
  }
}
