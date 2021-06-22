package com.zju.dq.sound;

import cn.hutool.core.lang.Dict;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author Linker
 * @date 6/22/2021 11,21 AM
 * @description：
 */
public class SSC {
    /**
     * 1韵母位表
     * iou-iu
     * ven-vn
     */
    Dict yunmuDict = Dict.create()
            .set("a", "1").set("o", "2").set("e", "3").set("i", "4")
            .set("u", "5").set("v", "6").set("ai", "7").set("ei", "7")
            .set("ui", "8").set("ao", "9").set("ou", "A").set("iou", "B")
            .set("ie", "C").set("ve", "D").set("er", "E").set("an", "F")
            .set("en", "G").set("in", "H").set("un", "I").set("vn", "J")
            .set("ang", "F").set("eng", "G").set("ing", "H").set("ong", "K");

    /**
     * 2声母位表
     */
    Dict shengmuDict = Dict.create()
            .set("b", "1").set("p", "2").set("m", "3").set("f", "4")
            .set("d", "5").set("t", "6").set("n", "7").set("l", "7")
            .set("g", "8").set("k", "9").set("h", "A").set("j", "B")
            .set("q", "C").set("x", "D").set("zh", "E").set("ch", "F")
            .set("sh", "G").set("r", "H").set("z", "E").set("c", "F")
            .set("s", "G").set("y", "I").set("w", "J").set("0", "0");

    /**
     * 3.补码：声母和韵母之间的辅音
     * 4.声调：1,2,3,4替代四声
     *
     * @param oneChiWord
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public String getSoundCode(String oneChiWord) throws BadHanyuPinyinOutputFormatCombination {
        String[] res = new String[oneChiWord.length()];
        //声母
        String[] pinyinArray = PinYinUtils.stringToPinYinStringArray(oneChiWord.charAt(0));
        shengmuDict.containsKey();

        /**
         *  韵母
         *  FINALS_TONE3: 拼音风格 [['zhong1', 'zhong4'], ['xin1']]
         *  heteronym: 多音字模式 false
         *  strict: 当成声母 true
         */
        if (yunmuDict.containsKey(yunmuFullStrict))

            //音调
            String[] yindiaoArray = new String[]{"1", "2", "3", "4"};


    }

    /**
     * 暂时只有SoundCode
     *
     * @param oneWord
     * @return
     */
    public String[] getSSC(Character oneWord) {
        //获取声母

        //获取韵母辅音

        //获取韵母
//        diet.containt(yummu...);


        //获取音调

        return null;

    }

    /**
     * 计算音码的权重值
     *
     * @param ssc1
     * @param ssc2
     * @return
     */
    public Double computeSoundSSCSimilarity(String[] ssc1, String[] ssc2) {
        int size1 = ssc1.length;
        double[] weights = {0.4, 0.4, 0.1, 0.1};
        Double[] multiplier = new Double[4];
        for (int i = 0; i < size1; i++) {
            if (ssc1[i] == ssc2[i]) {
                multiplier[i] = 1.0;
            } else {
                multiplier[i] = 0.0;
            }
        }
        Double soundSimilarity = 0.0;
        for (int i = 0; i < size1; i++) {
            soundSimilarity += weights[i] * multiplier[i];
        }
        return soundSimilarity;

    }

}
