import java.util.Scanner;

public class BinaryGap {
  public static int solution(int N) {
    int max = 0, curr = 0;
    boolean in = false;
    while (N != 0) {
      if ((N & 1) == 1) {
        max = Math.max(max, curr);
        curr = 0;
        in = true;
      } else if (in) {
        curr++;
      }
      N >>>= 1;
    }

    return max;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(solution(scanner.nextInt()));
    scanner.close();
  }
}
