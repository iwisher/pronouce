package cn.applesay.tag.ext.pingying;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SpeechComparatorTest {

    String currentDirectory ="pinying/";
    String speechSimilarityEstimatorFile="SpeechSimilartyEstimator.properties";
    SpeechComparator speechComparator = new SpeechComparator(currentDirectory,speechSimilarityEstimatorFile);

    @Test
    public void testSimilarityScore() throws Exception {

        Double score = speechComparator.similarityScore("男死开衫","男式率伞");
        System.out.println("Socre is "+ score);
        score = speechComparator.similarityScore("男死开衫","男式凯伞");
        System.out.println("Socre is "+ score);


    }

    @Test
    public void testSimilarityScore1() throws Exception {
        double score = speechComparator.similarityScore("第一节哎","DJI");
        System.out.println("Socre is "+ score);

        score = speechComparator.similarityScore("第节哎","DJI");
        System.out.println("Socre is "+ score);
    }

    @Test
    public void testSimilarityScore2() throws Exception {
    }

    @Test
    public void testGetPinyinTransfomer() throws Exception {
    }

    @Test
    public void testSetPinyinTransfomer() throws Exception {
    }
}