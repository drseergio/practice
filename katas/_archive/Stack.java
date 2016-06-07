import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
  private Node first;
  private int size;

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void push(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("null values are not allowed");
    }
    Node newNode = new Node();
    newNode.next = first;
    newNode.item = item;
    first = newNode;
    size++;
  }

  public Item pop() {
    if (isEmpty()) {
      throw new IllegalStateException("stack is empty");
    }
    Item item = first.item;
    first = first.next;
    size--;
    return item;
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    Node curr = first;

    public Item next() {
      Item item = curr.item;
      curr = first.next;
      return item;
    }

    public boolean hasNext() {
      return curr != null;
    }

    public void remove() {}
  }

  private class Node {
    Node next;
    Item item;
  }

  public static void main(String[] args) {
    Stack<String> stack = new Stack<String>();
    stack.push("Hello");
    stack.push("");
    System.out.println(stack.size());
    System.out.println(stack.isEmpty());
    System.out.println("pop(): " + stack.pop());
    System.out.println("pop(): " + stack.pop());
    System.out.println(stack.size());
    stack.pop();
  }
}
