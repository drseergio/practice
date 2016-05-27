public class E56 {
  public static long divide(long x, long y) {
    long res = 0;
    int power = 32;
    long yPower = y << power;

    while (x >= y) {
      while (yPower > x) {
        yPower >>>= 1;
        --power;
      }

      res += 1L << power;
      x -= yPower;
    }

    return res;
  }

  public static void main(String args[]) {
    int x = 11211, y = 17;
    System.out.println(x / y);
    System.out.println(divide(x, y));
  }
}
