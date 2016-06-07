import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way {
  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length-1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int lt = lo, i = lo+1, gt = hi;
    Comparable v = a[lo];
    while (i <= gt) {
      int cmp = a[i].compareTo(v);
      if (cmp < 0) exch(a, lt++, i++);
      else if (cmp > 0) exch(a, i, gt--);
      else i++;
    }
    sort(a, lo, lt-1);
    sort(a, gt+1, hi);
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

    Quick3Way.sort(a);
    Arrays.sort(b);

    System.out.println("Is array sorted correctly: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
