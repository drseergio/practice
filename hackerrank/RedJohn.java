import java.util.Scanner;
import java.util.Arrays;

public class RedJohn {
  private static int ways;

  public static int countWays(int N) {
    ways = 0;
    countWays(0, N);
    return ways;
  }

  private static void countWays(int i, int N) {
    if (i == N) {
      ways++;
      return;
    } else if (i > N) {
      return;
    } else {
      countWays(i+1, N);
      countWays(i+4, N);
    }
  }

  private static int countPrimes(int M) {
    if (M < 2) return 0;
    boolean[] primes = new boolean[M+1];
    int count = 0;
    Arrays.fill(primes, true);
    for (int i = 2; i <= M; i++) {
      if (primes[i]) count++;
      System.out.println(i + " " + primes[i]);
      for (int j = i + i; j <= M; j += i) {
        primes[j] = false;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    for (int i = 0; i < T; i++) {
      System.out.println(countPrimes(countWays(scanner.nextInt())));
    }
    scanner.close();
  }
}
