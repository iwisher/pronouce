package cn.applesay.tag.ext.pingying;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class PinyinTransfomerTest {

    @Test
    public void testGetHomophony() throws Exception {
        PinyinTransfomer ptf = new PinyinTransfomer(HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.LOWERCASE);
        String[] pinyin= ptf.getHomophony('不');
        Assert.assertEquals(pinyin.length,2);

        ptf = new PinyinTransfomer(HanyuPinyinToneType.WITH_TONE_NUMBER, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.LOWERCASE);
        pinyin= ptf.getHomophony('不');
        Assert.assertEquals(pinyin.length,3);


    }

    @Test
    public void testGetTermPinYin() throws Exception {
        PinyinTransfomer ptf = new PinyinTransfomer(HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.LOWERCASE);
        List<String[]> pinyin= ptf.getTermPinYin("花式男装不了于");
        Assert.assertEquals(pinyin.size(),7);
        System.out.println(Arrays.toString(pinyin.toArray()));
    }
}