import java.util.Scanner;
import java.util.TreeSet;

/**
 * Manasa and Stones:
 * https://www.hackerrank.com/challenges/manasa-and-stones
 *
 * This is admittedly a bruteforce solution but it's fast enough to pass. A
 * better approach is explained in the editorial.
 */
public class Manasa {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    for (int t = scanner.nextInt(); t > 0; t--) {
      int n = scanner.nextInt();
      int a = scanner.nextInt();
      int b = scanner.nextInt();
      TreeSet<Integer> curr = new TreeSet<>(), next;
      curr.add(0);
      for (int i = 0; i < n-1; i++) {
        next = new TreeSet<>();
        for (int e : curr) {
          next.add(e + a);
          next.add(e + b);
        }
        curr = next;
      }
      for (int e : curr) {
        System.out.print(e + " ");
      }
      System.out.println();
    }
    scanner.close();
  }
}
