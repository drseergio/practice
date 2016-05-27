import java.util.Scanner;
import java.io.File;

public class Topological {
  private Iterable<Integer> order;

  public Topological(Digraph g) {
    DirectedCycle cycle = new DirectedCycle(g);
    if (!cycle.hasCycle()) {
      DepthFirstOrder dfs = new DepthFirstOrder(g);
      order = dfs.reversePost();
    }
  }

  public boolean isDAG() {
    return order != null;
  }

  public Iterable<Integer> order() {
    return order;
  }

  public static void main(String[] args) throws Exception {
    Digraph g = new Digraph(new Scanner(new File("tinyDAG.txt")));
    Topological topo = new Topological(g);
    System.out.println("Is DAG: " + topo.isDAG());
    if (topo.isDAG()) {
      System.out.print("Order: ");
      for (int v : topo.order()) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }
}
