package cn.applesay.tag.ext.util;

import com.hankcs.hanlp.dictionary.py.Pinyin;

import java.util.*;

/**
 * 求最长公共字串的长度<br>
 *     最长公共子串（Longest Common Substring）指的是两个字符串中的最长公共子串，要求子串一定连续
 * @Ref http://www.hankcs.com/program/algorithm/implementation-and-application-of-nlp-longest-common-subsequence-longest-common-subsequence-of-java.html
 * @author hankcs
 */
public class LongestCommonSubstring
{
    public static final int SCORE_UNIT = 10;
    public static final String CONCATENATED_STRING = "+";
    public static final int SCORE_NO_MATCH = 0;
    private HashMap<String, Integer> switchableMap = null;
    //private Comparator<?> comparator = null;

    public LongestCommonSubstring(HashMap<String, Integer> switchableMap){
        this.switchableMap = switchableMap;

    }

    /**
     * 利用两次遍历查找最大匹配, 二维数据空间最优
     * @param target
     * @param alternative
     * @return
     */
    public  int compute(List<String> target, List<String> alternative)
    {
        if (target == null || target.size() ==0 || alternative == null || alternative.size()==0)
            return 0;


        int highestScore = getHighestScore(target, alternative);
        int highestScore_reverse = getHighestScore(alternative, target);

        // shift string2 to find the longest com.hankcs.common substring
        return Math.max(highestScore,highestScore_reverse);
    }

    private int getHighestScore(List<String> target, List<String> alternative) {
        int target_size = target.size();
        int alternative_size = alternative.size();
        // the start position of substring in original string
        int target_start = -1;
        int alternative_start = -1;

        //The biggest score, unit is 10
        int highestScore = 0;
        int longest=0;

        // record how many comparisons the solution did;
        // it can be used to know which algorithm is better
        int comparisons = 0;

        /*
            Add two past pos score to keep short term memory
            Currently memory is only skip 1 mismatch
             */
        boolean isSkip = true;

        for (int i = 0; i < target_size; ++i)
        {
            int m = i;
            int n = 0;
            //Matched length in this round
            int score = 0;
            int length = 0;
            while (m < target_size && n < alternative_size)
            {
                ++comparisons;

                int currentScore = getEvalScore(target.get(m),alternative.get(n));

                if (currentScore > SCORE_NO_MATCH)
                {
                    ++length;
                    score += currentScore;
                    if (highestScore < score)
                    {
                        highestScore = score;
                        longest = length;
                        target_start = m - longest + 1;
                        alternative_start = n - longest + 1;
                    }
                    isSkip = true;
                }else
                {
                    if(isSkip)
                    {
                        ++m;
                        isSkip =false;
                        continue;
                    }
                    score =0;
                    length =0;
                }

                ++m;
                ++n;
            }
        }
        System.out.printf("from %d of %s and %d of %s, compared for %d times\t Max score is %d\t Max length is %d \n",
                target_start, target, alternative_start, alternative, comparisons, highestScore, longest);
        return highestScore;
    }

    private int getEvalScore(String src, String target)
    {
        int score = SCORE_NO_MATCH;
        if (src.equalsIgnoreCase(target)) {
            score = SCORE_UNIT;
        }
        else if (switchableMap != null)
        {
            String mapKey = src.trim() + CONCATENATED_STRING + target.trim();
            String mapKey2 = target.trim() +CONCATENATED_STRING + src.trim();
            Integer switchScore = switchableMap.get(mapKey) != null? switchableMap.get(mapKey):switchableMap.get(mapKey2);
            if (switchScore != null)
            {
                score = switchScore;
            }
        }

        return score;
    }

    public  double computeSyllable(List<Pinyin> target, List<Pinyin> alternative, boolean removeTone)
    {
        if (target == null || target.size() ==0 || alternative == null || alternative.size()==0)
            return 0;


        double highestScore = getPinyingScore(target, alternative,removeTone);
        double highestScore_reverse = getPinyingScore(alternative, target,removeTone);

        // shift string2 to find the longest com.hankcs.common substring
        return Math.max(highestScore,highestScore_reverse);
    }

    private double getPinyingScore(List<Pinyin> target, List<Pinyin> alternative, boolean removeTone) {
        int target_size = target.size();
        int alternative_size = alternative.size();
        // the start position of substring in original string
        int target_start = -1;
        int alternative_start = -1;

        //The biggest score, unit is 10
        double highestScore = 0;
        int longest=0;

        // record how many comparisons the solution did;
        // it can be used to know which algorithm is better
        int comparisons = 0;


        for (int i = 0; i < target_size; ++i)
        {
            int m = i;
            int n = 0;
            //Matched length in this round
            double score = 0;
            int length = 0;


            /*
            Add two past pos score to keep short term memory
            Currently memory is only skip 1 mismatch
             */
            boolean isSkip = true;

            while (m < target_size && n < alternative_size)
            {
                ++comparisons;

                //If exactly match
                Pinyin pinyinTarget = target.get(m);
                Pinyin pinyinAlternative = alternative.get(n);
                int currentConsonantScore = getEvalScore(pinyinTarget.getShengmu().name(), pinyinAlternative.getShengmu().name());
                int currentVowelScore = getEvalScore(pinyinTarget.getYunmu().name(), pinyinAlternative.getYunmu().name());
                int currentToneScore = Math.abs(pinyinTarget.getTone() - pinyinAlternative.getTone());


                if (currentConsonantScore > SCORE_NO_MATCH && currentVowelScore >SCORE_NO_MATCH)
                {
                    ++length;
                    double currentScore = (SCORE_UNIT*2.0 - Math.sqrt((SCORE_UNIT - currentConsonantScore)^2 + (SCORE_UNIT - currentVowelScore)^2))/2;
                    currentScore = removeTone? currentScore:
                            currentScore*(currentToneScore==SCORE_NO_MATCH?1.0:0.9);

                    score += currentScore ;

                    if (highestScore < score)
                    {
                        highestScore = score;
                        longest = length;
                        target_start = m - longest + 1;
                        alternative_start = n - longest + 1;
                    }
                    isSkip = true;
                } else{
                    if (isSkip)
                    {
                        isSkip = false;
                        ++m;
                        continue;
                    }
                        score =0.0d;
                        length =0;
                }



                ++m;
                ++n;
            }
        }
        System.out.printf("from %d of %s and %d of %s, compared for %d times\t Max score is %f\t Max length is %d \n",
                target_start, target, alternative_start, alternative, comparisons, highestScore, longest);
        return highestScore;
    }

}