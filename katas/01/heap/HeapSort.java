import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class HeapSort {
  public static void sort(Comparable[] pq) {
    int N = pq.length;
    for (int k = N/2; k >= 1; k--)
        sink(pq, k, N);
    while (N > 1) {
      exch(pq, 1, N--);
      sink(pq, 1, N);
    }
  }

  private static void sink(Comparable[] pq, int k, int N) {
    while (k*2 <= N) {
      int j = k*2;
      if (j < N && less(pq, j, j+1)) j++;
      if (!less(pq, k, j)) break;
      exch(pq, k, j);
      k = j;
    }
  }

  private static boolean less(Comparable[] a, int i, int j) {
    return a[i-1].compareTo(a[j-1]) < 0;
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i-1]; a[i-1] = a[j-1]; a[j-1] = t;
  }

  public static void main(String[] args) {
    int N = 30;
    Integer[] a = new Integer[N];
    for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(1, 100);
    Integer[] b = Arrays.copyOf(a, a.length);

    System.out.println(Arrays.toString(a));

    HeapSort.sort(a);
    Arrays.sort(b);

    System.out.println("Is array sorted correctly: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
