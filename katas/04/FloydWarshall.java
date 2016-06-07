import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Deque;
import java.util.LinkedList;

public class FloydWarshall {
  private boolean hasNegativeCycle;
  private double[][] distTo;
  private DirectedEdge[][] edgeTo;

  public FloydWarshall(AdjMatrixEdgeWeightedDigraph g) {
    int V = g.V();
    distTo = new double[V][V];
    edgeTo = new DirectedEdge[V][V];

    for (int v = 0; v < V; v++)
      for (int w = 0; w < V; w++)
        distTo[v][w] = Double.POSITIVE_INFINITY;

    for (int v = 0; v < V; v++) {
      for (DirectedEdge e : g.adj(v)) {
        distTo[e.from()][e.to()] = e.weight();
        edgeTo[e.from()][e.to()] = e;
      }
      if (distTo[v][v] >= 0.0) {
        distTo[v][v] = 0.0;
        edgeTo[v][v] = null;
      }
    }

    for (int i = 0; i < V; i++) {
      for (int v = 0; v < V; v++) {
        for (int w = 0; w < V; w++) {
          if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
            distTo[v][w] = distTo[v][i] + distTo[i][w];
            edgeTo[v][w] = edgeTo[i][w];
          }
        }
        if (distTo[v][v] < 0.0) {
          hasNegativeCycle = true;
          return;
        }
      }
    }
  }

  public boolean hasNegativeCycle() {
    return hasNegativeCycle;
  }

  public Iterable<DirectedEdge> negativeCycle() {
    for (int v = 0; v < distTo.length; v++) {
      if (distTo[v][v] < 0.0) {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int w = 0; w < V; w++)
          if (edgeTo[v][w] != null)
            spt.addEdge(edgeTo[v][w]);
        DirectedCycle dc = new DirectedCycle(spt);
        return dc.cycle();
      }
    }
    return null;
  }

  public boolean hasPath(int s, int t) {
    return distTo[s][t] < Double.POSITIVE_INFINITY;
  }

  public double dist(int s, int t) {
    return distTo[s][t];
  }

  public Iterable<DirectedEdge> path(int s, int t) {
    Deque<DirectedEdge> path = new LinkedList<>();
    for (DirectedEdge e = edgeTo[s][t]; e != null; e = edgeTo[s][e.from()]) {
      path.push(e);
    }
    return path;
  }

  public static void main(String[] args) {
    // random graph with V vertices and E edges, parallel edges allowed
    int V = Integer.parseInt(args[0]);
    int E = Integer.parseInt(args[1]);
    AdjMatrixEdgeWeightedDigraph G = new AdjMatrixEdgeWeightedDigraph(V);
    for (int i = 0; i < E; i++) {
      int v = StdRandom.uniform(V);
      int w = StdRandom.uniform(V);
      double weight = Math.round(100 * (StdRandom.uniform() - 0.15)) / 100.0;
      if (v == w) G.addEdge(new DirectedEdge(v, w, Math.abs(weight)));
      else G.addEdge(new DirectedEdge(v, w, weight));
    }

    StdOut.println(G);

    // run Floyd-Warshall algorithm
    FloydWarshall spt = new FloydWarshall(G);

    // print all-pairs shortest path distances
    StdOut.printf("  ");
    for (int v = 0; v < G.V(); v++) {
      StdOut.printf("%6d ", v);
    }
    StdOut.println();
    for (int v = 0; v < G.V(); v++) {
      StdOut.printf("%3d: ", v);
      for (int w = 0; w < G.V(); w++) {
        if (spt.hasPath(v, w)) StdOut.printf("%6.2f ", spt.dist(v, w));
        else StdOut.printf("  Inf ");
      }
      StdOut.println();
    }

    // print negative cycle
    if (spt.hasNegativeCycle()) {
      StdOut.println("Negative cost cycle:");
      for (DirectedEdge e : spt.negativeCycle())
        StdOut.println(e);
      StdOut.println();
    // print all-pairs shortest paths
    } else {
      for (int v = 0; v < G.V(); v++) {
        for (int w = 0; w < G.V(); w++) {
          if (spt.hasPath(v, w)) {
            StdOut.printf("%d to %d (%5.2f)  ", v, w, spt.dist(v, w));
            for (DirectedEdge e : spt.path(v, w))
              StdOut.print(e + "  ");
            StdOut.println();
          } else {
            StdOut.printf("%d to %d no path\n", v, w);
          }
        }
      }
    }
  }
}
