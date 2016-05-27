import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Scanner;

public class Candies {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int[] rank = new int[N];

    for (int i = 0; i < N; i++) {
      rank[i] = scanner.nextInt();
    }
    scanner.close();

    int[] candy = new int[N];
    Arrays.fill(candy, 1);

    int i = 0, j;
    boolean isIncrease = false;
    while (i < N-1) {
      j = i;

      if (rank[i+1] > rank[i]) {
        isIncrease = true;
      } else {
        isIncrease = false;
      }

      if (isIncrease) {
        while (i < N-1 && rank[i+1] > rank[i]) i++;
        for (int x = j+1; x <= i; x++) {
          if (candy[x] <= candy[x-1]) candy[x] = candy[x-1] + 1;
        }
      } else {
        while (i < N-1 && rank[i+1] <= rank[i]) i++;
        for (int x = i-1; x >= j; x--) {
          if (candy[x] <= candy[x+1] && rank[x] > rank[x+1]) candy[x] = candy[x+1] + 1;
        }
      }
    }
    System.out.println(Arrays.toString(candy));
    System.out.println(IntStream.of(candy).sum());
  }
}
