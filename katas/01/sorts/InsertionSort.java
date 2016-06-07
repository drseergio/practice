import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class InsertionSort {

  public static void sort(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      for (int j = i; j > 0 && less(a[j], a[j-1]); j--) exch(a, j, j-1);
    }
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i]; a[i] = a[j]; a[j] = t;
  }

  public static void main(String[] args) {
    int N = 30;
    Integer[] a = new Integer[N];
    for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(1, 100);
    Integer[] b = Arrays.copyOf(a, a.length);

    System.out.println(Arrays.toString(a));

    InsertionSort.sort(a);
    Arrays.sort(b);

    System.out.println("Is array sorted correctly: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
