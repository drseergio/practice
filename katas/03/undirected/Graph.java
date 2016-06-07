import java.util.Scanner;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
  private final int V;
  private int E;
  private LinkedList<Integer>[] adj;

  public Graph(int V) {
    this.V = V;
    adj = new LinkedList[V];
    for (int i = 0; i < V; i++) {
      adj[i] = new LinkedList<Integer>();
    }
  }

  public Graph(Scanner scanner) {
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

  public int E() {
    return this.E;
  }

  public void addEdge(int v, int w) {
    adj[v].add(w);
    adj[w].add(v);
    E++;
  }

  public Queue<Integer> adj(int v) {
    return adj[v];
  }

  public static void main(String[] args) throws Exception {
    Graph g = new Graph(new Scanner(new File("tinyG.txt")));
  }
}
