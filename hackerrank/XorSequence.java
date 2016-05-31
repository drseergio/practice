import java.util.Scanner;

/**
 * Xor Sequence:
 * https://www.hackerrank.com/challenges/xor-se
 *
 * Learned another cool thing about XOR -- pattern when computing 1 xor .. xor n.
 */
public class XorSequence {
  private static long xor1ToN(long n) {
    long[] res = new long[]{n, 1, n+1, 0};
    return res[(int)(n % 4)];
  }

  private static long getXor(long a) {
    if (a % 2 == 0) {
      return xor1ToN(a/2) * 2;
    } else {
      long k = xor1ToN(a/2) * 2;
      if ((a/2) % 2 == 0) k++;
      return k;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    for (int c = scanner.nextInt(); c > 0; c--) {
      long L = scanner.nextLong(), R = scanner.nextLong();
      System.out.println(getXor(R) ^ getXor(L-1));
    }
    scanner.close();
  }
}
