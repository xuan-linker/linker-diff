package com.zju.dq.sound;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * @author Linker
 * @date 6/22/2021 11,21 AM
 * @description：
 */
public class SSC {

    Map<Object, Object> colorMap = MapUtil.of(new String[][]{
            {"RED", "#FF0000"},
            {"GREEN", "#00FF00"},
            {"BLUE", "#0000FF"}
    });

    Dict yunmuDict = Dict.create()
            .set("a", "1").set("o", "2").set("e", "3").set("i", "4").set("u", "5").set("v", "6").set("ai", "7").set("ei", "7").set("ui", "8")
            .set("ao", "9").set("ou", "A").set("iou", "B").set("ie", "C").set("ve", "D").set("er", "E").set("an", "F").set("en", "G").set("in", "H")
            .set("un", "I").set("vn", "J").set("ang", "F").set("eng", "G").set("ing", "H").set("ong", "K");

    Dict shengmuDict = Dict.create()
            .set("b", "1")
            .set("p", "2")
            .set("m", "3")
            .set("f", "4")
            .set("d", "5")
            .set("t", "6")
            .set("n", "7")
            .set("l", "7")
            .set("g", "8")
            .set("k", "9")
            .set("h", "A")
            .set("j", "B")
            .set("q", "C")
            .set("x", "D")
            .set("zh", "E")
            .set("ch", "F")
            .set("sh", "G")
            .set("r", "H")
            .set("z", "E")
            .set("c", "F")
            .set("s", "G")
            .set("y", "I")
            .set("w", "J")
            .set("0", "0");

    public String getSoundCode(String oneChiWord) {
        String[] res = new String[oneChiWord.length()];
        //pinyinUtils get shengmu
        String shengmuStr =


    }

}
