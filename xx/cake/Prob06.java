public class Prob06 {
  private static class Rectangle {
    // coordinates of bottom left corner
    Integer leftX;
    Integer bottomY;

    // dimensions
    Integer width;
    Integer height;

    public Rectangle(Integer leftX, Integer bottomY, Integer width, Integer height) {
      this.leftX = leftX;
      this.bottomY = bottomY;
      this.width  = width;
      this.height = height;
    }

    public Rectangle() {}

    public String toString() {
        return String.format("(%d, %d, %d, %d)", leftX, bottomY, width, height);
    }
  }

  public static Rectangle findOverlap(Rectangle a, Rectangle b) {
    RangeOverlap xOverlap = getOverlap(a.leftX, a.width, b.leftX, b.width);
    RangeOverlap yOverlap = getOverlap(a.bottomY, a.height, b.bottomY, b.width);
    if (xOverlap == null || yOverlap == null) return null;
    return new Rectangle(xOverlap.start, yOverlap.start, xOverlap.length, yOverlap.length);
  }

  private static class RangeOverlap {
    int start;
    int length;
    public RangeOverlap(int start, int length) {
      this.start = start; this.length = length;
    }
  }

  private static RangeOverlap getOverlap(int p1, int length1, int p2, int length2) {
    int highestStartPoint = Math.max(p1, p2);
    int lowestEndPoint = Math.min(p1+length1, p2+length2);
    if (highestStartPoint >= lowestEndPoint) return null;
    return new RangeOverlap(highestStartPoint, lowestEndPoint - highestStartPoint);
  }

  public static void main(String[] args) {
    Rectangle a = new Rectangle();
    Rectangle b = new Rectangle();
    System.out.println(findOverlap(a, b));
  }
}
