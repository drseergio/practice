
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrettyPrinting {
  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  // @include
  public static int minimumMessiness(List<String> words, int lineLength) {
    // minimumMessiness[i] is the minimum messiness when placing words[0 : i].
    int[] minimumMessiness = new int[words.size()];
    Arrays.fill(minimumMessiness, Integer.MAX_VALUE);
    int numRemainingBlanks = lineLength - words.get(0).length();
    minimumMessiness[0] = numRemainingBlanks * numRemainingBlanks;
    for (int i = 1; i < words.size(); ++i) {
      System.out.println("\n\ni: " + i);
      numRemainingBlanks = lineLength - words.get(i).length();
      System.out.println("nrb: " + numRemainingBlanks);
      minimumMessiness[i]
          = minimumMessiness[i - 1] + numRemainingBlanks * numRemainingBlanks;
      System.out.println("mm["+i+"]: " + minimumMessiness[i]);
      // Try adding words.get(i - 1), words.get(i - 2), ...
      for (int j = i - 1; j >= 0; --j) {
        System.out.println("j: " + j);
        numRemainingBlanks -= (words.get(j).length() + 1);
        System.out.println("nrb: " + numRemainingBlanks);
        if (numRemainingBlanks < 0) {
          // Not enough space to add more words.
          break;
        }
        int firstJMessiness = j - 1 < 0 ? 0 : minimumMessiness[j - 1];
        System.out.println("firstJ: " + firstJMessiness);
        int currentLineMessiness = numRemainingBlanks * numRemainingBlanks;
        System.out.println("currentlinemessiness: " + currentLineMessiness);
        minimumMessiness[i] = Math.min(minimumMessiness[i],
                                       firstJMessiness + currentLineMessiness);
        System.out.println("mm["+i+"]: " + minimumMessiness[i]);
      }
    }
    return minimumMessiness[words.size() - 1];
  }
  // @exclude

  private static void smallTest() {
    assert(minimumMessiness(
               Arrays.asList("a", "b", "c", "d"), 5)
           == 5);
  }

  public static void main(String[] args) {
    System.out.println(minimumMessiness(Arrays.asList("a", "b", "c", "d"), 5));
  }
}
