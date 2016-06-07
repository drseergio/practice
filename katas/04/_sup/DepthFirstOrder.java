import java.util.Scanner;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

public class DepthFirstOrder {
  private boolean[] marked;
  private List<Integer> pre;
  private List<Integer> post;
  private Deque<Integer> reversePost;

  public DepthFirstOrder(EdgeWeightedDigraph g) {
    marked = new boolean[g.V()];
    pre = new LinkedList<>();
    post = new LinkedList<>();
    reversePost = new LinkedList<>();
    for (int s = 0; s < g.V(); s++) if (!marked[s]) dfs(g, s);
  }

  private void dfs(EdgeWeightedDigraph g, int v) {
    marked[v] = true;
    pre.add(v);
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (!marked[w]) dfs(g, w);
    }
    post.add(v);
    reversePost.push(v);
  }

  public Iterable<Integer> pre() {
    return pre;
  }

  public Iterable<Integer> post() {
    return post;
  }

  public Iterable<Integer> reversePost() {
    return reversePost;
  }

  public static void main(String[] args) throws Exception {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(new Scanner(new File("tinyDG.txt")));
    DepthFirstOrder order = new DepthFirstOrder(g);
    System.out.print("Pre-order: ");
    for (int v : order.pre()) System.out.print(v + " ");
    System.out.println();
    System.out.print("Post-order: ");
    for (int v : order.post()) System.out.print(v + " ");
    System.out.println();
    System.out.print("Reverse post-order: ");
    for (int v : order.reversePost()) System.out.print(v + " ");
    System.out.println();
  }
}
