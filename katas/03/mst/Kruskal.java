import java.util.Queue;
import java.util.LinkedList;

import java.util.Scanner;
import java.io.File;

import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.MinPQ;

public class Kruskal {
  private Queue<Edge> mst;

  public Kruskal(EdgeWeightedGraph g) {
    mst = new LinkedList<Edge>();
    MinPQ<Edge> pq = new MinPQ<Edge>();
    for (Edge e : g.edges())
      pq.insert(e);
    UF uf = new UF(g.V());

    while (!pq.isEmpty() && mst.size() < g.V()-1) {
      Edge e = pq.delMin();
      int v = e.either(), w = e.other(v);
      if (uf.connected(v, w)) continue;
      uf.union(v, w);
      mst.add(e);
    }
  }

  public Iterable<Edge> edges() {
    return mst;
  }

  public double weight() {
    double sum = 0;
    for (Edge e : mst) sum += e.weight();
    return sum;
  }

  public static void main(String[] args) throws Exception {
    EdgeWeightedGraph g = new EdgeWeightedGraph(new Scanner(new File("tinyEWG.txt")));
    Kruskal mst = new Kruskal(g);
    for (Edge e : mst.edges()) {
      System.out.println(e);
    }
    System.out.printf("%.5f\n", mst.weight());
  }
}
