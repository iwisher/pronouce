package cn.applesay.tag.ext.pingying;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;


import java.util.List;

import static org.testng.Assert.*;

public class EvaluatorTest {

    @org.testng.annotations.Test
    public void testSimilarity() throws Exception {
        System.out.println(HanLP.segment("你好，我要蓝色羽绒服！"));
        System.out.println(NLPTokenizer.segment("你好，我要蓝色的羽绒服."));
        Segment segment = new CRFSegment();
        segment.enablePartOfSpeechTagging(true);

        List<Term> termList = segment.seg("你好，我要蓝色滑雪衫.");
        System.out.println(termList);
        for (Term term : termList)
        {
            if (term.nature == null)
            {
                System.out.println("识别到新词：" + term.word);
            }
        }
    }

    @org.testng.annotations.Test
    public void testEvaluate() throws Exception {
        String[] testCase = new String[]{
                "一桶冰水当头倒下，微软的比尔盖茨、Facebook的扎克伯格跟桑德博格、亚马逊的贝索斯、苹果的库克全都不惜湿身入镜，这些硅谷的科技人，飞蛾扑火似地牺牲演出，其实全为了慈善。",
                "世界上最长的姓名是简森·乔伊·亚历山大·比基·卡利斯勒·达夫·埃利奥特·福克斯·伊维鲁莫·马尔尼·梅尔斯·帕特森·汤普森·华莱士·普雷斯顿。",
        };
        Segment segment = HanLP.newSegment().enableTranslatedNameRecognize(true);
        for (String sentence : testCase)
        {
            List<Term> termList = segment.seg(sentence);
            System.out.println(termList);
        }

        String content = "你好，我要蓝色羽绒服";
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        System.out.println(keywordList);

        System.out.println("\n\n\r\r");

    }

    @org.testng.annotations.Test
    public void testNormalize() throws Exception {
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        String address = "上海中冶祥腾城市广场店,上海市嘉定区南翔镇真南路4368弄1号中冶祥腾城市广场1F,江苏省太仓市城厢镇上海东路188号万达广场1F";
        System.out.println(segment.seg(address));
        System.out.println(segment.seg("阿狸我最近的门店在哪有哪些"));
    }

    @org.testng.annotations.Test
    public void testSplit() throws Exception {
    }

    @org.testng.annotations.Test
    public void testIsTone() throws Exception {
    }
}