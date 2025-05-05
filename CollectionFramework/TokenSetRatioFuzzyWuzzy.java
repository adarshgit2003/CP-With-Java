package CollectionFramework;

import java.util.Arrays;
import java.util.stream.Collectors;
public class TokenSetRatioFuzzyWuzzy {
    public static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + cost
                    );
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // Method to calculate the similarity ratio
    public static double similarityRatio(String s1, String s2) {
        int maxLen = Math.max(s1.length(), s2.length());
        if (maxLen == 0) {
            return 100.0; // Both strings are empty, so return 100%
        }
        int distance = levenshteinDistance(s1, s2);
        return ((maxLen - distance) / (double) maxLen) * 100;
    }

    // Method to tokenize, sort, and rejoin the string
    public static String tokenizeAndSort(String s) {
        return Arrays.stream(s.split("\\s+")) // Split by whitespace
                .sorted()                // Sort the tokens
                .collect(Collectors.joining(" ")); // Rejoin them
    }

    // Token Sort Ratio logic
    public static double tokenSortRatio(String s1, String s2) {
        String sortedS1 = tokenizeAndSort(s1);
        String sortedS2 = tokenizeAndSort(s2);
        return similarityRatio(sortedS1, sortedS2);
    }

    public static void main(String[] args) {
        String str1 = "Adarsh Kumar Shrivastav";
        String str2 = "Adarsh Singh";

        double ratio = tokenSortRatio(str1, str2);
        System.out.println("Token Sort Ratio: " + ratio);
    }
}
