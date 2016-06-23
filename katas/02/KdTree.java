import java.util.LinkedList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/** Adapted from: https://github.com/dychen/algs4/blob/master/kdtree/KdTree.java */
public class KdTree {
  public static final double MAX_X = 10.0;
  public static final double MAX_Y = 10.0;
  private Node root;

  private static class Node {
    private Point2D p;
    private RectHV r;
    private Node left, right;

    Node(Point2D p, RectHV r) {
      this.p = p; this.r = r;
    }
  }

  public boolean contains(Point2D p) {
    return contains(root, p, true);
  }

  private boolean contains(Node node, Point2D p, boolean xcmp) {
    if (node == null) return false;
    else if (node.p.x() == p.x() && node.p.y() == p.y()) return true;
    else if (xcmp) {
      if (p.x() < node.p.x())
        return contains(node.left, p, !xcmp);
      else
         return contains(node.right, p, !xcmp);
    } else {
      if (p.y() < node.p.y())
        return contains(node.left, p, !xcmp);
      else
        return contains(node.right, p, !xcmp);
    }
  }

  public void insert(Point2D p) {
    root = insert(root, p, 0.0, 0.0, MAX_X, MAX_Y, true);
  }

  private Node insert(Node node, Point2D p, double x0, double y0, double x1, double y1, boolean xcmp) {
    if (node == null) return new Node(p, new RectHV(x0, y0, x1, y1));
    else if (node.p.x() == p.x() && node.p.y() == p.y()) return node;
    else if (xcmp) {
      if (p.x() < node.p.x())
        node.left = insert(node.left, p, x0, y0, node.p.x(), y1, !xcmp);
      else
        node.right = insert(node.right, p, node.p.x(), y0, x1, y1, !xcmp);
    } else {
      if (p.y() < node.p.y())
        node.left = insert(node.left, p, x0, y0, x1, node.p.y(), !xcmp);
      else
        node.right = insert(node.right, p, x0, node.p.y(), x1, y1, !xcmp);
    }
    return node;
  }

  public Iterable<Point2D> range(RectHV query) {
    LinkedList<Point2D> points = new LinkedList<Point2D>();
    range(root, query, points);
    return points;
  }

  private void range(Node node, RectHV rect, LinkedList<Point2D> list) {
    if (node == null) return;
    if (rect.contains(node.p)) {
      list.add(node.p);
    }
    if (rect.intersects(node.r)) {
      range(node.left, rect, list);
      range(node.right, rect, list);
    }
  }

  public Point2D nearest(Point2D p) {
    if (root == null) return null;
    return nearest(root, p, root.p, true);
  }

  private Point2D nearest(Node node, Point2D p, Point2D closestSoFar, boolean xcmp) {
    if (node == null) return closestSoFar;
    if (node.p.distanceSquaredTo(p) < closestSoFar.distanceSquaredTo(p))
      closestSoFar = node.p;
    if (node.r.distanceSquaredTo(p) < closestSoFar.distanceSquaredTo(p)) {
      Node first;
      Node second;
      if ((xcmp && (p.x() < node.p.x())) || (!xcmp && (p.y() < node.p.y()))) {
        first = node.left;
        second = node.right;
      } else {
        first = node.right;
        second = node.left;
      }
      closestSoFar = nearest(first, p, closestSoFar, !xcmp);
      closestSoFar = nearest(second, p, closestSoFar, !xcmp);
    }
    return closestSoFar;
  }

  public static void main(String[] args) {
    double[] xPoints = {1.8, 1.85, 2.34, 2.52, 3.23, 3.49, 4.91, 5.12, 6.00, 6.71};
    double[] yPoints = {1.6, 4.71, 0.69, 3.20, 2.39, 4.73, 1.18, 3.21, 4.89, 1.75};

    RectHV query = new RectHV(2, 2, 4, 5);

    KdTree kd = new KdTree();
    for (int i = 0; i < xPoints.length; i++)
      kd.insert(new Point2D(xPoints[i], yPoints[i]));

    System.out.println("Range search:");
    for (Point2D p : kd.range(query))
      System.out.println(p);

    System.out.println("\nNearest neighbors:");
    for (int i = 0; i < xPoints.length; i++) {
      System.out.println("[" + i + "] " + kd.nearest(new Point2D(xPoints[i], yPoints[i])));
    }
  }
}
