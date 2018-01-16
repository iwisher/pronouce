package cn.applesay.tag.ext.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteEndingPunctuationTest {

    public static final String SENTENCE = "?我爱学习,我写代码. zhongwe句。";

    @Test
    public void testDeleteEndingPunctuation() throws Exception {
        String str = DeleteEndingPunctuation.deleteEndingPunctuation(SENTENCE);
        System.out.println(str);
        Assert.assertEquals(str.length(), SENTENCE.length()-1);
    }

    @Test
    public void testRemovePunctuation() throws Exception {
        String str = DeleteEndingPunctuation.removePunctuation(SENTENCE);
        System.out.println(str);
        Assert.assertEquals(str.length(), SENTENCE.length()-5);
    }

    @Test
    public void testIsPunctuation() throws Exception {
        Assert.assertTrue(DeleteEndingPunctuation.isPunctuation('!'));
    }
}