package cn.applesay.tag.ext.util;

public class LongestCommonSubsequence {
  public static double computeSimilarity(String x, String y) {
    x = x.trim();
    x = x.replaceAll("\\s+", "");
    char[] xchar = x.toCharArray();

    y = y.trim();
    y = y.replaceAll("\\s+", "");
    char[] ychar = y.toCharArray();

    int m = x.length();
    int n = y.length();

    int[][] c = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (xchar[(i - 1)] == ychar[(j - 1)]) {
          c[i][j] = (c[(i - 1)][(j - 1)] + 1);
        }
        else if (c[(i - 1)][j] > c[i][(j - 1)]) {
          c[i][j] = c[(i - 1)][j];
        }
        else {
          c[i][j] = c[i][(j - 1)];
        }
      }
    }

    if (m > n) {
      return c[m][n] / m;
    }

    return c[m][n] / n;
  }
}
