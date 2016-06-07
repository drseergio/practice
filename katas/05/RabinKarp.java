import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
  public static final int R = 256;
  private String pat;
  private long Q;
  private long RM;
  private long patHash;

  public RabinKarp(String pat) {
    this.pat = pat;
    int M = pat.length();
    Q = longRandomPrime();
    RM = 1;
    for (int i = 1; i <= M-1; i++)
      RM = (R * RM) % Q;
    patHash = hash(pat, M);
  }

  public int search(String txt) {
    System.out.println("Q: " + Q + ", RM: " + RM);
    int N = txt.length();
    int M = pat.length();
    long txtHash = hash(txt, M);
    if (patHash == txtHash) return 0;

    for (int i = M; i < N; i++) {
      txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
      txtHash = (txtHash * R + txt.charAt(i)) % Q;
      if (patHash == txtHash) return i - M + 1;
    }

    return N;
  }

  private long longRandomPrime() {
    BigInteger prime = BigInteger.probablePrime(31, new Random());
    return prime.longValue();
  }

  private long hash(String key, int M) {
    long h = 0;
    for (int j = 0; j < M; j++)
      h = (R * h + key.charAt(j)) % Q;
    return h;
  }

  public static void main(String[] args) {
    String txt = args[1];
    String pat = args[0];
    RabinKarp kmp = new RabinKarp(pat);
    System.out.println("text:    " + txt);
    int offset = kmp.search(txt);
    System.out.print("pattern: ");
    for (int i = 0; i < offset; i++)
      System.out.print(" ");
    System.out.println(pat);
  }
}
