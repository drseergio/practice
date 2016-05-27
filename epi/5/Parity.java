public class Parity {
  private static short[] precomputedParity;
  private static int WORD_LENGTH = 16;
  private static int BIT_MASK = 0xFFFF;

  static {
    precomputedParity = new short[1 << 16];
    for (int i = 0; i < precomputedParity.length; i++)
      precomputedParity[i] = paritySlow(i);
  }

  private static short paritySlow(long n) {
     n ^= n >>> 32;
     n ^= n >>> 16;
     n ^= n >>> 8;
     n ^= n >>> 4;
     n ^= n >>> 2;
     n ^= n >>> 1;
     return (short)(n & 0x1);
  }

  public static short parity(long n) {
    return (short)(precomputedParity[(int)((n >>> WORD_LENGTH*3) & BIT_MASK)]
      ^ precomputedParity[(int)((n >>> WORD_LENGTH*2) & BIT_MASK)]
      ^ precomputedParity[(int)((n >>> WORD_LENGTH) & BIT_MASK)]
      ^ precomputedParity[(int)(n & BIT_MASK)]);
  }

  public static void main(String[] args) {
    long x = 0b1001011100;
    System.out.println(parity(x));
  }
}
