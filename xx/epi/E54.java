public class E54 {
  public static int reverse(int x) {
    for (int i = 0; i < 16; i++) {
      x = swap(x, i, 32-i-1);
    }
    return x;
  }

  public static int swap(int x, int i, int j) {
    if (((x >>> i) & 1) != ((x >>> j) & 1)) {
      int mask = (1 << i) | (1 << j);
      return x ^ mask;
    }
    return x;
  }

  public static void main(String args[]) {
    int x = 170, y = reverse(x);
    System.out.println(y);
    System.out.println(Integer.reverse(x));
  }
}
