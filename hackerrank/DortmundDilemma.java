import java.util.Scanner;

/**
 * Dortmund Dilemma:
 * https://www.hackerrank.com/challenges/dortmund-dilemma
 *
 * Based on editorial (which has 2 typos: k^n instead of n^k and
 * sum of P[n][j] instead of G[n][j]):
 * https://hr-filepicker.s3.amazonaws.com/101jun14/3124-Dortmund-Dilemma.pdf
 *
 * My original solution was too slow because it did not use caching. At the end I
 * rewrote my code by reading another user's code:
 * https://github.com/linjiyuan90/OJ_Code/blob/master/hackerrank/DortmundDilemma.cc
 */
public class DortmundDilemma {
  public static final int MAX_N = 100000;
  public static final int MAX_K = 26;
  public static final long MOD = 1000000009;

  /** binomial coefficient */
  static long[][] C;
  /**
   * Total number of all possible strings that contain NO prefix-suffix
   * matches and NOT NECESSARILY all K letters (strings may be made with
   * fewer unique letters) for every N, K combinations.
   */
  static long[][] F;
  /**
   * Total number of all possible strings that contain at least one
   * prefix-suffix match by subtracting from k^N (all possible K letter combinations
   * of length N) all possible strings that contain NO prefix-suffix matches
   * (strings may include NOT all K letters).
   */
  static long[][] G;
  static long[][] P;

  public static void main(String[] args) {
    C = new long[MAX_K+1][MAX_K+1];
    for (int i = 0; i <= MAX_K; i++) {
      C[i][0] = C[i][i] = 1;
      for (int j = 1; j < i; j++) {
        C[i][j] = (C[i-1][j] + C[i-1][j-1]) % MOD;
      }
    }

    F = new long[MAX_N+1][MAX_K+1];
    G = new long[MAX_N+1][MAX_K+1];
    P = new long[MAX_N+1][MAX_K+1];
    for (int k = 1; k <= MAX_K; k++) {
      F[1][k] = k;
      long kn = k;

      for (int n = 2; n <= MAX_N; n++) {
        kn = kn * k % MOD;
        if (n % 2 == 1) {
          F[n][k] = F[n-1][k] * k % MOD;
        } else {
          F[n][k] = (F[n-1][k] * k % MOD - F[n/2][k] + MOD) % MOD;
        }
        G[n][k] = (kn - F[n][k] + MOD) % MOD;
        P[n][k] = G[n][k];
        for (int j = 1; j < k; j++) {
          P[n][k] = (P[n][k] - P[n][j] * C[k][j] % MOD + MOD) % MOD;
        }
      }
    }

    Scanner scanner = new Scanner(System.in);
    for (int t = scanner.nextInt(); t > 0; t--) {
      int N = scanner.nextInt(), K = scanner.nextInt();
      System.out.println(P[N][K] * C[26][K] % MOD);
    }
    scanner.close();
  }
}
