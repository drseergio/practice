import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

public class SherlockAnagrams {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int T = scanner.nextInt();
    HashMap<String, Integer> anagrams = new HashMap<>();

    for (int i = 0; i < T; i++) {
      String S = scanner.next();
      int N = S.length();
      int pairCount = 0;

      for (int length = 1; length <= N-1; length++) {
        for (int start = 0; start <= (N - length); start++) {
          char[] stringChars = S.substring(start, start+length).toCharArray();
          Arrays.sort(stringChars);
          String sortedString = String.valueOf(stringChars);

          if (anagrams.containsKey(sortedString)) {
            int dupCount = anagrams.get(sortedString);
            if (dupCount == 1) {
              pairCount++;
            } else {
              pairCount += dupCount;
            }
            anagrams.put(sortedString, dupCount + 1);
          } else {
            anagrams.put(sortedString, 1);
          }
        }
        anagrams.clear();
      }
      System.out.println(pairCount);
    }
    scanner.close();
  }
}
