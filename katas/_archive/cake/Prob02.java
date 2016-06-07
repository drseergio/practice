import java.util.Arrays;

public class Prob02 {
  public static long[] getAllProductsExceptIndex(int[] a) {
    int N = a.length;
    long[] productForward = new long[N];
    productForward[0] = a[0];
    for (int i = 1; i < N; i++)
      productForward[i] = productForward[i-1] * a[i];

    long productBackward = a[N-1];
    productForward[N-1] = productForward[N-2];
    for (int i = N-2; i >= 1; i--) {
      productForward[i] = productForward[i-1] * productBackward;
      productBackward *= a[i];
    }
    productForward[0] = productBackward;
    return productForward;
  }

  public static void main(String[] args) {
    int[] input = new int[]{1, 7, 3, 4};
    System.out.println(Arrays.toString(getAllProductsExceptIndex(input)));
  }
}
