import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Prob07 {
  public static Set<String> getPermutations(String input) {
    Set<String> set = new HashSet<String>();
    permute(new char[input.length()], 0, set, input.toCharArray(), new boolean[input.length()]);
    return set;
  }

  public static void permute(char[] prefix, int i, Set<String> set, char[] input, boolean[] used) {
    if (i == prefix.length) {
      set.add(new String(prefix));
      return;
    }

    char[] newPrefix = new char[prefix.length];
    for (int j = 0; j <= i; j++) newPrefix[j] = prefix[j];

    for (int c = 0; c < input.length; c++) {
      if (used[c]) continue;
      newPrefix[i] = input[c];
      used[c] = true;
      permute(newPrefix, i+1, set, input, used);
      used[c] = false;
    }
  }

  public static void main(String[] args) {
    String input = "cat";
    System.out.println(Arrays.toString(getPermutations("cat").toArray()));
  }
}
