package cn.applesay.tag.ext.util;

import cn.applesay.tag.ext.pingying.PinyinTransfomer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class LongestCommonSubstringTest {

    @Test
    public void testCompute() throws Exception {
        LongestCommonSubstring lct = new LongestCommonSubstring(null);
        String inputA="www.hankcs.com";
        String inputB="hanks";

        /*String[] resultA = new String[inputA.length()];
        //String[] resultA = new String[inputA.length()];
        String[] resultB = new String[inputB.length()];

        System.arraycopy(inputA,0,resultA,0,inputA.length());
        System.arraycopy(inputB,0,resultB,0,inputB.length());*/

        lct.compute(Arrays.asList(inputA.split("")),Arrays.asList(inputB.split("")));

    }

    @Test
    public void testComputePinyin() throws Exception {
        LongestCommonSubstring lct = new LongestCommonSubstring(null);
        String inputA="滑雪衫";
        String inputB= "化雪三";

        /*String[] resultA = new String[inputA.length()];
        //String[] resultA = new String[inputA.length()];
        String[] resultB = new String[inputB.length()];

        System.arraycopy(inputA,0,resultA,0,inputA.length());
        System.arraycopy(inputB,0,resultB,0,inputB.length());*/

        double score1 = lct.computeSyllable(PinyinTransfomer.getHanLPPinYin(inputA),PinyinTransfomer.getHanLPPinYin(inputB),true);
        double score2 = lct.computeSyllable(PinyinTransfomer.getHanLPPinYin(inputA),PinyinTransfomer.getHanLPPinYin(inputB),false);

    }
}