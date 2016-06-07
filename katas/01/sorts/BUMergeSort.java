import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;

public class BUMergeSort {
  static Comparable[] aux;

  public static void sort(Comparable a[]) {
    int N = a.length;
    aux = new Comparable[N];
    for (int sz = 1; sz < N; sz += sz)
      for (int lo = 0; lo < N-sz; lo += sz + sz)
        merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
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

    BUMergeSort.sort(a);
    Arrays.sort(b);

    System.out.println("Is array sorted correctly: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
