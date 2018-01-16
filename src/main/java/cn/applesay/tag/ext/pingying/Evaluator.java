package cn.applesay.tag.ext.pingying;

import cn.applesay.tag.ext.util.LongestCommonSubstring;
import com.hankcs.hanlp.dictionary.py.Pinyin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Evaluator
{
  private float discount = 0.9F;
  private float weightVowel = 0.5F;

  private enum SyllablePart {tone, vowl, consotant, both};

  private LongestCommonSubstring lcs;

  public Evaluator(String similarityFile)
  {
    //this.similarity = new HashMap();
    try {
      Properties similarity = new Properties();
      InputStream fileInputStream = PinyinTransfomer.class.getClassLoader().getResourceAsStream(similarityFile);
      similarity.load(fileInputStream);
      HashMap<String, Integer> similarityMap = new HashMap<>();
      for (String key: similarity.stringPropertyNames())
      {
        try{
          similarityMap.put(key, Integer.parseInt(similarity.getProperty(key)));
        }catch (NumberFormatException nfe)
        {
          nfe.printStackTrace(System.err);
        }
      }
      this.lcs = new LongestCommonSubstring(similarityMap);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public double evaluateSyllable(List<Pinyin> prediction, List<Pinyin> annotation) {

    return this.evaluateSyllable(prediction,annotation,true);
  }

  /**
   *
   * @param prediction chinese sentence input by STT
   * @param annotation tags
   * @return
   */
  public double evaluateSyllable(List<Pinyin> prediction, List<Pinyin> annotation, boolean removeTone) {


    int length = Math.max(prediction.size(),annotation.size());
    return this.lcs.computeSyllable(prediction,annotation,removeTone)/((double) length * LongestCommonSubstring.SCORE_UNIT * 2);
  }

  /**
   *
   * @param prediction
   * @param annotation
   * @return
   */
  public double evaluateConsonant(List<Pinyin> prediction, List<Pinyin> annotation) {
    ArrayList<String> predictionConsonant = getPinyinString(prediction, SyllablePart.consotant);
    ArrayList<String> annotationConsonant = getPinyinString(annotation, SyllablePart.consotant);
    int length = Math.max(prediction.size(),annotation.size());
    return this.lcs.compute(predictionConsonant,annotationConsonant)/((double) length * LongestCommonSubstring.SCORE_UNIT);
  }

  /**
   *
   * @param prediction
   * @param annotation
   * @return
   */
  public double evaluateVowel(List<Pinyin> prediction, List<Pinyin> annotation) {
    ArrayList<String> predictionVowel = getPinyinString(prediction, SyllablePart.vowl);
    ArrayList<String> annotationVowel = getPinyinString(annotation, SyllablePart.vowl);
    int length = Math.max(prediction.size(),annotation.size());
    return this.lcs.compute(predictionVowel,annotationVowel)/((double) length * LongestCommonSubstring.SCORE_UNIT);
  }

  /**
   *
   * @param prediction
   * @param annotation
   * @return
   */
  public double evaluateTone(List<Pinyin> prediction, List<Pinyin> annotation) {
      ArrayList<String> predictionTone = getPinyinString(prediction, SyllablePart.tone);
      ArrayList<String> annotationTone = getPinyinString(annotation, SyllablePart.tone);
      int length = Math.max(prediction.size(),annotation.size());
      return this.lcs.compute(predictionTone,annotationTone)/((double) length * LongestCommonSubstring.SCORE_UNIT);
  }

  private ArrayList<String> getPinyinString(List<Pinyin> prediction,SyllablePart part) {
    ArrayList<String> toneList = new ArrayList<String>();
    for(Pinyin p:prediction)
    {
      switch (part) {
            case tone:
              toneList.add(String.valueOf(p.getTone()));
              break;
            case consotant:
              toneList.add(String.valueOf(p.getShengmu()));
              break;
            case vowl:
              toneList.add(String.valueOf(p.getYunmu()));
              break;
            case both:
              toneList.add(String.valueOf(p.getShengmu()));
              toneList.add(String.valueOf(p.getYunmu()));
              break;
          }
    }
    return toneList;
  }

}