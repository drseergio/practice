import java.util.Scanner;
import java.io.File;

import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;

public class BellmanFordSP {
  private DirectedEdge[] edgeTo;
  private double[] distTo;
  private boolean[] onQ;
  private Queue<Integer> queue;
  private Iterable<DirectedEdge> cycle;
  private int cost;

  public BellmanFordSP(EdgeWeightedDigraph g, int s) {
    onQ = new boolean[g.V()];
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    for (int i = 0; i < g.V(); i++) distTo[i] = Double.POSITIVE_INFINITY;
    queue = new LinkedList<Integer>();

    distTo[s] = 0.0;
    onQ[s] = true;
    queue.add(s);

    while (!queue.isEmpty() && !this.hasNegativeCycle()) {
      int v = queue.remove();
      onQ[v] = false;
      relax(g, v);
    }
  }

  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (!onQ[w]) {
          queue.add(w);
          onQ[w] = true;
        }
      }
      if (cost++ % g.V() == 0) {
        findNegativeCycle();
        if (hasNegativeCycle()) return;
      }
    }
  }

  private void findNegativeCycle() {
    int V = edgeTo.length;
    EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
    for (int v = 0; v < V; v++)
      if (edgeTo[v] != null)
        spt.addEdge(edgeTo[v]);

    DirectedCycle cf = new DirectedCycle(spt);
    cycle = cf.cycle();
  }

  public boolean hasNegativeCycle() {
    return cycle != null;
  }

  public Iterable<DirectedEdge> negativeCycle() {
    return cycle;
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
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(new Scanner(new File("tinyEWDnc.txt")));
    int s = Integer.parseInt(args[0]);
    BellmanFordSP d = new BellmanFordSP(g, s);

    if (d.hasNegativeCycle()) {
      for (DirectedEdge e : d.negativeCycle())
        System.out.println(e);
    } else {
      for (int v = 0; v < g.V(); v++) {
        if (d.hasPathTo(v)) {
          System.out.printf("%d to %d (%5.2f): ", s, v, d.distTo(v));
          for (DirectedEdge e : d.pathTo(v))
            System.out.print(e + "  ");
          System.out.println();
        } else {
          System.out.printf("%d to %d     no path\n", s, v);
        }
      }
    }
  }
}
