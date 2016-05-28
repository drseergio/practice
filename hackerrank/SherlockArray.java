import java.util.Scanner;

/**
 * Sherlock and Array:
 * https://www.hackerrank.com/challenges/sherlock-and-array
 */
public class SherlockArray {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    outer:
    for (int c = scanner.nextInt(); c > 0; c--) {
      int[] a = new int[scanner.nextInt()];

      for (int i = 0; i < a.length; i++)
        a[i] = scanner.nextInt();

      int[] leftSum = new int[a.length], rightSum = new int[a.length];
      leftSum[0] = a[0];
      rightSum[a.length-1] = a[a.length-1];

      for (int i = 1; i < a.length; i++) {
        leftSum[i] = leftSum[i-1] + a[i];
        rightSum[a.length-i-1] = a[a.length-i-1] + rightSum[a.length-i];
      }

      for (int i = 0; i < a.length; i++) {
        int left = i == 0 ? 0 : leftSum[i-1];
        int right = i == a.length-1 ? 0 : rightSum[i+1];
        if (left == right) {
          System.out.println("YES");
          continue outer;
        }
      }

      System.out.println("NO");
    }
    scanner.close();
  }
}
