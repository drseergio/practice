import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;

/** The code used to be simpler on Java 6 because substring was O(1). **/
public class SuffixArray {
  private Suffix[] suffixes;

  public SuffixArray(String text) {
    int N = text.length();
    suffixes = new Suffix[N];
    for (int i = 0; i < N; i++)
      suffixes[i] = new Suffix(text, i);
    Arrays.sort(suffixes);
  }

  public String select(int i) {
    return suffixes[i].toString();
  }

  public int index(int i) {
    return suffixes[i].index();
  }

  public int length() {
    return suffixes.length;
  }

  public int lcp(int i) {
    return lcp(suffixes[i], suffixes[i-1]);
  }

  private static int lcp(Suffix a, Suffix b) {
    int N = Math.min(a.length(), b.length());
    for (int i = 0; i < N; i++) {
      if (a.charAt(i) != b.charAt(i)) return i;
    }
    return N;
  }

  public int rank(String q) {
    Suffix query = new Suffix(q, 0);
    int lo = 0, hi = length() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = Suffix.compare(query, suffixes[mid]);
      if (cmp < 0) hi = mid - 1;
      else if (cmp > 0) lo = mid + 1;
      else return mid;
    }
    return lo;
  }

  public static void main(String[] args) {
    String s = StdIn.readAll().replaceAll("\\s+", " ").trim();
    SuffixArray suffix = new SuffixArray(s);

    System.out.println("  i ind lcp rnk select");
    System.out.println("---------------------------");

    for (int i = 0; i < s.length(); i++) {
      int index = suffix.index(i);
      String ith = "\"" + s.substring(index, Math.min(index + 50, s.length())) + "\"";
      int rank = suffix.rank(s.substring(index));

      if (i == 0) {
        System.out.printf("%3d %3d %3s %3d %s\n", i, index, "-", rank, ith);
      } else {
        int lcp = suffix.lcp(i);
        System.out.printf("%3d %3d %3d %3d %s\n", i, index, lcp, rank, ith);
      }
    }
  }
}
