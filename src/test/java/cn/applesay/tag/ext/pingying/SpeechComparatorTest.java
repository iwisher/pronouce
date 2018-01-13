package cn.applesay.tag.ext.pingying;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SpeechComparatorTest {

    String currentDirectory ="pinying/";
    String speechSimilarityEstimatorFile="SpeechSimilartyEstimator.properties";


    @Test
    public void testSimilarityScore() throws Exception {
        SpeechComparator speechComparator = new SpeechComparator(currentDirectory,speechSimilarityEstimatorFile);
        speechComparator.similarityScore("男死开衫","男式开衫");
    }

    @Test
    public void testSimilarityScore1() throws Exception {
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