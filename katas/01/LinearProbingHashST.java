import java.util.Queue;
import java.util.LinkedList;

public class LinearProbingHashST<Key, Value> {
  int N;
  int M;
  Key[] keys;
  Value[] vals;

  public LinearProbingHashST() {
    this(8);
  }

  private LinearProbingHashST(int M) {
    keys = (Key[]) new Object[M];
    vals = (Value[]) new Object[M];
    this.M = M;
  }

  private int hash(Key k) {
    return (k.hashCode() & 0x7fffffff) % M;
  }

  public void put(Key key, Value val) {
    System.out.println("put(" + key + ", " + val + ")");
    if (N >= M/2) resize(M*2);

    int i;
    for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (keys[i].equals(key)) {
        vals[i] = val;
        return;
      }
    }
    N++;
    keys[i] = key;
    vals[i] = val;
  }

  private void resize(int M) {
    System.out.println("Resize called for M=" + M);
    LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(M);
    for (int i = 0; i < keys.length; i++) {
      if (keys[i] != null) {
        t.put(keys[i], vals[i]);
      }
    }
    keys = t.keys;
    vals = t.vals;
    this.M = t.M;
  }

  public Value get(Key key) {
    for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (keys[i].equals(key)) return vals[i];
    }
    return null;
  }

  public int size() {
    return N;
  }

  public Queue<Key> keys() {
    Queue<Key> queue = new LinkedList<Key>();
    for (int i = 0; i < keys.length; i++) {
      if (keys[i] != null) queue.add(keys[i]);
    }
    return queue;
  }

  public void delete(Key key) {
    System.out.println("delete(" + key + ")");
    if (get(key) == null) return;

    int i = hash(key);
    while (!key.equals(keys[i])) i = (i + 1) % M;
    keys[i] = null;
    vals[i] = null;

    i = (i + 1) % M;

    while (keys[i] != null) {
      Key keyToRedo = keys[i];
      Value valToRedo = vals[i];
      keys[i] = null;
      vals[i] = null;
      N--;
      put(keyToRedo, valToRedo);
      i = (i + 1) % M;
    }

    N--;
    if (N > 0 && N == M/8) resize(M/2);
  }

  public static void main(String[] args) {
    LinearProbingHashST<Integer, String> hst = new LinearProbingHashST<Integer, String>();
    System.out.println("size: " + hst.size());
    hst.put(2, "hello");
    hst.put(4, "tater");
    hst.put(0, "dentist");
    hst.put(9, "velocity");
    hst.put(7, "grand");
    hst.put(19, "splendor");
    hst.put(25, "gratification");
    hst.delete(7);
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
