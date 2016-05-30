import java.util.Scanner;

/**
 * Floyd City of Blinding Lights:
 * https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights
 */
public class FloydCityLights {
  private static class DirectedEdge {
    private final int from, to, weight;

    DirectedEdge(int from, int to, int weight) {
      this.from = from; this.to = to; this.weight = weight;
    }
  }

  private static class AdjMatrixEdgeWeightedDigraph {
    final int V;
    DirectedEdge[][] adj;

    AdjMatrixEdgeWeightedDigraph(int V) {
      this.V = V;
      adj = new DirectedEdge[V][V];
    }

    void addEdge(DirectedEdge e) {
      adj[e.from][e.to] = e;
    }
  }

  private static class FloydWarshall {
    long[][] distTo;

    FloydWarshall(AdjMatrixEdgeWeightedDigraph g) {
      int V = g.V;
      distTo = new long[V][V];
      for (int v = 0; v < V; v++)
        for (int w = 0; w < V; w++)
          distTo[v][w] = Integer.MAX_VALUE;

      for (int v = 0; v < V; v++) {
        for (int w = 0; w < V; w++) {
          if (g.adj[v][w] != null) {
            DirectedEdge e = g.adj[v][w];
            distTo[e.from][e.to] = e.weight;
          }
          distTo[v][v] = 0;
        }
      }

      for (int i = 0; i < V; i++) {
        for (int v = 0; v < V; v++) {
          for (int w = 0; w < V; w++) {
            if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
              distTo[v][w] = distTo[v][i] + distTo[i][w];
            }
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int V = scanner.nextInt();
    AdjMatrixEdgeWeightedDigraph g = new AdjMatrixEdgeWeightedDigraph(V);
    for (int e = scanner.nextInt(); e > 0; e--) {
      g.addEdge(new DirectedEdge(scanner.nextInt()-1, scanner.nextInt()-1, scanner.nextInt()));
    }
    FloydWarshall floyd = new FloydWarshall(g);
    for (int q = scanner.nextInt(); q > 0; q--) {
      long weight = floyd.distTo[scanner.nextInt()-1][scanner.nextInt()-1];
      if (weight == Integer.MAX_VALUE) System.out.println(-1);
      else System.out.println(weight);
    }
    scanner.close();
  }
}
