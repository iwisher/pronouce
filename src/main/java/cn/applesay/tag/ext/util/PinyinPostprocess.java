package cn.applesay.tag.ext.util;

public class PinyinPostprocess {
  public static String process(String pinyin, boolean isDeleteNonPinyin) {
    String result = "";
    String[] tokens = pinyin.split("\\s+");
    int length = 0;
    for (int i = 0; i < tokens.length; i++) {
      length = tokens[i].length();
      if ((length > 1) &&
        (isTone(tokens[i].substring(length - 1)))) {
        result = result + tokens[i] + " ";
      }
    }

    return result.trim();
  }

  public static boolean isTone(String tone) {
    if ((tone.equals("0")) || (tone.equals("1")) || (tone.equals("2")) || (tone.equals("3")) || (tone.equals("4"))) {
      return true;
    }

    return false;
  }
}