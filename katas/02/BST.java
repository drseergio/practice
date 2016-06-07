import java.util.Queue;
import java.util.LinkedList;

public class BST<Key extends Comparable<Key>, Value> {
  private Node root;
  private class Node {
    private Key key;
    private Value val;
    private Node left, right;
    private int N;

    public Node(Key key, Value val, int N) {
      this.key = key; this.val = val; this.N = N;
    }
  }

  public int size() {
    return size(root);
  }

  public int size(Node x) {
    if (x == null) return 0;
    return x.N;
  }

  public Value get(Key key) {
    return get(root, key);
  }

  private Value get(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0) return get(x.left, key);
    else if (cmp > 0) return get(x.right, key);
    else return x.val;
  }

  public void put(Key key, Value val) {
    root = put(root, key, val);
  }

  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val, 1);
    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = put(x.left, key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else x.val = val;
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }

  public int height() {
    return height(root);
  }

  private int height(Node x) {
    if (x == null) return -1;
    return 1 + Math.max(height(x.left), height(x.right));
  }

  public int rank(Key key) {
    return rank(key, root);
  }

  private int rank(Key key, Node x) {
    if (x == null) return 0;
    int cmp = key.compareTo(x.key);
    if (cmp < 0) return rank(key, x.left);
    else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
    else return size(x.left);
  }

  public Key select(int k) {
    Node x = select(root, k);
    return x.key;
  }

  private Node select(Node x, int k) {
    if (x == null) return null;
    int t = size(x.left);
    if (t > k) return select(x.left, k);
    else if (t < k) return select(x.right, k-t-1);
    else return x;
  }

  public Key floor(Key key) {
    Node x = floor(root, key);
    if (x == null) return null;
    return x.key;
  }

  private Node floor(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;
    if (cmp < 0) return floor(x.left, key);
    Node t = floor(x.right, key);
    if (t != null) return t;
    else return x;
  }

  public Key min() {
    return min(root).key;
  }

  private Node min(Node x) {
    if (x.left == null) return x;
    return min(x.left);
  }

  public Key max() {
    return max(root).key;
  }

  private Node max(Node x) {
    if (x.right == null) return x;
    return max(x.right);
  }

  public Iterable<Key> keys() {
    return keys(min(), max());
  }

  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new LinkedList<Key>();
    keys(root, queue, lo, hi);
    return queue;
  }

  private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
    if (x == null) return;
    int cmplo = lo.compareTo(x.key);
    int cmphi = hi.compareTo(x.key);
    if (cmplo < 0) keys(x.left, queue, lo, hi);
    if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
    if (cmphi > 0) keys(x.right, queue, lo, hi);
  }

  public static void main(String[] args) {
    BST<Integer, String> bst = new BST<Integer, String>();
    System.out.println("size: " + bst.size());
    bst.put(2, "hello");
    bst.put(4, "tater");
    bst.put(0, "dentist");
    bst.put(9, "velocity");
    bst.put(7, "grand");
    bst.put(19, "splendor");
    bst.put(25, "gratification");
    bst.put(31, "museum");
    bst.put(41, "trifecta");
    System.out.println("size: " + bst.size());
    System.out.println("height: " + bst.height());

    for (Integer i : bst.keys()) {
       System.out.println(i + " : " + bst.get(i));
    }

    System.out.println("get(5): " + bst.get(5));
    System.out.println("get(4): " + bst.get(4));
    //System.out.println("rank(7): " + bst.rank(7));
    //System.out.println("select(1): " + bst.select(1));
    System.out.println("min(): " + bst.min());
    System.out.println("max(): " + bst.max());
  }
}
