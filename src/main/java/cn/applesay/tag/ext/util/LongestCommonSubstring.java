package cn.applesay.tag.ext.util;

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
        return highestScore >= highestScore_reverse? highestScore:highestScore_reverse;
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

                //If exactly match
                if (target.get(m).equalsIgnoreCase(alternative.get(n)))
                {
                    ++length;
                    score += SCORE_UNIT;
                    if (highestScore < score)
                    {
                        highestScore = score;
                        longest = length;
                        target_start = m - longest + 1;
                        alternative_start = n - longest + 1;
                    }
                } else if (switchableMap != null)
                {
                    /**
                     * Prepare alternative map
                     */
                    String mapKey = target.get(m).trim() + CONCATENATED_STRING + alternative.get(n).trim();
                    String mapKey2 = alternative.get(n).trim() +CONCATENATED_STRING + target.get(m).trim();
                    Integer switchScore = switchableMap.get(mapKey) != null? switchableMap.get(mapKey):switchableMap.get(mapKey2);
                    if (switchScore != null)
                    {
                        ++length;
                        score += switchScore;
                        if (highestScore < score)
                        {
                            highestScore = score;
                            longest = length;
                            target_start = m - longest + 1;
                            alternative_start = n - longest + 1;
                        }
                    }
                }else{
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

}