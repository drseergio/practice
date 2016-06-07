public class DirectedEdge implements Comparable<DirectedEdge> {
  private int v;
  private int w;
  private double weight;

  public DirectedEdge(int v, int w, double weight) {
    this.v = v; this.w = w; this.weight = weight;
  }

  public int from() {
    return v;
  }

  public int to() {
    return w;
  }

  public double weight() {
    return weight;
  }

  public int compareTo(DirectedEdge that) {
    if (weight < that.weight) return -1;
    else if (weight > that.weight) return 1;
    else return 0;
  }

  public String toString() {
    return v + "-" + w + " " + weight;
  }
}
