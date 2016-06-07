import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Digraph {
  private final int V;
  private List<Integer>[] adj;
  private int E;

  public Digraph(int V) {
    this.V = V;
    adj = (List<Integer>[]) new List[V];
    for (int v = 0; v < V; v++)
      adj[v] = new LinkedList<Integer>();
  }

  public Digraph(Scanner scanner) {
    this(scanner.nextInt());
    int E = scanner.nextInt();

    for (int i = 0; i < E; i++) {
      int v = scanner.nextInt();
      int w = scanner.nextInt();
      addEdge(v, w);
    }
  }

  public int V() {
    return this.V;
  }

  public void addEdge(int v, int w) {
    adj[v].add(w);
    E++;
  }

  public Iterable<Integer> adj(int v) {
    return adj[v];
  }

  public Digraph reverse() {
    Digraph dig = new Digraph(V);
    for (int v = 0; v < V; v++) {
      for (int w : adj(v))
        dig.addEdge(w, v);
    }
    return dig;
  }
}
