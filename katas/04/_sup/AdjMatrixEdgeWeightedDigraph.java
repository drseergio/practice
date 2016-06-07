import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjMatrixEdgeWeightedDigraph {
  private static final String NEWLINE = System.getProperty("line.separator");

  private int V;
  private int E;
  private DirectedEdge[][] adj;
    
  public AdjMatrixEdgeWeightedDigraph(int V) {
    if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
    this.V = V;
    this.E = 0;
    this.adj = new DirectedEdge[V][V];
  }

  public AdjMatrixEdgeWeightedDigraph(int V, int E) {
    this(V);
    if (E < 0) throw new RuntimeException("Number of edges must be nonnegative");
    if (E > V*V) throw new RuntimeException("Too many edges");

    while (this.E != E) {
      int v = StdRandom.uniform(V);
      int w = StdRandom.uniform(V);
      double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
      addEdge(new DirectedEdge(v, w, weight));
    }
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void addEdge(DirectedEdge e) {
    int v = e.from();
    int w = e.to();
    if (adj[v][w] == null) {
      E++;
      adj[v][w] = e;
    }
  }

  public Iterable<DirectedEdge> adj(int v) {
    return new AdjIterator(v);
  }

  private class AdjIterator implements Iterator<DirectedEdge>, Iterable<DirectedEdge> {
    private int v;
    private int w = 0;

    public AdjIterator(int v) {
      this.v = v;
    }

    public Iterator<DirectedEdge> iterator() {
      return this;
    }

    public boolean hasNext() {
      while (w < V) {
        if (adj[v][w] != null) return true;
        w++;
      }
      return false;
    }

    public DirectedEdge next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return adj[v][w++];
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(V + " " + E + NEWLINE);
    for (int v = 0; v < V; v++) {
      s.append(v + ": ");
      for (DirectedEdge e : adj(v)) {
        s.append(e + "  ");
      }
      s.append(NEWLINE);
    }
    return s.toString();
  }
}
