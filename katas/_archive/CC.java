import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;

public class CC {
  private boolean[] marked;
  private int[] id;
  private int count;

  public CC(Graph g) {
    marked = new boolean[g.V()];
    id = new int[g.V()];
    for (int s = 0; s < g.V(); s++) {
      if (!marked[s]) {
        dfs(g, s);
        count++;
      }
    }
  }

  private void dfs(Graph g, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : g.adj(v)) {
      if (!marked[w]) dfs(g, w);
    }
  }

  public int id(int v) {
    return id[v];
  }

  public int count() {
    return count;
  }

  public boolean connected(int v, int w) {
    return id[v] == id[w];
  }

  public static void main(String[] args) throws Exception {
    Graph g = new Graph(new Scanner(new File("tinyG.txt")));
    CC cc = new CC(g);

    int M = cc.count();
    System.out.println(M + " components");

    Queue<Integer>[] components;
    components = (Queue<Integer>[]) new Queue[M];
    for (int i = 0; i < M; i++)
      components[i] = new LinkedList<Integer>();
    for (int v = 0; v < g.V(); v++)
      components[cc.id(v)].add(v);

    for (int i = 0; i < M; i++) {
      for (int v : components[i])
        System.out.print(v + " ");
      System.out.println();
    }
  }
}
