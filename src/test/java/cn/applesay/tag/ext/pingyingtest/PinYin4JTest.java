package cn.applesay.tag.ext.pingyingtest;

/*
import cn.applesay.tag.ext.pingying.AlphabetPronouce;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import net.sourceforge.pinyin4j.*;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
*/

/**
 * Created by rsong on 12/28/17.
 */
public class PinYin4JTest {

    /*static HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();
    static {
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
    }

    public static void singleChar(char charactor)
    {
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(charactor);
        for(int i = 0; i < pinyinArray.length; ++i)
        {
            System.out.println(pinyinArray[i]);
        }
    }

    public static void mixString(String sentence){
        HanyuPinyinOutputFormat format =  new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] pinyin;

    }

    private static String getStringPinYin(String str){
        StringBuilder sb = new StringBuilder();
        String tempPinyin = null;
        for(int i = 0; i < str.length(); ++i)
        {
            //tempPinyin =getCharacterPinYin(str.charAt(i));
            try
            {
                tempPinyin = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i), format)[0];
            }
            catch(BadHanyuPinyinOutputFormatCombination e)
            {
                e.printStackTrace();
            }
            if(tempPinyin == null)
            {
                // 如果str.charAt(i)非汉字，则保持原样
                sb.append(str.charAt(i));
            }
            else
            {
                sb.append(tempPinyin);
            }
        }
        return sb.toString();
    }


    public static void  formatOutput(char charactor){
        String[] pinyinArray = null;

        try
        {
            pinyinArray = PinyinHelper.toHanyuPinyinStringArray(charactor, format);
        }
        catch(BadHanyuPinyinOutputFormatCombination e)
        {
            e.printStackTrace();
        }

        for(int i = 0; i < pinyinArray.length; ++i)
        {
            System.out.println(pinyinArray[i]);
        }
        System.out.println();
    }

    public static void main(String... args) {
        singleChar('单');
        //System.out.print('\n');
        formatOutput('黄');

        System.out.println(AlphabetPronouce.getCharPronunce('a'));

    }
    */

}
