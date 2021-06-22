package com.zju.dq.sound;

/**
 * @author Linker
 * @date 6/22/2021 7:07 PM
 * @description：
 */
public class LongestSSCSubsequence {

    public static Double THRESHOLD = 0.8;

    public Integer lss(String str1, String str2) {
        int size1 = str1.length();
        int size2 = str2.length();

        int[][] dp = new int[size1 + 1][size2 + 1];
        for (int i = 0; i < size1 + 1; i++) {
            for (int j = 0; j < size2 + 1; j++) {
                dp[i][j] = 0;
            }
        }

        for (int i = 0; i < size1 + 1; i++) {
            Character c1 = str1.charAt(i);
            for (int j = 0; j < size2 + 1; j++) {
                Character c2 = str2.charAt(j);

                if (isSimilar(c1, c2)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[size1][size2];
    }

    /**
     * 判断是否相似
     *
     * @param c1
     * @param c2
     * @return
     */
    public boolean isSimilar(Character c1, Character c2) {
        // SOUND
        SSC ssc = new SSC();
        String[] c1SSC = ssc.getSSC(c1);
        String[] c2SSC = ssc.getSSC(c2);

        Double res = ssc.computeSoundSSCSimilarity(c1SSC, c2SSC);
        if (res > THRESHOLD) {
            return true;
        } else {
            return false;
        }

    }
}
