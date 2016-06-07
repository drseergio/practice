import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class ShellSort {
  public static void sort(Comparable[] a) {
    int N = a.length, h = 1;
    while (h < N/3) h = 3*h + 1;

    while (h >= 1) {
      for (int i = h; i < N; i += h) {
        for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) exch(a, j, j-h);
      }
      h /= 3;
    }
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  public static void main(String[] args) {
    int N = 30;
    Integer[] a = new Integer[N];
    for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(1, 100);
    Integer[] b = Arrays.copyOf(a, a.length);

    System.out.println(Arrays.toString(a));

    ShellSort.sort(a);
    Arrays.sort(b);

    System.out.println("Is array sorted correctly: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
