public class E55 {
  public static int multiply(int x, int y) {
    int res = 0;

    while (y != 0) {
      if ((y & 1) == 1) {
        res = add(res, x);
      }

      y >>>= 1;
      x <<= 1;
    }

    return res;
  }

  public static int m2(int x, int y) {
    int sum = 0;
    while (x != 0) {
      if ((x & 1) != 0) {
        sum = add(sum, y);
      }
      x >>>= 1;
      y <<= 1;
    }
    return sum;
  }

  private static int add(int a, int b) {
    int sum = 0, carryin = 0, k = 1, tempA = a, tempB = b;
    while (tempA != 0 || tempB != 0) {
      int ak = a & k, bk = b & k;
      int carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
      sum |= (ak ^ bk ^ carryin);
      carryin = carryout << 1;
      k <<= 1;
      tempA >>>= 1;
      tempB >>>= 1;
    }
    return sum | carryin;
  }

  public static void main(String args[]) {
    int x = 241, y = 17;
    System.out.println(multiply(x, y));
    System.out.println(m2(x, y));
    System.out.println(x * y);
  }
}
