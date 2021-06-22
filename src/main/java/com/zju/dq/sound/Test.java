package com.zju.dq.sound;

/**
 * @author Linker
 * @date 6/22/2021 7:04 PM
 * @description：
 */
public class Test {
    public static void main(String[] args) {
        String str1 = "为有一个家里部的人";
        String str2 = "秦末有个叫季布的人。一向说话";

        str1 = str1.replaceAll("[\\pP\\p{Punct}]","");//清除所有符号,只留下字母 数字  汉字  共3类
        str2 = str2.replaceAll("[\\pP\\p{Punct}]","");//清除所有符号,只留下字母 数字  汉字  共3类
        System.out.println(str1);
        System.out.println(str2);




    }

}
