package cn.applesay.tag.ext.pingying;

import com.hankcs.hanlp.dictionary.py.Pinyin;
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

        List<Pinyin> pinyin= PinyinTransfomer.getHanLPPinYin("不");
        Assert.assertEquals(pinyin.size(),1);
        System.out.printf("Pingyin result is: %s \n\n", pinyin);
    }

    @Test
    public void testGetTermPinYin() throws Exception {
        //PinyinTransfomer ptf = new PinyinTransfomer(HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.LOWERCASE);
        List<Pinyin> pinyin= PinyinTransfomer.getHanLPPinYin("花式男装不了于");
        Assert.assertEquals(pinyin.size(),7);
        System.out.printf("Pingyin result is: %s \n\n", pinyin);
    }

    @Test
    public void testGetHanLPPinYin() throws  Exception {

        List<Pinyin>  pinyin=  PinyinTransfomer.getHanLPPinYin("卒阿狸d我最近的门店在哪有D哪z些");
        System.out.printf("Pingyin result is: %s \n\n", pinyin);
        Assert.assertEquals(pinyin.get(pinyin.size()-2),PinyinTransfomer.getHanLPPinYin("z").get(0));
    }

    @Test
    public void testGetHanLPPinYinAZ() throws  Exception {

        List<Pinyin>  pinyin=  PinyinTransfomer.getHanLPPinYin("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.printf("Pingyin result is: %s \n\n", pinyin);

        int nontCount = 0;
        for (Pinyin p:pinyin)
        {
            if (p == Pinyin.none5)
                nontCount++;
        }
        Assert.assertEquals(pinyin.size(),34);
        Assert.assertEquals(nontCount,0);

        pinyin=  PinyinTransfomer.getHanLPPinYin("w");
        System.out.printf("Pingyin result is: %s \n\n", pinyin);
        Assert.assertEquals(pinyin.size(),3);

        pinyin=  PinyinTransfomer.getHanLPPinYin("l");
        System.out.printf("Pingyin result is: %s \n\n", pinyin);
        Assert.assertEquals(pinyin.size(),2);



    }
}