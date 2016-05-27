public class Power {
  public static double power(double x, int y) {
    int power = y;
    double result = 1.0;
    if (y < 0) {
      x = 1 / x;
      power = -power;
    }

    while (power != 0) {
      if ((power & 0x1) == 1) {
        result *= x;
      }

      x *= x;

      power >>>= 1;
    }

    return result;
  }

  public static void main(String[] args) {
    double x = 16;
    int y = -2;
    System.out.println(power(x, y));
  }
}
