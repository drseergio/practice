import java.util.LinkedList;
import java.util.Queue;

public class TST<Value> {
  private Node root;

  private class Node {
    char c;
    Node left, mid, right;
    Value val;
  }

  public Value get(String key) {
    Node x = get(root, key, 0);
    if (x == null) return null;
    return x.val;
  }

  private Node get(Node x, String key, int d) {
    if (x == null) return null;
    char c = key.charAt(d);
    if (c < x.c) return get(x.left, key, d);
    else if (c > x.c) return get(x.right, key, d);
    else if (d < key.length() - 1) return get(x.mid, key, d+1);
    else return x;
  }

  public void put(String key, Value val) {
    root = put(root, key, val, 0);
  }

  public Node put(Node x, String key, Value val, int d) {
    char c = key.charAt(d);
    if (x == null) {
       x = new Node();
       x.c = c;
    }
    if (c < x.c) x.left = put(x.left, key, val, d);
    else if (c > x.c) x.right = put(x.right, key, val, d);
    else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d+1);
    else x.val = val;
    return x;
  }

  public int size() {
    return size(root);
  }

  private int size(Node x) {
    if (x == null) return 0;
    int cnt = 0;
    if (x.val != null) cnt++;
    cnt += size(x.left) + size(x.right) + size(x.mid);
    return cnt;
  }

  public Iterable<String> keys() {
    Queue<String> q = new LinkedList<>();
    collect(root, new StringBuilder(), q);
    return q;
  }

  public Iterable<String> keysWithPrefix(String pre) {
    Queue<String> q = new LinkedList<>();
    Node x = get(root, pre, 0);
    if (x == null) return q;
    if (x.val != null) q.add(pre);
    collect(x.mid, new StringBuilder(pre), q);
    return q;
  }

  private void collect(Node x, StringBuilder pre, Queue<String> q) {
    if (x == null) return;
    collect(x.left, pre, q);
    if (x.val != null) q.add(pre.toString() + x.c);
    collect(x.mid, pre.append(x.c), q);
    pre.deleteCharAt(pre.length() - 1);
    collect(x.right, pre, q);
  }

  public String longestPrefixOf(String s) {
    int length = 0;
    Node x = root;
    int i = 0;
    while (x != null && i < s.length()) {
      char c = s.charAt(i);
      if (c < x.c) x = x.left;
      else if (c > x.c) x = x.right;
      else {
        i++;
        if (x.val != null) length = i;
        x = x.mid;
      }
    }
    return s.substring(0, length);
  }

  public static void main(String[] args) {
    TST<Integer> tst = new TST<>();
    tst.put("hello", 13);
    tst.put("he", 0);
    tst.put("jelly", 7);
    tst.put("stop", 3);
    tst.put("gender", 39);
    tst.put("helloz", 98);
    tst.put("hello my friend", 15);

    System.out.println(tst.get("helloz"));
    System.out.println(tst.get("hhelloz"));
    System.out.println(tst.get("he"));
    System.out.println(tst.get("hello"));
    System.out.println(tst.get("hell"));

    System.out.println("\nsize: " + tst.size());
    System.out.println("\nKeys starting with h:");
    for (String s : tst.keysWithPrefix("h"))
      System.out.println(s);
    System.out.println("\nALL keys:");
    for (String s : tst.keys())
      System.out.println(s);
    System.out.println("\nLongest prefix of jellybeans:");
    System.out.println(tst.longestPrefixOf("jellybeans"));
  }
}
