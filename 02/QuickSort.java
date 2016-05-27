import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    int N = a.length;
    sort(a, 0, N-1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int partition = partition(a, lo, hi);
    sort(a, lo, partition-1);
    sort(a, partition+1, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo, j = hi+1;
    Comparable v = a[lo];

    while (true) {
      while (less(a[++i], v)) if (i == hi) break;
      while (less(v, a[--j])) if (j == lo) break;
      if (i >= j) break;
      exch(a, i, j);
    }

    exch(a, lo, j);
    return j;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i]; a[i] = a[j]; a[j] = t;
  }

  public static void main(String[] args) {
    int N = 12;
    Integer[] a = new Integer[N];
    for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(1, 100);
    Integer[] b = Arrays.copyOf(a, a.length);

    System.out.println(Arrays.toString(a));

    QuickSort.sort(a);
    Arrays.sort(b);

    System.out.println("Is array sorted correctly: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
