import java.util.Scanner;

public class CoinChange {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int M = scanner.nextInt();

    long[] change = new long[N+1];
    change[0] = 1;

    int[] coins = new int[M];
    for (int i = 0; i < M; i++) coins[i] = scanner.nextInt();

    for (int i = 0; i < M; i++)
      for (int j = coins[i]; j <= N; j++)
        change[j] += change[j-coins[i]];

    System.out.println(change[N]);

    scanner.close();
  }
}
