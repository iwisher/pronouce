/*
  繁体 简体 中文切换
 */

package cn.applesay.tag.ext.util;



public class SBCConversion
{
  static final char SBC_CHAR_START = '！';
  static final char SBC_CHAR_END = '～';
  static final int CONVERT_STEP = 65248;
  static final char SBC_SPACE = '　';
  static final char DBC_SPACE = ' ';

  public static String SBCToDBC(String str) {
    if (str == null) {
      return str;
    }
    StringBuilder buf = new StringBuilder(str.length());
    char[] sent = str.toCharArray();
    for (int i = 0; i < str.length(); i++) {
      if ((sent[i] >= 65281) && (sent[i] <= 65374)) {
        buf.append((char)(sent[i] - 65248));
      } else if (sent[i] == '　') {
        buf.append(' ');
      } else {
        buf.append(sent[i]);
      }
    }
    return buf.toString();
  }
}
