import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Knapsack {
  private static HashMap<Integer, Integer> cache = new HashMap<>();

  public static int max(int n, List<Integer> values) {
    if (n < values.get(0)) return 0;
    else if (cache.containsKey(n)) return cache.get(n);

    int max = Integer.MIN_VALUE;
    for (int v : values) {
      int remaining = n - v;
      if (remaining == 0) {
        max = Math.max(max, v);
      } else if (remaining > 0) {
        max = Math.max(max, v + max(remaining, values));
      }
    }
    cache.put(n, max);
    return max;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    ArrayList<Integer> values = new ArrayList<>();

    for (int i = 0; i < T; i++) {
      int N = scanner.nextInt();
      int k = scanner.nextInt();
      for (int j = 0; j < N; j++) {
        values.add(scanner.nextInt());
      }
      Collections.sort(values);
      System.out.println(max(k, values));
      values.clear();
      cache.clear();
    }
    scanner.close();
  }
}
