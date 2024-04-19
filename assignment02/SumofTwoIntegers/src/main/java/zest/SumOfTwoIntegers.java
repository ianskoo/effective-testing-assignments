package zest;

public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        int originala = a;
        int originalb = b;
        long sum = (long) a + (long) b;
        // Pre-condition
        assert (sum >= Integer.MIN_VALUE && sum <= Integer.MAX_VALUE): "Pre-condition violated: sum exceeds 32-bit signed integer range";
        // Invariant
        assert (invariant(a, b, originala, originalb))
                : "Invariant violated: does not hold before caluclation";
        while (b != 0) {
            int carry = (a & b) << 1; // Carry now contains common set bits of a and b
            a = a ^ b; // Sum of bits of a and b where at least one of the bits is not set
            b = carry; // Carry is shifted by one so that adding it to a gives the required sum
            // Invariant
            assert (invariant(a, b, originala, originalb))
                    : "Invariant violated: does not hold during calculation";
        }
        // Post-condition
        assert a == originala + originalb
                : "Post-condition violated: incorrect sum returned: " + a + " != " + originala + " + " + originalb;
        // Invariant
        assert (invariant(a, b, originala, originalb))
                : "Invariant violated: does not hold after calculation";
        return a;
    }

    private boolean invariant(int a, int b, int originala, int originalb) {
        return a == (originala + originalb - b);
    }
}
