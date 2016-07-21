import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MandragoraForest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();

    for (int t = 0; t < T; t++) {
      int N = scanner.nextInt();
      long[] mandragoras = new long[N];
      for (int i = 0; i < N; i++) mandragoras[i] = scanner.nextLong();

      Arrays.sort(mandragoras);
      for (int i = N-1; i > 0; i--) mandragoras[i-1] += mandragoras[i];

      long solution = 0;
      for (int i = N-1; i >= 0; i--) {
        long S = i + 1;
        long P = mandragoras[i] * S;

        if (P > solution) solution = P;
        else break;
      }

      System.out.println(solution);
    }
    scanner.close();
  }
}
