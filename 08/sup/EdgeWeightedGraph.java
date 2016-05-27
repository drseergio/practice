import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class EdgeWeightedGraph {
  private final int V;
  private int E;
  private List<Edge>[] adj;

  public EdgeWeightedGraph(int V) {
    this.V = V;
    adj = (List<Edge>[]) new List[V];
    for (int i = 0; i < V; i++) adj[i] = new LinkedList<Edge>();
  }

  public EdgeWeightedGraph(Scanner scanner) {
    this(scanner.nextInt());
    int E = scanner.nextInt();

    for (int i = 0; i < E; i++) {
      int v = scanner.nextInt();
      int w = scanner.nextInt();
      double weight = scanner.nextDouble();
      addEdge(new Edge(v, w, weight));
    }
  }

  public int V() {
    return this.V;
  }

  public int E() {
    return this.E;
  }

  public void addEdge(Edge e) {
    int v = e.either(), w = e.other(v);
    adj[v].add(e);
    adj[w].add(e);
    E++;
  }

  public Iterable<Edge> adj(int v) {
    return adj[v];
  }

  public Iterable<Edge> edges() {
    List<Edge> b = new LinkedList<Edge>();
    for (int v = 0; v < V; v++)
      for (Edge e : adj[v])
        if (e.other(v) > v) b.add(e);
    return b;
  }

  public static void main(String[] args) throws Exception {
    EdgeWeightedGraph g = new EdgeWeightedGraph(new Scanner(new File("tinyEWG.txt")));
  }
}
