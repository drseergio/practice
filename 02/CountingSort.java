import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

public class CountingSort {
  public static final int R = 8;

  public static void sort(int[] a) {
    int N = a.length;
    int[] aux = new int[N];
    int[] count = new int[R+1];

    for (int i = 0; i < N; i++)
      count[a[i] + 1]++;

    for (int r = 0; r < R; r++)
      count[r+1] += count[r];

    for (int i = 0; i < N; i++)
      aux[count[a[i]]++] = a[i];

    for (int i = 0; i < N; i++)
      a[i] = aux[i];
  }

  public static void main(String[] args) {
    int N = 32;
    int[] a = new int[N];
    for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(1, R);
    System.out.println(Arrays.toString(a));
    sort(a);
    System.out.println(Arrays.toString(a));
  }
}
