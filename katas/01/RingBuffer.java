import java.util.Arrays;

public class RingBuffer<Key extends Comparable<Key>> {
  private Key[] elements;
  private int offset;
  private int N;

  public RingBuffer(int size) {
    elements = (Key[]) new Comparable[size];
  }

  public void push(Key key) {
    if (N == elements.length) throw new IndexOutOfBoundsException("buffer is full");
    elements[offset] = key;
    offset = (offset + 1) % elements.length;
    N++;
  }

  public Key pop() {
    int index = (offset + (elements.length - N)) % elements.length;
    Key key = elements[index];
    elements[index] = null;
    N--;
    return key;
  }

  public Key peek() {
    return elements[(offset + (elements.length - N)) % elements.length];
  }

  public int size() {
    return elements.length;
  }

  public int count() {
    return N;
  }

  public String toString() {
    return "N=" + count() + "   L=" + size() + ",   " + Arrays.toString(elements);
  }

  public static void main(String[] args) {
    RingBuffer<Integer> rb = new RingBuffer<>(5);
    rb.push(5);
    rb.push(13);
    rb.push(9);
    rb.push(45);
    System.out.println(rb);
    System.out.println("after one delete " + rb.pop());
    System.out.println(rb);
    System.out.println("peek: " + rb.peek());
    System.out.println("after one delete " + rb.pop());
    System.out.println(rb);
    rb.push(81);
    rb.push(22);
    System.out.println(rb);
    rb.push(1);
    System.out.println(rb);
    try {
      rb.push(6);
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    }
    System.out.println("after one delete " + rb.pop());
    System.out.println(rb);
  }
}
