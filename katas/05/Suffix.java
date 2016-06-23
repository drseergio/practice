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
    if (this == that) return 0;
    int N = Math.min(this.length(), that.length());
    for (int i = 0; i < N; i++) {
      if (this.charAt(i) < that.charAt(i)) return -1;
      if (this.charAt(i) > that.charAt(i)) return +1;
    }
    return this.length() - that.length();
  }

  public String toString() {
    return text.substring(index);
  }
}
