package cn.applesay.tag.ext.pingying;



import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.*;

public class PinyinTransfomer
{


    private HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();

    /*
        3. Several output format
            3.1. Uppercase or lowercase
            3.2. v or u: or unicode ü
            3.3. unicode output with tone numbers, with tone marks or without tone
     */
    public PinyinTransfomer(HanyuPinyinToneType toneType, HanyuPinyinVCharType vCharType, HanyuPinyinCaseType caseType)
    {
        format.setToneType(toneType);
        format.setVCharType(vCharType);
        format.setCaseType(caseType);
    }

    public String[] getHomophony(char charactor)
            throws BadHanyuPinyinOutputFormatCombination
    {
        String[] homophony = PinyinHelper.toHanyuPinyinStringArray(charactor,this.format);

        Set<String> homophonySet = new HashSet<String>(Arrays.asList(homophony));
        /*
            Use set to dedupe the pinyin without tone
         */
        return homophonySet.toArray(new String[homophonySet.size()]);
    }

    /*
        Instead of return one PinYin String. It return all Homophony. During evaluation, it will pickup the most likely
        Homophony for each char to reduce the calculation on combination
     */
    public List<String[]> getTermPinYin(String str)
            throws BadHanyuPinyinOutputFormatCombination
    {
        List<String[]> pinYinString = new ArrayList<String[]>();
        for(int i = 0; i < str.length(); ++i)
        {
            //tempPinyin =getCharacterPinYin(str.charAt(i));
            String[] tempPinyin = getHomophony(str.charAt(i));

            if(tempPinyin == null)
            {

                /*
                @Todo 如果str.charAt(i)非汉字，从配置文件中读取非汉字之母发音
                 */
                pinYinString.add(Arrays.asList(String.valueOf(str.charAt(i))).toArray(new String[1]));
            }
            else
            {
                pinYinString.add(tempPinyin);
            }
        }
        return pinYinString;
    }
}
