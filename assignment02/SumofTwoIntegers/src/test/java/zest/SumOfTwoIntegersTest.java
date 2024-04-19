package zest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import net.jqwik.api.*;

class SumOfTwoIntegersTest {
    SumOfTwoIntegers soti = new SumOfTwoIntegers();

    @Test
    void testExample1() {
        assertEquals(3, soti.getSum(1, 2));
    }

    @Test
    void testExample2() {
        assertEquals(1, soti.getSum(-2, 3));
    }

    @Test
    void testTwoNegativeValues() {
        assertEquals(-3, soti.getSum(-1, -2));
    }

    @Test
    void testMaxBorder() {
        assertEquals(Integer.MAX_VALUE, soti.getSum(Integer.MAX_VALUE - 1, 1));
    }

    @Test
    void testMinBorder() {
        assertEquals(Integer.MIN_VALUE, soti.getSum(Integer.MIN_VALUE + 1, -1));
    }

    @Test
    void testPreConditionViolation1() {
        assertThrows(AssertionError.class, () -> {
            soti.getSum(Integer.MIN_VALUE, -1);
        });
    }

    @Test
    void testPreConditionViolation2() {
        assertThrows(AssertionError.class, () -> {
            soti.getSum(Integer.MAX_VALUE, 1);
        });

    }

    @Property
    void commutativity(@ForAll("validIntegers") int a, @ForAll("validIntegers") int b) {
        assertEquals(soti.getSum(a, b), soti.getSum(b, a));
    }

    @Property
    void identity(@ForAll int a) {
        assertEquals(a, soti.getSum(a, 0));
    }

    @Property
    void negation(@ForAll("validIntegers") int a) {
        assertEquals(0, soti.getSum(a, -a));
    }

    @Property
    void associativity(@ForAll("validIntegers") int a, @ForAll("validIntegers") int b, @ForAll("validIntegers") int c) {
        assertEquals(soti.getSum(a, soti.getSum(b, c)), soti.getSum(soti.getSum(a, b), c));
    }

    @Provide
    Arbitrary<Integer> validIntegers() {
        return Arbitraries.integers().between(Integer.MIN_VALUE / 3, Integer.MAX_VALUE / 3);
    }
}
