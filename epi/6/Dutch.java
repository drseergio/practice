import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class Dutch {
  public static final int P = 5;

  public static void partition(int[] a, int p) {
    int lt = 0, gt = a.length - 1, i = 0;
    int v = a[p];

    while (i <= gt) {
      if (a[i] < v) exch(a, lt++, i++);
      else if (a[i] > v) exch(a, gt--, i);
      else i++;
    }
  }

  private static void exch(int[] a, int i, int j) {
    int t = a[i]; a[i] = a[j]; a[j] = t;
  }

  public static void main(String[] args) {
    int N = 20;
    int[] a = new int[N];
    for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(1, 340);
    int[] b = Arrays.copyOf(a, a.length);

    System.out.println(Arrays.toString(a));
    System.out.println("p=" + P);

    partition(a, P);
    Arrays.sort(b);

    System.out.println("Is array sorted correctly: " + Arrays.equals(a, b));
    System.out.println(Arrays.toString(a));
  }
}
