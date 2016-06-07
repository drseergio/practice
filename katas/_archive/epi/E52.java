import java.util.Random;

public class E52 {
  public static int parity(long n) {
    short res = 0;
    while (n != 0) {
      res ^= 1;
      n &= (n-1);
    }
    return res;
  }

  public static int parity2(long n) {
    n ^= n >>> 32;
    n ^= n >>> 16;
    n ^= n >>> 8;
    n ^= n >>> 4;
    n ^= n >>> 2;
    n ^= n >>> 1;
    return (int)n & 0x1;
  }

  public static void main(String args[]) {
    Random r = new Random();
    long n = r.nextLong();
    System.out.println(n + " parity: " + parity(n) + " " + parity2(n));
  }
}
