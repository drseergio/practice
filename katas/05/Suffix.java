public class Suffix implements Comparable<Suffix> {
  private final String text;
  private final int index;

  public Suffix(String text, int index) {
    this.text = text;
    this.index = index;
  }

  public int length() {
    return text.length() - index;
  }

  public int index() {
    return index;
  }

  public char charAt(int i) {
    return text.charAt(index + i); 
  }

  public int compareTo(Suffix that) {
    return compare(this, that);
  }

  public static int compare(Suffix a, Suffix b) {
    if (a == b) return 0;
    int N = Math.min(a.length(), b.length());
    for (int i = 0; i < N; i++) {
      if (a.charAt(i) < b.charAt(i)) return -1;
      if (a.charAt(i) > b.charAt(i)) return +1;
    }
    return a.length() - b.length();
  }

  public String toString() {
    return text.substring(index);
  }
}
