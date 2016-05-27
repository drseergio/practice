import java.util.LinkedList;

import java.util.Scanner;
import java.io.File;

import edu.princeton.cs.algs4.IndexMinPQ;

public class Prim {
  private Edge[] edgeTo;
  private double[] distTo;
  private boolean[] marked;
  private IndexMinPQ<Double> pq;

  public Prim(EdgeWeightedGraph g) {
    edgeTo = new Edge[g.V()];
    distTo = new double[g.V()];
    marked = new boolean[g.V()];
    pq = new IndexMinPQ<Double>(g.V());
    for (int i = 0; i < g.V(); i++) distTo[i] = Double.POSITIVE_INFINITY;

    distTo[0] = 0.0;
    pq.insert(0, 0.0);
    while (!pq.isEmpty()) {
      visit(g, pq.delMin());
    }
  }

  private void visit(EdgeWeightedGraph g, int v) {
    marked[v] = true;
    for (Edge e : g.adj(v)) {
      int w = e.other(v);
      if (marked[w]) continue;
      if (e.weight() < distTo[w]) {
        edgeTo[w] = e;
        distTo[w] = e.weight();
        if (pq.contains(w)) pq.change(w, distTo[w]);
        else pq.insert(w, distTo[w]);
      }
    }
  }

  public Iterable<Edge> edges() {
    LinkedList<Edge> mst = new LinkedList<Edge>();
    for (int v = 0; v < edgeTo.length; v++) {
      Edge e = edgeTo[v];
      if (e != null) {
        mst.add(e);
      }
    }
    return mst;
  }

  public double weight() {
    double sum = 0;
    for (Edge e : edges()) sum += e.weight();
    return sum;
  }

  public static void main(String[] args) throws Exception {
    EdgeWeightedGraph g = new EdgeWeightedGraph(new Scanner(new File("tinyEWG.txt")));
    Prim mst = new Prim(g);
    for (Edge e : mst.edges()) {
      System.out.println(e);
    }
    System.out.printf("%.5f\n", mst.weight());
  }
}
