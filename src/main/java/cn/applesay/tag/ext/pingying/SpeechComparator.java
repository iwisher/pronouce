package cn.applesay.tag.ext.pingying;


import com.hankcs.hanlp.dictionary.py.Pinyin;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class SpeechComparator
{
    private double pinyinWeight = 0.6D;
    //private PinyinTransfomer pinyinTransfomer = null;
    private Evaluator eval = null;

    public SpeechComparator(String currentDirectory, String speechSimilarityEstimatorFile) {
        Properties prop = new Properties();
        try {
            InputStream fileInputStream = SpeechComparator.class.getClassLoader().getResourceAsStream(currentDirectory+speechSimilarityEstimatorFile);
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String similarityFile = currentDirectory + prop.getProperty("similarityFile");
        this.pinyinWeight = Double.parseDouble(prop.getProperty("pinyinWeight"));

        String alphabetPronunceFile = currentDirectory + prop.getProperty("alphabetPronunce");

        this.eval = new Evaluator(similarityFile);
    }

    public double similarityScore(String speech, String tags)
    {
        List<Pinyin> pinyinSpeech =  PinyinTransfomer.getHanLPPinYin(speech);
        List<Pinyin> tagsSpeech =  PinyinTransfomer.getHanLPPinYin(tags);
        double pinyinSimilarity = this.eval.evaluateSyllable(pinyinSpeech,tagsSpeech);

        return pinyinSimilarity;
    }

    /*public double similarityScore(String speech, String gold, String pinyinGold)
            throws BadHanyuPinyinOutputFormatCombination
    {
        speech = DeleteEndingPunctuation.process(speech);
        gold = DeleteEndingPunctuation.process(gold);


        List<String[]> pinyinSpeech = this.pinyinTransfomer.getTermPinYin(speech);

        double pinyinSimilarity = this.eval.evaluate(pinyinSpeech.get(0), pinyinGold).getTotalSyllableScore() / 100.0F;
        double writingSimilarity = LongestCommonSubsequence.computeSimilarity(speech, gold);
        double similarity = this.pinyinWeight * pinyinSimilarity + (1.0D - this.pinyinWeight) * writingSimilarity;

        return similarity;
    }*/

    /*public double similarityScore(String speech, String pinyinSpeech, String gold, String pinyinGold)
    {
        double pinyinSimilarity = this.eval.evaluate(pinyinSpeech, pinyinGold).getTotalSyllableScore() / 100.0F;
        double writingSimilarity = LongestCommonSubsequence.computeSimilarity(speech, gold);
        double similarity = this.pinyinWeight * pinyinSimilarity + (1.0D - this.pinyinWeight) * writingSimilarity;

        return similarity;
    }*/

}

