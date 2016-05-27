import java.util.Scanner;
import java.io.File;

public class DirectedDFS {
  private boolean[] marked;

  public DirectedDFS(Digraph g, int s) {
    marked = new boolean[g.V()];
    dfs(g, s);
  }

  private void dfs(Digraph g, int v) {
    marked[v] = true;
    for (int w : g.adj(v)) if (!marked(w)) dfs(g, w);
  }

  public boolean marked(int v) {
    return marked[v];
  }

  public static void main(String[] args) throws Exception {
    Digraph g = new Digraph(new Scanner(new File("tinyDG.txt")));
    DirectedDFS dfs = new DirectedDFS(g, Integer.parseInt(args[0]));
    for (int i = 0; i < g.V(); i++) {
      if (dfs.marked(i)) System.out.print(i + " ");
    }
    System.out.println();
  }
}
