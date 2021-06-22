package com.zju.dq.pinyin;

import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.pinyin.engine.pinyin4j.Pinyin4jEngine;
import com.zju.dq.sound.PinYinUtils;
import io.github.frapples.javapinyin.api.JavaPinyin;
import io.github.frapples.javapinyin.api.constants.Style;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author Linker
 * @date 6/22/2021 12:35 PM
 * @description：
 */
@SpringBootTest
public class PinyinTest {
    @Test
    void test1() throws BadHanyuPinyinOutputFormatCombination {
//        PinYinUtils pinYinUtils = new PinYinUtils();
//        List<String[]> pyList = pinYinUtils.stringToPinYinStringArrayList("泥头车");
//        System.out.println(pyList.toArray().toString());
    }

    @Test
    void test2() throws BadHanyuPinyinOutputFormatCombination {
        PinYinUtils pinYinUtils = new PinYinUtils();
        String[] s = pinYinUtils.stringToPinYinStringArray('车');
        System.out.println(Arrays.toString(s));
    }

    @Test
    void test3() throws BadHanyuPinyinOutputFormatCombination {
        //[che1, ju1]
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//设置大小写
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);//不加入声调
        Character c = '车';

        String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c, format);

        System.out.println(Arrays.toString(pinyinStringArray));
    }

    @Test
    void test4() throws BadHanyuPinyinOutputFormatCombination {
        //[che1, ju1]
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//设置大小写
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);//不加入声调
        Character c = '车';

        String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c, format);

        System.out.println(Arrays.toString(pinyinStringArray));
        char letter = PinyinUtil.getFirstLetter(c);
        System.out.println(letter);

//        PinyinUtil.is
    }

    @Test
    void test5(){

        List<String> result = JavaPinyin.pinyinForWord("一举两得", Style.TONE3);

    }

}
