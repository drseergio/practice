import java.util.Scanner;

/**
 * Flip bits:
 * https://www.hackerrank.com/challenges/flipping-bits
 */
public class FlipBits {
  public static final long MASK = 0xFFFF_FFFFL;
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    for (int c = scanner.nextInt(); c > 0; c--) {
      long n = scanner.nextInt();
      System.out.println(((~n) & MASK));
    }
    scanner.close();
  }
}
