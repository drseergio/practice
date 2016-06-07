import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

public class SelectionSort {
  public static void sort(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      int min = i;
      for (int j = i+1; j < a.length; j++) if (less(a[j], a[min])) min = j;
      exch(a, i, min);
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

    SelectionSort.sort(a);
    Arrays.sort(b);
    System.out.println("Has array been correctly sorted: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
