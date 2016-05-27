import java.util.Scanner;
import java.io.File;

import java.util.Deque;
import java.util.LinkedList;

public class AcyclicSP {
  private DirectedEdge[] edgeTo;
  private double[] distTo;

  public AcyclicSP(EdgeWeightedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    for (int i = 0; i < g.V(); i++) distTo[i] = Double.POSITIVE_INFINITY;
    distTo[s] = 0.0;

    Topological topo = new Topological(g);
    for (int v : topo.order()) {
      relax(g, v);
    }
  }

  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
      }
    }
  }

  public double distTo(int v) {
    return distTo[v];
  }

  public boolean hasPathTo(int v) {
    return distTo[v] < Double.POSITIVE_INFINITY;
  }

  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hasPathTo(v)) return null;
    Deque<DirectedEdge> path = new LinkedList<DirectedEdge>();
    for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
      path.push(e);
    return path;
  }

  public static void main(String[] args) throws Exception {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(new Scanner(new File("tinyEWDAG.txt")));
    int s = Integer.parseInt(args[0]);

    AcyclicSP d = new AcyclicSP(g, s);
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
