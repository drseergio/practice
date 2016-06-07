import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class MergeSort {
  static Comparable[] aux;

  public static void sort(Comparable a[]) {
    aux = new Comparable[a.length];
    sort(a, 0, a.length - 1);
  }

  private static void sort(Comparable a[], int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, lo, mid);
    sort(a, mid+1, hi);
    merge(a, lo, mid, hi);
  }

  private static void merge(Comparable a[], int lo, int mid, int hi) {
    int i = lo, j = mid + 1;

    for (int k = lo; k <= hi; k++) aux[k] = a[k];

    for (int k = lo; k <= hi; k++) {
      if (i > mid) a[k] = aux[j++];
      else if (j > hi) a[k] = aux[i++];
      else if (less(aux[j], aux[i])) a[k] = aux[j++];
      else a[k] = aux[i++];
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

    MergeSort.sort(a);
    Arrays.sort(b);

    System.out.println("Is array sorted correctly: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
