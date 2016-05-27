import java.util.Scanner;
import java.io.File;

import java.util.Deque;
import java.util.LinkedList;

import edu.princeton.cs.algs4.IndexMinPQ;

public class Dijkstra {
  private DirectedEdge[] edgeTo;
  private double[] distTo;
  private IndexMinPQ<Double> pq;

  public Dijkstra(EdgeWeightedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    for (int i = 0; i < g.V(); i++) distTo[i] = Double.POSITIVE_INFINITY;
    pq = new IndexMinPQ<Double>(g.V());

    distTo[s] = 0.0;
    pq.insert(s, 0.0);
    while (!pq.isEmpty()) {
      relax(g, pq.delMin());
    }
  }

  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (pq.contains(w)) pq.change(w, distTo[w]);
        else pq.insert(w, distTo[w]);
      }
    }
  }

  public double distTo(int v) {
    return distTo[v];
  }

  public boolean hasPathTo(int v) {
    return distTo[v] != Double.POSITIVE_INFINITY;
  }

  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hasPathTo(v)) return null;
    Deque<DirectedEdge> path = new LinkedList<DirectedEdge>();
    for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
      path.push(e);
    return path;
  }

  public static void main(String[] args) throws Exception {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(new Scanner(new File("tinyEWD.txt")));
    int s = Integer.parseInt(args[0]);

    Dijkstra d = new Dijkstra(g, s);
    for (int t = 0; t < g.V(); t++) {
      System.out.print(s + " to " + t);
      System.out.printf(" (%4.2f): ", d.distTo(t));
      if (d.hasPathTo(t))
        for (DirectedEdge e : d.pathTo(t))
          System.out.print(e + "  ");
      System.out.println();
    }
  }
}
