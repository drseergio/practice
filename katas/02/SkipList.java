import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** Adapted from: https://github.com/spratt/SkipList. **/
public class SkipList<Key extends Comparable<Key>, Value> {
  private static final double PROBABILITY = 0.5;

  private class Node {
    Key key;
    Value val;
    List<Node> nextNodes = new ArrayList<>();
    List<Node> prevNodes = new ArrayList<>();

    Node(Key key, Value val) {
      this.key = key; this.val = val;
    }
  }

  private Node head;
  private int maxLevel;
  private int size;

  public SkipList() {
    head = new Node(null, null);
    head.nextNodes.add(null); 
    head.prevNodes = null;
  }

  public int size() {
    return size;
  }

  public Value get(Key key) {
    Node n = find(key, head, maxLevel);
    if (n == null || !key.equals(n.key)) return null;
    return n.val;
  }

  private Node find(Key key, Node curr, int level) {
    do {
      curr = findNext(key, curr, level);
    } while (level-- > 0);
    return curr;
  }

  private Node findNext(Key key, Node curr, int level) {
    Node next = curr.nextNodes.get(level);
    while (next != null) {
      Key nextKey = next.key;
      if (less(key, nextKey)) break;
      curr = next;
      next = curr.nextNodes.get(level);
    }
    return curr;
  }

  public void put(Key key, Value val) {
    if (val == null) throw new IllegalArgumentException("Value cannot be null");

    Node n = find(key, head, maxLevel);
    if (n != null && key.equals(n.key)) {
      n.val = val;
      return;
    }

    int level = 0;
    while (Math.random() < PROBABILITY)
      level++;
    while (level > maxLevel) {
      head.nextNodes.add(null);
      maxLevel++;
    }

    Node newNode = new Node(key, val);
    Node curr = head;
    do {
      curr = findNext(key, curr, level);
      Node newNodeNext = curr.nextNodes.get(level);
      curr.nextNodes.set(level, newNode);

      newNode.nextNodes.add(0, newNodeNext);
      newNode.prevNodes.add(0, curr);

      if (newNodeNext != null) newNodeNext.prevNodes.set(level, newNode);
    } while (level-- > 0);

    size++;
  }

  public void delete(Key key) {
    Node n = find(key, head, maxLevel);
    if (n == null || !key.equals(n.key)) return;

    for (int i = 0; i < n.prevNodes.size(); i++) {
      Node prev = n.prevNodes.get(i);
      Node newNext = n.nextNodes.get(i);
      prev.nextNodes.set(i, newNext);
      if (newNext != null) newNext.prevNodes.set(i, prev);
    }

    size--;
  }

  private boolean less(Key a, Key b) {
    return a.compareTo(b) < 0;
  }

  public Iterable<Key> keys() {
    LinkedList<Key> keys = new LinkedList<>();
    for (Node curr = head.nextNodes.get(0); curr != null; curr = curr.nextNodes.get(0))
      keys.add(curr.key);
    return keys;
  }

  public static void main(String[] args) {
    SkipList<Integer, String> sl = new SkipList<>();
    sl.put(9, "bug");
    sl.put(0, "zero");
    sl.put(4, "four");
    sl.put(7, "seven");
    sl.put(1, "one");
    sl.put(2, "two");
    sl.put(9, "nine");
    System.out.println("size: " + sl.size());

    for (Integer key : sl.keys()) {
      System.out.println(key + ": " + sl.get(key));
    }

    sl.delete(9);
    sl.delete(2);
    System.out.println("size after two deletes: " + sl.size());

    for (Integer key : sl.keys()) {
      System.out.println(key + ": " + sl.get(key));
    }
  }
}
