import java.util.Scanner;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

public class DirectedCycle {
  private boolean[] marked;
  private boolean[] onStack;
  private DirectedEdge[] edgeTo;
  private Deque<DirectedEdge> cycle;

  public DirectedCycle(EdgeWeightedDigraph g) {
    marked = new boolean[g.V()];
    onStack = new boolean[g.V()];
    edgeTo = new DirectedEdge[g.V()];
    for (int v = 0; v < g.V(); v++) if (!marked[v]) dfs(g, v);
  }

  private void dfs(EdgeWeightedDigraph g, int v) {
    onStack[v] = true;
    marked[v] = true;

    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (!marked[w]) {
        edgeTo[w] = e;
        dfs(g, w);
      } else if (onStack[w]) {
        cycle = new LinkedList<DirectedEdge>();
        while (e.from() != w) {
          cycle.push(e);
          e = edgeTo[e.from()];
        }
        cycle.push(e);
      }
    }

    onStack[v] = false;
  }

  public boolean hasCycle() {
    return cycle != null;
  }

  public Iterable<DirectedEdge> cycle() {
    return cycle;
  }
}
