public class E53 {
  public static int swap(int x, int i, int j) {
    if (((x >>> i) & 1) != ((x >>> j) & 1)) {
      int mask = (1 << i) | (1 << j);
      return x ^ mask;
    }
    return x;
  }

  public static void main(String args[]) {
    int x = 93, i = 1, j = 6;
    System.out.println(swap(x, i, j)); // 31
  }
}
