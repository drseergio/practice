import java.util.Scanner;

public class MaxXor {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int L = scanner.nextInt();
    int R = scanner.nextInt();
    int max = 0;
    for (int i = L; i <= R; i++) {
      for (int j = i; j <= R; j++) {
        max = Math.max(max, j ^ i);
      }
    }
    System.out.println(max);
    scanner.close();
  }
}
