import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;

public class KosarajuSharirSCC {
  private boolean[] marked;
  private int[] id;
  private int count;

  public KosarajuSharirSCC(Digraph g) {
    marked = new boolean[g.V()];
    id = new int[g.V()];
    DepthFirstOrder order = new DepthFirstOrder(g.reverse());
    for (int s : order.reversePost()) {
      if (!marked[s]) {
        dfs(g, s);
        count++;
      }
    }
  }

  private void dfs(Digraph g, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : g.adj(v)) {
      if (!marked[w]) dfs(g, w);
    }
  }

  public boolean isStronglyConnected(int v, int w) {
    return id[v] == id[w];
  }

  public int id(int v) {
    return id[v];
  }

  public int count() {
    return count;
  }

  public static void main(String[] args) throws Exception {
    Digraph g = new Digraph(new Scanner(new File("tinyDG.txt")));
    KosarajuSharirSCC scc = new KosarajuSharirSCC(g);

    int M = scc.count();
    System.out.println(M + " strong components");

    Queue<Integer>[] components;
    components = (Queue<Integer>[]) new Queue[M];
    for (int i = 0; i < M; i++)
      components[i] = new LinkedList<Integer>();
    for (int v = 0; v < g.V(); v++)
      components[scc.id(v)].add(v);

    for (int i = 0; i < M; i++) {
      for (int v : components[i])
        System.out.print(v + " ");
      System.out.println();
    }
  }
}
