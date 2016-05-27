import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class EdgeWeightedDigraph {
  private final int V;
  private int E;
  private List<DirectedEdge>[] adj;

  public EdgeWeightedDigraph(int V) {
    this.V = V;
    adj = (List<DirectedEdge>[]) new List[V];
    for (int i = 0; i < V; i++) adj[i] = new LinkedList<DirectedEdge>();
  }

  public EdgeWeightedDigraph(Scanner scanner) {
    this(scanner.nextInt());
    int E = scanner.nextInt();

    for (int i = 0; i < E; i++) {
      int v = scanner.nextInt();
      int w = scanner.nextInt();
      double weight = scanner.nextDouble();
      addEdge(new DirectedEdge(v, w, weight));
    }
  }

  public int V() {
    return this.V;
  }

  public int E() {
    return this.E;
  }

  public void addEdge(DirectedEdge e) {
    adj[e.from()].add(e);
    E++;
  }

  public Iterable<DirectedEdge> adj(int v) {
    return adj[v];
  }

  public Iterable<DirectedEdge> edges() {
    List<DirectedEdge> b = new LinkedList<DirectedEdge>();
    for (int v = 0; v < V; v++)
      for (DirectedEdge e : adj[v])
        b.add(e);
    return b;
  }

  public static void main(String[] args) throws Exception {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(new Scanner(new File("tinyEWG.txt")));
  }
}
