import java.util.Queue;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
  int N;
  int M;
  List<Pair>[] st;

  private class Pair {
    Key key;
    Value val;

    Pair(Key key, Value val) {
      this.key = key; this.val = val;
    }
  }

  public SeparateChainingHashST() {
    this(997);
  }

  public SeparateChainingHashST(int M) {
    this.M = M;
    st = (List<Pair>[]) new List[M];
    for (int i = 0; i < M; i++) st[i] = new LinkedList<Pair>();
  }

  private int hash(Key k) {
    return (k.hashCode() & 0x7fffffff) % M;
  }

  public void put(Key key, Value val) {
    List<Pair> chain = st[hash(key)];
    for (Pair p : chain) {
      if (p.key.equals(key)) {
        p.val = val;
        return;
      }
    }
    N++;
    chain.add(new Pair(key, val));
  }

  public Value get(Key key) {
    for (Pair p : st[hash(key)]) {
      if (p.key.equals(key)) return p.val;
    }
    return null;
  }

  public void delete(Key key) {
    Iterator<Pair> i = st[hash(key)].iterator();
    while (i.hasNext()) {
      if (i.next().key.equals(key)) {
        i.remove();
        N--;
        break;
      }
    }
    return;
  }

  public int size() {
    return N;
  }

  public Queue<Key> keys() {
    Queue<Key> queue = new LinkedList<Key>();
    for (int i = 0; i < M; i++) {
       for (Pair p : st[i]) queue.add(p.key);
    }
    return queue;
  }

  public static void main(String[] args) {
    SeparateChainingHashST<Integer, String> hst = new SeparateChainingHashST<Integer, String>();
    System.out.println("size: " + hst.size());
    hst.put(2, "hello");
    hst.put(4, "tater");
    hst.put(0, "dentist");
    hst.put(9, "velocity");
    hst.put(7, "grand");
    hst.put(19, "splendor");
    hst.put(25, "gratification");
    hst.put(31, "museum");
    hst.put(41, "trifecta");
    hst.put(0, "united");
    hst.delete(25);
    System.out.println("size: " + hst.size());

    for (Integer i : hst.keys()) {
       System.out.println(i + " : " + hst.get(i));
    }

    System.out.println("get(5): " + hst.get(5));
    System.out.println("get(4): " + hst.get(4));
  }
}
