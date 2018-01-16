package cn.applesay.tag.ext.pingying;


import cn.applesay.tag.ext.util.DeleteEndingPunctuation;
import cn.applesay.tag.ext.util.LongestCommonSubsequence;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

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

        /*String parameterFile = currentDirectory + prop.getProperty("parameterFile");
        String templateFile = currentDirectory + prop.getProperty("templateFile");
        String homophonyFile = currentDirectory + prop.getProperty("homophonyFile");
        boolean isCharacterLevel = Boolean.parseBoolean(prop.getProperty("isCharacterLevel"));*/
        String similarityFile = currentDirectory + prop.getProperty("similarityFile");
        this.pinyinWeight = Double.parseDouble(prop.getProperty("pinyinWeight"));
        //this.pinyinTransfomer = new PinyinTransfomer(HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.LOWERCASE);
        this.eval = new Evaluator(similarityFile);
    }

    public double similarityScore(String speech, String gold)
        throws BadHanyuPinyinOutputFormatCombination
    {
        double similarity = 0.0D;
        double pinyinSimilarity = 0.0D;


        //speech = DeleteEndingPunctuation.process(speech);
        //gold = DeleteEndingPunctuation.process(gold);


        //List<String[]> pinyinSpeech =  this.pinyinTransfomer.getTermPinYin(speech);
        //List<String[]> pinyinGold = this.pinyinTransfomer.getTermPinYin(gold);




        //if ((pinyinSpeech.size() > 0) && (pinyinGold.size() > 0)) {
            //pinyinSimilarity = this.eval.evaluate(pinyinSpeech.get(0), pinyinGold.get(0)).getTotalSyllableScore() / 100.0F;
        //}
        //writingSimilarity = LongestCommonSubsequence.computeSimilarity(speech, gold);
        similarity = this.pinyinWeight * pinyinSimilarity;// + (1.0D - this.pinyinWeight) * writingSimilarity;

        return similarity;
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

