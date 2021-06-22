package com.zju.dq.sound;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Linker
 * @date 6/8/2021 12:25 PM
 * @description：
 */
public class PinYinUtils {

    public static List<String[]> stringsToPinYinStringArrayList(String str) throws BadHanyuPinyinOutputFormatCombination {
        //replaceAll
        str = replaceAll(str);

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//设置大小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不加入声调

//        StringBuilder sb = new StringBuilder();

        List<String[]> pinyinList = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            //如果是空格则跳过
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (isHanZi(c)) {
                //多音字，返回多个拼音
                String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c, format);

                pinyinList.add(pinyinStringArray);

            } else if (isEnglish(c)) {
                continue;
            }
        }
        return pinyinList;
    }

    /**
     * @param c
     * @return
     */
    public static String[] stringToPinYinStringArray(Character c) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//设置大小写
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);//不加入声调
        String[] pinyinArray = new String[10];
        //如果是空格则跳过
        if (Character.isWhitespace(c)) {
            return new String[]{};
        }
        if (isHanZi(c)) {
            //多音字，返回多个拼音
            pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
        } else if (isEnglish(c)) {
            return new String[]{};
        }
        return pinyinArray;
    }

    public static boolean isHanZi(char c) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
        Matcher matcher = pattern.matcher(String.valueOf(c));
        return matcher.matches();
    }

    public static boolean isEnglish(char c) {
        return String.valueOf(c).matches("^[a-zA-Z]*");
    }

    public static String replaceAll(String str) {
        return str.replaceAll("\\p{P}", "");
    }


    private static final String[] Shengmu = {"b", "p", "m", "f", "d", "t", "l", "n", "g", "k", "h", "j", "q", "x", "z",
            "c", "s", "zh", "ch", "sh", "y", "w"};
    private static final String[] Yunmu = { "a", "o", "e", "i", "u", "v", "ai", "ei", "ui", "ao", "ou", "iu", "ie",
            "ue", "ve", "er", "an", "en", "in", "un", "ang", "eng", "ing", "ong" };

}
