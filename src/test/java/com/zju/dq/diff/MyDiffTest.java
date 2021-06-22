package com.zju.dq.diff;

import com.zju.dq.diff.demo.test.diff_match_patch;

import com.zju.dq.diff.pojo.Delta;
import com.zju.dq.diff.pojo.Patch;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.zju.dq.diff.demo.test.Speedtest.readFile;

/**
 * @author Linker
 * @date 6/22/2021 9:42 AM
 * @description：
 */
@SpringBootTest
public class MyDiffTest {
    @Ignore
    @Test
    void test1() throws IOException {
        String text1 = readFile("E:\\Linker\\linker-diff\\src\\main\\java\\com\\zju\\dq\\diff\\demo\\test\\Speedtest1.txt");
        String text2 = readFile("E:\\Linker\\linker-diff\\src\\main\\java\\com\\zju\\dq\\diff\\demo\\test\\Speedtest2.txt");

        diff_match_patch dmp = new diff_match_patch();
        dmp.Diff_Timeout = 0;

        // Execute one reverse diff as a warmup.
        dmp.diff_main(text2, text1, false);

        long start_time = System.nanoTime();
        dmp.diff_main(text1, text2, false);
        long end_time = System.nanoTime();
        System.out.printf("Elapsed time: %f\n", ((end_time - start_time) / 1000000000.0));
    }

    @Test
    void test2(){
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
    }

}
