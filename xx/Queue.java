public class Queue<Item> {
  private int size;
  private Node first;
  private Node last;

  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException("item cannot be null");
    Node newNode = new Node();
    newNode.item = item;

    if (isEmpty()) {
      first = last = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }

    size++;
  }

  public Item dequeue() {
    if (isEmpty()) throw new IllegalStateException("queue is empty");

    Item item = first.item;
    if (size == 1) {
      first = last = null; 
    } else {
      first = first.next;
    }

    size--;
    return item;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private class Node {
    Node next;
    Item item;
  }

  public static void main(String[] args) {
    Queue<Integer> queue = new Queue<Integer>();
    queue.enqueue(5);
    queue.enqueue(8);
    queue.enqueue(3);
    queue.enqueue(7);

    System.out.println(queue.dequeue());
    queue.enqueue(0);
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
  }
}
