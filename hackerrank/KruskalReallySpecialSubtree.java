import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Kruskal Really Special Subtree:
 * https://www.hackerrank.com/challenges/kruskalmstrsub
 */
public class KruskalReallySpecialSubtree {
  static class UnionFind {
    private int[] sz;
    private int[] id;

    UnionFind(int N) {
      sz = new int[N];
      for (int i = 0; i < N; i++)
        sz[i] = 1;
      id = new int[N];
      for (int i = 0; i < N; i++)
        id[i] = i;
    }

    private int find(int p) {
      while (p != id[p]) p = id[p];
      return p;
    }

    boolean connected(int p, int q) {
      return find(p) == find(q);
    }

    void union(int p, int q) {
      int i = find(p);
      int j = find(q);
      if (i == j) return;

      if (sz[i] > sz[j]) { id[j] = i; sz[i] += sz[j]; }
      else { id[i] = j; sz[j] += sz[i]; }
    }
  }

  static class EdgeWeightedGraph {
    final int V;
    List<Edge>[] adj;

    EdgeWeightedGraph(int V) {
      this.V = V;
      adj = new LinkedList[V];
      for (int i = 0; i < V; i++)
        adj[i] = new LinkedList<Edge>();
    }

    Iterable<Edge> edges() {
      LinkedList<Edge> l = new LinkedList<>();
      for (int v = 0; v < V; v++) {
        for (Edge e : adj[v]) {
          if (e.other(v) > v) l.add(e);
        }
      }
      return l;
    }

    void addEdge(Edge e) {
      int v = e.either(), w = e.other(v);
      adj[v].add(e);
      adj[w].add(e);
    }
  }

  static class Edge implements Comparable<Edge> {
    int weight;
    int v;
    int w;

    Edge(int v, int w, int weight) {
      this.v = v; this.w = w; this.weight = weight;
    }

    int either() {
      return v;
    }

    int other(int x) {
      if (x == v) return w;
      return v;
    }

    @Override
    public int compareTo(Edge that) {
      return Integer.compare(this.weight, that.weight);
    }
  }

  static class Kruskal {
    List<Edge> mst;

    public Kruskal(EdgeWeightedGraph g) {
      mst = new LinkedList<>();
      UnionFind uf = new UnionFind(g.V);
      PriorityQueue<Edge> pq = new PriorityQueue<>();
      for (Edge e : g.edges()) {
        pq.add(e);
      }
      while (!pq.isEmpty() && mst.size() < g.V-1) {
        Edge e = pq.poll();
        int v = e.either(), w = e.other(v);
        if (uf.connected(v, w)) continue;
        mst.add(e);
        uf.union(v, w);
      }
    }

    int weight() {
      int sum = 0;
      for (Edge e : mst) sum += e.weight;
      return sum;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int V = scanner.nextInt();
    int E = scanner.nextInt();
    EdgeWeightedGraph g = new EdgeWeightedGraph(V);
    for (int i = 0; i < E; i++) {
      g.addEdge(new Edge(scanner.nextInt()-1, scanner.nextInt()-1, scanner.nextInt()));
    }
    scanner.nextInt(); // S is irrelevant to MSTs
    Kruskal kruskal = new Kruskal(g);
    System.out.println(kruskal.weight());
    scanner.close();
  }
}
