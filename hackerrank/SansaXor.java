import java.util.Scanner;

public class SansaXor {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    for (int c = scanner.nextInt(); c > 0; c--) {
      int[] nums = new int[scanner.nextInt()];
      for (int i = 0; i < nums.length; i++) {
        nums[i] = scanner.nextInt();
      }
      if (nums.length % 2 == 0) {
        System.out.println(0);
        continue;
      }
      int res = 0;
      for (int i = 0; i < nums.length; i += 2) {
        res ^= nums[i];
      }
      System.out.println(res);
    }
    scanner.close();
  }
}
