public class Prob03 {
  public static int maxProductOf3(int[] a) {
    int highest = Math.max(a[0], a[1]);
    int lowest = Math.min(a[0], a[1]);
    int lowestProductOf2 = a[0] * a[1];
    int highestProductOf2 = a[0] * a[1];
    int highestProductOf3 = a[0] * a[1] * a[2];

    for (int i = 2; i < a.length; i++) {
      highestProductOf3 = Math.max(
          highestProductOf3,
          Math.max(a[i] * lowestProductOf2, a[i] * highestProductOf2));

      highestProductOf2 = Math.max(
          highestProductOf2,
          Math.max(a[i] * highest, a[i] * lowest));

      lowestProductOf2 = Math.min(
          lowestProductOf2,
          Math.min(a[i] * highest, a[i] * lowest));

      highest = Math.max(a[i], highest);
      lowest = Math.min(a[i], lowest);
    }

    return highestProductOf3;
  }

  public static void main(String[] args) {
    int[] input = new int[]{4, -8, 2, -6, 1, 1, 5, 7};
    System.out.println(maxProductOf3(input));
  }
}
