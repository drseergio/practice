import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;

public class BFS {
  private boolean[] marked;
  private int count;

  public BFS(Graph g, int s) {
    count = 1;
    marked = new boolean[g.V()];
    bfs(g, s);
  }

  private void bfs(Graph g, int s) {
    Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(s);
    marked[s] = true;

    while (!queue.isEmpty()) {
      int v = queue.remove();
      for (int w : g.adj(v)) {
        if (!marked[w]) {
          marked[w] = true;
          count++;
          queue.add(w);
        }
      }
    }
  }

  public boolean marked(int v) {
    return marked[v];
  }

  public int count() {
    return count;
  }

  public static void main(String[] args) throws Exception {
    Graph g = new Graph(new Scanner(new File("tinyG.txt")));
    BFS bfs = new BFS(g, Integer.parseInt(args[0]));
    for (int i = 0; i < g.V(); i++) {
      if (bfs.marked(i)) System.out.print(i + " ");
    }
    System.out.println();
    System.out.println("Count: " + bfs.count());
  }
}
