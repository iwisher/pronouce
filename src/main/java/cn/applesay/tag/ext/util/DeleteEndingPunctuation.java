package cn.applesay.tag.ext.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeleteEndingPunctuation {
  public static String deleteEndingPunctuation(String sentence) {
    if (isPunctuation(sentence.toCharArray()[sentence.length() - 1])) {
      return sentence.substring(0, sentence.length() - 1);
    }

    return sentence;
  }

  public static String removePunctuation(String sentence) {
    StringBuilder sb = new StringBuilder();
    for (char c : sentence.toCharArray())
    {
      if(!isPunctuation(c))
        sb.append(c);
    }

    return sb.toString();
  }

  private static final Character[] punctuations = {'。','！','？','，','：','、','.','!','?',',',':',' '};
  private static final List<Character> punctuationList = Arrays.asList(punctuations);

  public static boolean isPunctuation(char c)
  {
    if (punctuationList.contains(c)) {
      return true;
    }

    return false;
  }
}
