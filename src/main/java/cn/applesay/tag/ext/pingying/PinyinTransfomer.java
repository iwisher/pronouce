package cn.applesay.tag.ext.pingying;



import cn.applesay.tag.ext.util.DeleteEndingPunctuation;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.dictionary.py.PinyinUtil;
import com.hankcs.hanlp.dictionary.py.String2PinyinConverter;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PinyinTransfomer
{
    /**
     * Initial English Char to Pinyin
     */
    private static Properties prop = new Properties();
    static
    {
        final String currentDirectory ="pinying/";
        final String speechSimilarityEstimatorFile="alphabetPronunce.properties";
        try {
            InputStream fileInputStream = SpeechComparator.class.getClassLoader().getResourceAsStream(currentDirectory+speechSimilarityEstimatorFile);
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pinyin> getHanLPPinYin(String str)
    {
        str = DeleteEndingPunctuation.removePunctuation(str);
        List<Pinyin> pinyinList  = HanLP.convertToPinyinList(str);
        List<Pinyin> translatedPinyinList = new ArrayList<Pinyin>();

        int index = -1;
        for(Pinyin p: pinyinList)
        {
            ++index;

            if(p == Pinyin.none5)
            {

                /*
                @Todo 如果str.charAt(i)非汉字，从配置文件中读取非汉字之母发音
                 */
                String value = prop.getProperty(String.valueOf(str.charAt(index)).toLowerCase());
                if (value != null)
                {
                    String[] syllables = value.split("\\s+");
                    for(String syllable : syllables)
                        translatedPinyinList.add(String2PinyinConverter.convertSingle(syllable));
                }
            }
            else
            {
                translatedPinyinList.add(p);
            }
        }
        return translatedPinyinList;
    }
}
