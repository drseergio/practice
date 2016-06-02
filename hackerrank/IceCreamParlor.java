import java.util.Arrays;
import java.util.Scanner;

/**
 * Ice Cream Parlor:
 * https://www.hackerrank.com/domains/algorithms/search
 */
public class IceCreamParlor {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    for (int t = scanner.nextInt(); t > 0; t--) {
      int M = scanner.nextInt();
      int[] a = new int[scanner.nextInt()], tmp = new int[a.length];
      for (int i = 0; i < a.length; i++) {
        tmp[i] = a[i] = scanner.nextInt();
      }
      Arrays.sort(tmp);
      for (int i = 0; i < a.length; i++) {
        int diff = M - tmp[i];
        int index = Arrays.binarySearch(tmp, diff);
        if (index > 0) {
          int p1 = tmp[i], p2 = tmp[index];
          for (int j = 0; j < a.length; j++) {
            if (a[j] == p1 || a[j] == p2) System.out.print((j+1) + " ");
          }
          System.out.println();
          break;
        }
      }
    }
    scanner.close();
  }
}
