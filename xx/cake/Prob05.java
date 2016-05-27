import java.util.Arrays;

public class Prob05 {
  public static int count(int amount, int[] coins) {
    return count(amount, 0, coins);
  }

  public static int count(int remaining, int d, int[] coins) {
    if (remaining == 0) return 1;
    if (d == coins.length) return 0;

    System.out.println("checking ways to make " + remaining + " with " + Arrays.toString(Arrays.copyOfRange(coins, d, coins.length)));

    int sum = 0, tot;
    for (int q = 0, total = q * coins[d]; total <= remaining; q++, total = q * coins[d])
      sum += count(remaining - total, d+1, coins);
    return sum;
  }

  public static int changePossibilitiesBottomUp(int amount, int[] denominations) {
    int[] waysOfDoingNCents = new int[amount+1]; // array of zeros from 0..amount
    waysOfDoingNCents[0] = 1;

    for (int coin : denominations) {
        for (int higherAmount = coin; higherAmount < amount + 1; higherAmount++) {
            int higherAmountRemainder = higherAmount - coin;
            waysOfDoingNCents[higherAmount] += waysOfDoingNCents[higherAmountRemainder];
        }
    }

    return waysOfDoingNCents[amount];
  }

  public static void main(String[] args) {
    int amount = 5;
    int[] coins = new int[]{5,3};
    System.out.println(count(amount, coins));
    System.out.println(changePossibilitiesBottomUp(amount, coins));
  }
}
