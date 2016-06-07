import java.util.Scanner;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

public class DirectedCycle {
  private boolean[] marked;
  private boolean[] onStack;
  private int[] edgeTo;
  private Deque<Integer> cycle;

  public DirectedCycle(Digraph g) {
    marked = new boolean[g.V()];
    onStack = new boolean[g.V()];
    edgeTo = new int[g.V()];
    for (int v = 0; v < g.V(); v++) if (!marked[v]) dfs(g, v);
  }

  private void dfs(Digraph g, int v) {
    onStack[v] = true;
    marked[v] = true;

    for (int w : g.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(g, w);
      } else if (onStack[w]) {
        cycle = new LinkedList<Integer>();
        for (int x = v; x != w; x = edgeTo[x]) cycle.push(x);
        cycle.push(w);
        cycle.push(v);
      }
    }

    onStack[v] = false;
  }

  public boolean hasCycle() {
    return cycle != null;
  }

  public Iterable<Integer> cycle() {
    return cycle;
  }

  public static void main(String[] args) throws Exception {
    Digraph g = new Digraph(new Scanner(new File("tinyDAG.txt")));
    DirectedCycle cycle = new DirectedCycle(g);
    System.out.println("Has a cycle: " + cycle.hasCycle());
    if (cycle.hasCycle()) {
      System.out.println("Cycle:");
      for (int v : cycle.cycle()) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }
}
