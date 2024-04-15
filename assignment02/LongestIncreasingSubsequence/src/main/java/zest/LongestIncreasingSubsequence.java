package zest;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        assert nums != null : "Input cannot be null";
        if (/* nums == null || */ nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1; // Each element is an increasing subsequence of length 1

            // Check all previous elements to find longer increasing subsequences
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            int oldMaxLength = maxLength;
            maxLength = Math.max(maxLength, dp[i]);
            assert maxLength >= oldMaxLength : "maxLength should not decrease";
        }
        assert maxLength >= 0 : "Output has to be non-negative";
        return maxLength;
    }
}

