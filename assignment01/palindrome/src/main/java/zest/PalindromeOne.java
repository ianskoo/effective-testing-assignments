package zest;

public class PalindromeOne {

    public static boolean isPalindrome(int x) {
        // Fix: Check legal int range:
        if (x < Math.round(-Math.pow(2, 20)) || x > Math.round(Math.pow(2, 20) - 1)) {
            throw new IllegalArgumentException("x must be between -2^(20) and 2^(20)-1");
        }

        // convert input into an array and rest is nothing but a simple two pointer solution
        char[] numbers = String.valueOf(x).toCharArray();
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] != numbers[end]) return false;
            start++;
            end--;
        }
        return true;
    }
}
