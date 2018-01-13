package cn.applesay.tag.ext.util;

public class DeleteEndingPunctuation {
  public static String process(String sentence) {
    if (isPunctuation(sentence.substring(sentence.length() - 1))) {
      return sentence.substring(0, sentence.length() - 1);
    }

    return sentence;
  }

  public static boolean isPunctuation(String str)
  {
    if ((str.equals("。")) || (str.equals("！")) || (str.equals("？")) || (str.equals("，")) || (str.equals("：")) || (str.equals("、")) ||
      (str.equals(".")) || (str.equals("!")) || (str.equals("?")) || (str.equals(",")) || (str.equals(":"))) {
      return true;
    }

    return false;
  }
}
