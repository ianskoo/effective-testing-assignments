package zest;


public class PalindromeTwo {

    static int v;

    public static boolean isPalindrome(int x) {
        // Fix: Check legal int range:
        if (x < Math.round(-Math.pow(2, 20)) || x > Math.round(Math.pow(2, 20) - 1)) {
            throw new IllegalArgumentException("x must be between -2^(20) and 2^(20)-1");
        }

        if (x < 0) return false;
        if (x % 10 == 0) return false;
        if (x < 100 && x % 11 == 0) return true;
        if (x < 1000 && ((x / 100) * 10 + x % 10) % 11 == 0) return true;

        v = x % 10;
        x = x / 10;
        while (x - v > 0) {
            v = v * 10 + x % 10;
            x /= 10;
        }
        if (v > x) {
            v /= 10;
        }
        return v == x;
    }
}
