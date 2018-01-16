package cn.applesay.tag.ext.pingying;

import cn.applesay.tag.ext.util.LongestCommonSubstring;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Evaluator
{
  private float discount = 0.9F;
  private float weightVowel = 0.5F;
  private float averageLength = 1.0F;
  boolean isDebug = false;

  private HashMap<String, Integer> similarity = null;

  public Evaluator(String similarityFile)
  {
    this.similarity = new HashMap();
    try {
      InputStream fis = Evaluator.class.getClassLoader().getResourceAsStream(similarityFile);
      java.io.InputStreamReader isr = new java.io.InputStreamReader(fis, "UTF-8");
      BufferedReader br = new BufferedReader(isr);

      String line = null;
      String[] tokens = (String[])null;
      int index = -1;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if ((!line.equals("")) && (!line.equals(" "))) {
          tokens = line.split("\\s+");
          if (tokens.length != 2) {
            System.out.println("Check the line of similarity file: " + line);
            System.exit(0);
          } else {
            this.similarity.put(tokens[0], Integer.valueOf(Integer.parseInt(tokens[1])));
            index = tokens[0].indexOf(LongestCommonSubstring.CONCATENATED_STRING);
            if (index < 0) {
              System.out.println("Check the line of similarity file: " + line);
            }
            this.similarity.put(tokens[0].substring(index + 1) + "_" + tokens[0].substring(0, index), Integer.valueOf(Integer.parseInt(tokens[1])));
          }
        }
      }

      br.close();
      isr.close();
      fis.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
  public double similarity(String py1, String py2)
  {
    double score = 0.0D;
    String pair = null;
    String[] component1 = split(py1).split("\\s+");
    String[] component2 = split(py2).split("\\s+");



    if (component1[0].equals(component2[0])) {
      score += 10.0D;
    }
    else {
      pair = component1[0] + "_" + component2[0];
      if (this.similarity.containsKey(pair)) {
        score += ((Integer)this.similarity.get(pair)).intValue();
      }
    }
    if (component1[1].equals(component2[1])) {
      score += 10.0D;
    }
    else {
      pair = component1[1] + "_" + component2[1];
      if (this.similarity.containsKey(pair)) {
        score += ((Integer)this.similarity.get(pair)).intValue();
      }
    }
    score /= 2.0D;
    if (!component1[2].equals(component2[2])) {
      score *= this.discount;
    }
    return score;
  }
  */

  /**
   *
   * @param prediction chinese sentence input by STT
   * @param annotation tags
   * @return
   */





  private boolean isPinyin(String str)
  {
    if (str != null) {
      int length = str.length();
      if ((length > 1) &&
        (isTone(str.substring(length - 1)))) {
        return true;
      }
    }

    return false;
  }

  public boolean isTone(String tone) {
    if ((tone.equals("0")) || (tone.equals("1")) || (tone.equals("2")) || (tone.equals("3")) || (tone.equals("4"))) {
      return true;
    }

    return false;
  }
}