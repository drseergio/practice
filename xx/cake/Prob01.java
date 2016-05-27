public class Prob01 {
  public static int getMaxProfit(int[] prices) {
    int minBuyPrice = prices[0];
    int profit = Integer.MIN_VALUE;
    for (int i = 1; i < prices.length; i++) {
      int diff = prices[i] - minBuyPrice;
      if (diff > profit) profit = diff;
      if (prices[i] < minBuyPrice) minBuyPrice = prices[i];
    }
    return profit;
  }

  public static void main(String[] args) {
    int[] input = new int[]{10, 7, 5, 8, 11, 9};
    System.out.println(getMaxProfit(input));
  }
}
