package com.zju.dq.diff;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.zju.dq.diff.demo.test.Speedtest.readFile;

/**
 * @author Linker
 * @date 6/22/2021 9:57 AM
 * @description：
 */
@SpringBootTest
public class DiffUtilTest {
    @Test
    void test1 () throws IOException {
//        public static <T> Patch<T> diff(List<T> original, List<T> revised,
//                BiPredicate<T, T> equalizer) throws DiffException {
//            if (equalizer != null) {
//                return DiffUtils.diff(original, revised,
//                        new MyersDiff<>(equalizer));
//            }
//            return DiffUtils.diff(original, revised, new MyersDiff<>());
//        }
//        String text1 = readFile("E:\\Linker\\linker-diff\\src\\main\\java\\com\\zju\\dq\\diff\\demo\\test\\Speedtest1.txt");
//        String text2 = readFile("E:\\Linker\\linker-diff\\src\\main\\java\\com\\zju\\dq\\diff\\demo\\test\\Speedtest2.txt");

        /**
         * google diff
         */
        String text1 = "泥头车www小黄鸭eeeAAww";
        String text2 = "泥头车啊啊啊小黄鸭是最帅的AA";
        System.out.println(text1);
        System.out.println(text2);

        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();
        for (int i = 0; i < text1.length(); i++) {
            list1.add(text1.charAt(i));
        }
        for (int i = 0; i < text2.length(); i++) {
            list2.add(text2.charAt(i));
        }
        Patch diff = DiffUtils.diff(list1, list2);
        for (Delta delta:diff.getDeltas()
        ) {
            System.out.println(delta);
        }
        /**
         * 统计相同子串之间的距离，统计子串之间的相似度，统计子串之间的时间
         */

    }
}
