import java.util.LinkedList;
import java.util.Deque;

public class Hanoi {
  public static void towers(int n) {
    Deque<Integer> src = new LinkedList<Integer>() {
      public String toString() { return "src"; }
    };
    Deque<Integer> dst = new LinkedList<Integer>() {
      public String toString() { return "dst"; }
    };
    Deque<Integer> aux = new LinkedList<Integer>() {
      public String toString() { return "aux"; }
    };
    for (int i = n; i >= 1; i--)
      src.push(i);
    move(src, dst, aux, n);
  }

  private static void move(Deque<Integer> src, Deque<Integer> dst, Deque<Integer> aux, int n) {
    if (n == 0) return;
    move(src, aux, dst, n-1);
    dst.push(src.pop());
    System.out.println("Moved " + dst.peek() + " from " + src + " to " + dst);
    move(aux, dst, src, n-1);
  }

  public static void main(String[] args) {
    towers(5);
  }
}
