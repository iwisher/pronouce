package cn.applesay.tag.ext.pingying;

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

  HashMap<String, Integer> similarity = null;

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
            index = tokens[0].indexOf("_");
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

  public Score evaluate(String[] prediction, String[] annotation) {
    String[] predSpeech = (String[])null;
    String[] annoSpeech = (String[])null;
    int[] predTone = (int[])null;
    int[] annoTone = (int[])null;

    String syllable = null;
    int predLength = 0;
    int annoLength = 0;

    /*prediction = prediction.trim();
    String[] tokens = prediction.split("\\s+"); */
    predLength = prediction.length * 2;

    predSpeech = new String[predLength];
    predTone = new int[predLength / 2];

    String[] component = (String[])null;

    for (int i = 0; i < predLength / 2; i++) {
      syllable = prediction[i];
      if (isPinyin(syllable)) {
        syllable = split(syllable);
        component = syllable.split("\\s+");
        predSpeech[(2 * i)] = component[0];
        predSpeech[(2 * i + 1)] = component[1];
        predTone[i] = Integer.parseInt(component[2]);
      }
      else {
        predSpeech[(2 * i)] = syllable;
        predSpeech[(2 * i + 1)] = syllable;
        predTone[i] = 0;
      }
    }

    if (this.isDebug) {
      System.out.print("The prediction: ");
      for (int i = 0; i < predLength; i += 2) {
        if (predSpeech[i].equals("null")) {
          System.out.print(predSpeech[(i + 1)] + predTone[(i / 2)] + "\t");
        } else if (predSpeech[(i + 1)].equals("null")) {
          System.out.print(predSpeech[i] + predTone[(i / 2)] + "\t");
        } else {
          System.out.print(predSpeech[i] + predSpeech[(i + 1)] + predTone[(i / 2)] + "\t");
        }
      }
      System.out.print("\r\n");
    }

    /*annotation = annotation.trim();
    tokens = annotation.split("\\s+");*/
    annoLength = annotation.length * 2;

    annoSpeech = new String[annoLength];
    annoTone = new int[annoLength / 2];

    for (int i = 0; i < annoLength / 2; i++) {
      syllable = annotation[i];
      if (isPinyin(syllable)) {
        syllable = split(syllable);
        component = syllable.split("\\s+");
        annoSpeech[(2 * i)] = component[0];
        annoSpeech[(2 * i + 1)] = component[1];
        annoTone[i] = Integer.parseInt(component[2]);
      }
      else {
        annoSpeech[(2 * i)] = syllable;
        annoSpeech[(2 * i + 1)] = syllable;
        annoTone[i] = 0;
      }
    }

    if (this.isDebug) {
      System.out.print("The annotation: ");
      for (int i = 0; i < annoLength; i += 2) {
        if (annoSpeech[i].equals("null")) {
          System.out.print(annoSpeech[(i + 1)] + annoTone[(i / 2)] + "\t");
        } else if (annoSpeech[(i + 1)].equals("null")) {
          System.out.print(annoSpeech[i] + annoTone[(i / 2)] + "\t");
        } else {
          System.out.print(annoSpeech[i] + annoSpeech[(i + 1)] +
            annoTone[(i / 2)] + "\t");
        }
      }
      System.out.print("\r\n");
    }


    int[][] comp = new int[predLength + 1][annoLength + 1];
    String[][] assi = new String[predLength + 1][annoLength + 1];
    String pair = null;
    int value = 0;
    for (int i = 1; i <= predLength; i++) {
      for (int j = 1; j <= annoLength; j++) {
        if (predSpeech[(i - 1)].equals(annoSpeech[(j - 1)])) {
          value = 10;
        } else {
          pair = predSpeech[(i - 1)] + "_" + annoSpeech[(j - 1)];
          if (this.similarity.containsKey(pair)) {
            value = ((Integer)this.similarity.get(pair)).intValue();
          } else {
            value = 0;
          }
        }

        value += comp[(i - 1)][(j - 1)];
        if ((value > comp[(i - 1)][j]) && (value > comp[i][(j - 1)])) {
          comp[i][j] = value;
          assi[i][j] = "diag";
        }
        else if (comp[(i - 1)][j] >= comp[i][(j - 1)]) {
          comp[i][j] = comp[(i - 1)][j];
          assi[i][j] = "up";
        } else {
          comp[i][j] = comp[i][(j - 1)];
          assi[i][j] = "left";
        }
      }
    }


    if (this.isDebug) {
      System.out.print("\r\nThe result of dynamic programming: \r\n");

      for (int i = 1; i <= predLength; i++) {
        for (int j = 1; j <= annoLength; j++) {
          System.out.print(comp[i][j] + "/" + assi[i][j] + "\t");
        }
        System.out.print("\r\n");
      }
    }

    ArrayList<Integer> matchSet = new ArrayList();
    LCS(assi, matchSet, predLength, annoLength);

    if (this.isDebug) {
      int order = 0;
      System.out.print("The match locations: ");
      Iterator<Integer> it = matchSet.iterator();
      while (it.hasNext()) {
        if (order % 2 == 0) {
          System.out.print("(" + it.next() + ",");
        } else {
          System.out.print(it.next() + ") ");
        }
        order++;
      }
      System.out.print("\r\n");
    }

    if (this.isDebug) {
      System.out.println("\r\nThe matched score: " +
        comp[predLength][annoLength] /
        annoLength * 10.0D);
    }

    int length = annoLength / 2;
    String[] pinyin = new String[predLength / 2];
    String[] standard = new String[predLength / 2];
    int[] standardIndex = new int[predLength / 2];
    float[] syllableScore = new float[length];
    float[] consonantScore = new float[length];
    float[] vowelScore = new float[length];
    float totalSyllableScore = 0.0F;
    float totalConsonantScore = 0.0F;
    float totalVowelScore = 0.0F;
    float fluencyScore = 0.0F;
    boolean[] tone = new boolean[length];

    for (int i = 0; i < predLength; i += 2) {
      if (predSpeech[i].equals("null")) {
        pinyin[(i / 2)] = (predSpeech[(i + 1)] + predTone[(i / 2)]);
      } else if (predSpeech[(i + 1)].equals("null")) {
        pinyin[(i / 2)] = (predSpeech[i] + predTone[(i / 2)]);
      } else {
        pinyin[(i / 2)] = (predSpeech[i] + predSpeech[(i + 1)] + predTone[(i / 2)]);
      }
      standardIndex[(i / 2)] = -1;
    }

    Iterator<Integer> it = matchSet.iterator();
    int order = 0;
    int predIndex = -1;
    int annoIndex = -1;
    int point = 0;


    while (it.hasNext()) {
      if (order % 2 == 0) {
        predIndex = ((Integer)it.next()).intValue();
      } else {
        annoIndex = ((Integer)it.next()).intValue();

        if (annoIndex % 2 == 0) {
          if (predSpeech[predIndex].equals(annoSpeech[annoIndex])) {
            point = 10;
          } else {
            pair = predSpeech[predIndex] + "_" + annoSpeech[annoIndex];
            if (this.similarity.containsKey(pair)) {
              point = ((Integer)this.similarity.get(pair)).intValue();
            }
            else {
              point = 0;
            }
          }
          consonantScore[(annoIndex / 2)] = (point * 10);
        }
        else
        {
          if (predSpeech[predIndex].equals(annoSpeech[annoIndex])) {
            point = 10;
          } else {
            pair = predSpeech[predIndex] + "_" + annoSpeech[annoIndex];
            if (this.similarity.containsKey(pair)) {
              point = ((Integer)this.similarity.get(pair)).intValue();
            }
            else {
              point = 0;
            }
          }
          vowelScore[(annoIndex / 2)] = (point * 10);
          if (predTone[(predIndex / 2)] == annoTone[(annoIndex / 2)]) {
            tone[(annoIndex / 2)] = true;
          }
          else {
            tone[(annoIndex / 2)] = false;
          }
          if ((annoSpeech[(annoIndex - 1)].equals("null")) || (annoSpeech[annoIndex].equals("null"))) {
            standard[(predIndex / 2)] = (annoSpeech[(annoIndex - 1)] + "-" + annoSpeech[annoIndex] + annoTone[(annoIndex / 2)]);
          }
          else {
            standard[(predIndex / 2)] = (annoSpeech[(annoIndex - 1)] + annoSpeech[annoIndex] + annoTone[(annoIndex / 2)]);
          }
          standardIndex[(predIndex / 2)] = (annoIndex / 2);
        }
      }
      order++;
    }

    if (this.isDebug)
    {
      System.out.print("The matched tones: ");
      for (int i = 0; i < length; i++) {
        System.out.print(tone[i] + " ");
      }
      System.out.print("\r\n");
    }

    int matchCount = 0;
    for (int i = 0; i < length; i++) {
      if (tone[i] != false) {
        syllableScore[i] = ((1.0F - this.weightVowel) * consonantScore[i] + this.weightVowel * vowelScore[i]);
      }
      else {
        syllableScore[i] = (((1.0F - this.weightVowel) * consonantScore[i] + this.weightVowel * vowelScore[i]) * this.discount);
      }
      if (syllableScore[i] > 0.0F) {
        matchCount++;
      }
      totalConsonantScore += consonantScore[i];
      totalVowelScore += vowelScore[i];
      totalSyllableScore += syllableScore[i];
    }
    if (this.isDebug) {
      System.out.println("The matched count: " + matchCount + "\r\n");
    }

    if (predLength > annoLength) {
      this.averageLength = (predLength / 2.0F);
    }
    else {
      this.averageLength = (annoLength / 2.0F);
    }

    totalConsonantScore /= this.averageLength;
    totalVowelScore /= this.averageLength;
    totalSyllableScore /= this.averageLength;

    Score score = new Score(length, predLength / 2, pinyin, standard, standardIndex, syllableScore, consonantScore,
      vowelScore, totalSyllableScore, totalConsonantScore,
      totalVowelScore, fluencyScore);

    return score;
  }

  public String normalize(String syllable)
  {
    String[] tokens = syllable.split("\\s+");
    String consonant = tokens[0];
    String vowel = tokens[1];
    int index = consonant.indexOf("-");
    consonant = consonant.substring(0, index);


    if ((consonant.equals("zhi")) || (consonant.equals("chi")) ||
      (consonant.equals("shi")) || (consonant.equals("zi")) ||
      (consonant.equals("ci")) || (consonant.equals("si")) ||
      (consonant.equals("ri")) || (consonant.equals("null")) ||
      (consonant.equals("yi")) || (consonant.equals("wu")) ||
      (consonant.equals("yv")) || (consonant.equals("ye")) ||
      (consonant.equals("yve")) || (consonant.equals("yvan")) ||
      (consonant.equals("yin")) || (consonant.equals("yvn")) ||
      (consonant.equals("ying"))) {
      consonant = "";
    }
    vowel = vowel.replaceAll("A+", "a");
    vowel = vowel.replaceAll("E+", "e");
    vowel = vowel.replaceAll("I+", "i");
    vowel = vowel.replaceAll("O+", "o");

    return split(consonant + vowel);
  }

  public String split(String syllable)
  {
    String speech = syllable.substring(0, syllable.length() - 1);
    String tone = syllable.substring(syllable.length() - 1);

    if (tone.equals("0")) {
      tone = "1";
    }

    if ((!tone.equals("1")) && (!tone.equals("2")) && (!tone.equals("3")) && (!tone.equals("4"))) {
      System.out.println("Check the tone of syllable: " + syllable);
      System.exit(0);
    }


    if ((speech.equals("zhi")) || (speech.equals("chi")) ||
      (speech.equals("shi")) || (speech.equals("zi")) ||
      (speech.equals("ci")) || (speech.equals("si")) ||
      (speech.equals("ri"))) {
      return speech.substring(0, speech.length() - 1) + " " + "null" + " " + tone;
    }

    if ((speech.equals("yi")) || (speech.equals("wu")) || (speech.equals("yv")) ||
      (speech.equals("yve")) || (speech.equals("yvan")) ||
      (speech.equals("yin")) || (speech.equals("yvn")) ||
      (speech.equals("ying"))) {
      return "null " + speech.substring(1) + " " + tone;
    }

    if (speech.equals("yan")) {
      return "null " + speech + " " + tone;
    }

    if (speech.equals("ye")) {
      return "null ie " + tone;
    }

    if ((speech.startsWith("zh")) || (speech.startsWith("ch")) || (speech.startsWith("sh"))) {
      return speech.substring(0, 2) + " " + speech.substring(2) + " " + tone;
    }

    if ((speech.startsWith("b")) || (speech.startsWith("p")) ||
      (speech.startsWith("m")) || (speech.startsWith("f")) ||
      (speech.startsWith("d")) || (speech.startsWith("t")) ||
      (speech.startsWith("n")) || (speech.startsWith("l")) ||
      (speech.startsWith("g")) || (speech.startsWith("k")) ||
      (speech.startsWith("h")) || (speech.startsWith("j")) ||
      (speech.startsWith("q")) || (speech.startsWith("x")) ||
      (speech.startsWith("z")) || (speech.startsWith("c")) ||
      (speech.startsWith("s")) || (speech.startsWith("r")) ||
      (speech.startsWith("y")) || (speech.startsWith("w"))) {
      return
        speech.substring(0, 1) + " " + speech.substring(1) + " " + tone;
    }
    return "null " + speech + " " + tone;
  }

  private void LCS(String[][] assi, ArrayList<Integer> matchSet, int i, int j) {
    if ((i == 0) || (j == 0)) {
      return;
    }
    if (assi[i][j].equals("diag")) {
      LCS(assi, matchSet, i - 1, j - 1);
      matchSet.add(Integer.valueOf(i - 1));
      matchSet.add(Integer.valueOf(j - 1));
    }
    else if (assi[i][j].equals("up")) {
      LCS(assi, matchSet, i - 1, j);
    } else {
      LCS(assi, matchSet, i, j - 1);
    }
  }


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