import java.util.Scanner;
import java.math.BigInteger;

public class CounterGame {
  public static final boolean RICHARD = true;
  public static final boolean LOUISE = false;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    for (int i = 0; i < T; i++) {
      long N = scanner.nextBigInteger().longValue();
      boolean winner = RICHARD;
      while (N != 1) {
        // check if number is a power of 2
        if ((N & (N - 1)) == 0) {
          // divide number by 2
          N >>>= 1;
        } else {
          // subtract largest power of 2
          N = N & ~Long.highestOneBit(N);
        }
        winner = !winner;
      }
      System.out.println(winner == RICHARD ? "Richard" : "Louise");
    }
    scanner.close();
  }
}
