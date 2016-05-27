import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasing {
  public static int longest(int[] X) {
    int N = X.length;
    int M[] = new int[N+1];

    int L = 0;
    for (int i = 0; i < N; i++) {
      int lo = 1;
      int hi = L;

      while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (X[M[mid]] < X[i]) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }

      int newL = lo;
      M[newL] = i;

      L = Math.max(L, newL);
    }

    return L;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int[] a = new int[N];
    for (int i = 0; i < N; i++) {
      a[i] = scanner.nextInt();
    }
    scanner.close();
    System.out.println(longest(a));
  }
}
