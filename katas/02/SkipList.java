import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Adapted from: https://github.com/spratt/SkipList. **/
public class SkipList<Key extends Comparable<Key>, Value> implements Iterable<Key> {
  private static final double PROBABILITY = 0.5;

  private class Node {
    Key key;
    Value val;
    List<Node> nextNodes;
    List<Node> prevNodes;

    Node(Key key, Value val) {
      this.key = key;
      this.val = val;
      nextNodes = new ArrayList<>();
      prevNodes = new ArrayList<>();
    }
  }

  private Node head;
  private int maxLevel;
  private int size;

  public SkipList() {
    size = 0;
    maxLevel = 0;
    head = new Node(null, null);
    head.nextNodes.add(null); 
    head.prevNodes = null;
  }

  public int size() {
    return size;
  }

  public Value get(Key key) {
    Node node = find(key);
    if (node == null) return null;
    if (node.key != null && node.key.equals(key)) return node.val;
    return null;
  }

  private Node find(Key key) {
    return find(key, head, maxLevel);
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

    Node n = find(key);
    if (n != null && n.key != null && n.key.equals(key)) {
      n.val = val;
      return;
    }

    size++;
    int level = 0;
    while (Math.random() < PROBABILITY) level++;

    while (level > maxLevel) {
      head.nextNodes.add(null);
      maxLevel++;
    }

    Node newNode = new Node(key, val);
    Node curr = head;
    do {
      curr = findNext(key, curr, level);
      Node newNext = curr.nextNodes.get(level);
      newNode.nextNodes.add(0, newNext);
      newNode.prevNodes.add(0, curr);
      if (newNext != null) newNext.prevNodes.set(level, newNode);
      curr.nextNodes.set(level, newNode);
    } while (level-- > 0);
  }

  public void delete(Key key) {
    if (get(key) == null) return;

    Node n = find(key);
    for (int i = 0; i < n.prevNodes.size(); i++) {
      Node prev = n.prevNodes.get(i);
      Node newNext = n.nextNodes.get(i);
      prev.nextNodes.set(i, newNext);
      if (newNext != null) newNext.prevNodes.set(i, prev);
    }
    size--;
  }

  public Iterator<Key> iterator() {
    return new SkipListIterator(this);
  }

  private boolean less(Key a, Key b) {
    return a.compareTo(b) < 0;
  }

  private class SkipListIterator implements Iterator<Key> {
    SkipList<Key, Value> list;
    Node curr;

    public SkipListIterator(SkipList<Key, Value> list) {
      this.list = list;
      this.curr = list.head;
    }

    public boolean hasNext() {
      return curr.nextNodes.get(0) != null;
    }

    public Key next() {
      curr = (Node)curr.nextNodes.get(0);
      return (Key)curr.key;
    }

    public void remove() {}
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

    for (Integer key : sl) {
      System.out.println(key + ": " + sl.get(key));
    }

    sl.delete(9);
    sl.delete(2);
    System.out.println("size after two deletes: " + sl.size());

    for (Integer key : sl) {
      System.out.println(key + ": " + sl.get(key));
    }
  }
}
