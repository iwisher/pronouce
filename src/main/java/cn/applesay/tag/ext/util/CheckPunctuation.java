package cn.applesay.tag.ext.util;

public class CheckPunctuation {
  static String punctuation = ".,，!！?？:：;；。、‘’“”[]{}()（）[]{【】「」<>`~@#$%^&*+=-_－|\"\\/";

  public static boolean isPunctation(String ch) {
    if (punctuation.contains(ch)) {
      return true;
    }

    return false;
  }
}
