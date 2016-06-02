import java.util.Scanner;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Maximize Sum:
 * https://www.hackerrank.com/challenges/maximize-sum
 */
public class MaximizeSum {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    for (int t = scanner.nextInt(); t > 0; t--) {
      long[] prefix = new long[scanner.nextInt()];
      long M = scanner.nextLong();
      long curr = 0;
      for (int i = 0; i < prefix.length; i++) {
        curr = (scanner.nextLong() % M + curr) % M;
        prefix[i] = curr;
      }

      long max = 0;
      TreeSet<Long> ts = new TreeSet<>();
      for (int i = 0; i < prefix.length; i++) {
        ts.add(prefix[i]);
        max = Math.max(max, prefix[i]); 
        Long next = ts.higher(prefix[i]);
        if (next != null) max = Math.max(max, (prefix[i] - next + M) % M);
      }
      System.out.println(max);
    }
    scanner.close();
  }
}
