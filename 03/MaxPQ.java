import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class MaxPQ<Key extends Comparable<Key>> {
  private Key[] pq;
  private int N;

  public MaxPQ(int maxN) {
    pq = (Key[]) new Comparable[maxN+1];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int size() {
    return N;
  }

  public void insert(Key key) {
    pq[++N] = key;
    swim(N);
  }

  public Key delMax() {
    Key max = pq[1];
    exch(1, N--);
    pq[N+1] = null;
    sink(1);
    return max;
  }

  private void sink(int k) {
    while (2*k <= N) {
      int j = 2*k;
      if (j < N && less(j, j+1)) j++;
      if (!less(k, j)) break;
      exch(k, j);
      k = j;
    }
  }

  private void swim(int k) {
    while (k > 1 && less(k/2, k)) {
      exch(k/2, k);
      k /= 2;
    }
  }

  private boolean less(int i, int j) {
    return pq[i].compareTo(pq[j]) < 0;
  }

  private void exch(int i, int j) {
    Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;
  }

  public static void main(String[] args) {
    int N = 15;
    int[] a = new int[N];
    for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(1, 100);
    System.out.println(Arrays.toString(a));

    MaxPQ mpq = new MaxPQ(N);
    for (int i = 0; i < N; i++) mpq.insert(a[i]);

    int[] b = new int[N];
    for (int i = 0; i < N; i++) b[i] = (Integer) mpq.delMax();

    System.out.println(Arrays.toString(b));
  }
}
