import java.util.Scanner;

public class LonelyInteger {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int accumulator = 0;
    for (int i = 0; i < N; i++) {
      accumulator ^= scanner.nextInt();
    }
    scanner.close();
    System.out.println(accumulator);
  }
}
