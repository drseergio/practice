import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Scanner;

public class BSearch {
  public static int rank(int[] a, int key) {
    int lo = 0;
    int hi = a.length - 1;
    int mid;

    while (lo <= hi) {
      mid = lo + (hi - lo) / 2;
      if (key > a[mid]) lo = mid + 1;
      else if (key < a[mid]) hi = mid - 1;
      else return mid;
    }

    return -1;
  }

  public static void main(String[] args) {
    int N = 15;
    int[] a = new int[N];
    for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(1, 100);
    Arrays.sort(a);

    Scanner scanner = new Scanner(System.in);
    System.out.println(Arrays.toString(a));
    do {
      String line = scanner.nextLine();
      System.out.println(rank(a, Integer.parseInt(line)));
      System.out.println(Arrays.toString(a));
    } while (scanner.hasNextLine());
  }
}
