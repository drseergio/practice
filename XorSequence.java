import java.util.Scanner;

/**
 * Xor Sequence:
 *
 *
 *
 * Learned another cool thing about XOR -- pattern when computing 1 xor .. xor n.
 */
public class XorSequence {
  private static long xor1ToN(long n) {
    long rem = n % 4;
    if (rem == 0) return n;
    else if (rem == 1) return 1;
    else if (rem == 2) return n + 1;
    else if (rem == 3) return 0;

    return 0;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    for (int c = scanner.nextInt(); c > 0; c--) {
      long L = scanner.nextLong(), R = scanner.nextLong(), count = 1 + (R-L);
      long answer;
      if (count % 2 == 1) {
        answer = xor1ToN(L);
        for (long i = L+2; i <= R; i += 2) {
          answer ^= i;
        }
      } else {
        answer = 0;
        for (long i = L+1; i <= R; i += 2) {
          answer ^= i;
        }
      }
      System.out.println(answer);
    }
    scanner.close();
  }
}
