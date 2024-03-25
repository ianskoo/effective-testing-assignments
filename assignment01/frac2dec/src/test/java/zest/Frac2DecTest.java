package zest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;import java.util.List;
import java.util.Arrays;

import java.util.List;
import java.util.Arrays;


class Frac2DecTest {

    @Test
    void testFractionToDecimal() {
        // Test case 1: Non-repeating decimal
        assertEquals("0.5", Frac2Dec.fractionToDecimal(1, 2), "1/2 should equal 0.5");

        // Test case 2: Non-repeating decimal, whole number
        assertEquals("2", Frac2Dec.fractionToDecimal(2, 1), "2/1 should equal 2");

        // Test case 3: Repeating decimal
        assertEquals("0.(012)", Frac2Dec.fractionToDecimal(4, 333), "4/333 should equal 0.(012)");

        // Test case 4: Zero numerator
        assertEquals("0", Frac2Dec.fractionToDecimal(0, 5), "0/5 should equal 0");

        // Test case 5: Negative fraction
        assertEquals("-0.5", Frac2Dec.fractionToDecimal(-1, 2), "-1/2 should equal -0.5");

        // Additional tests can be added here following the same format
    }

    @Test
    void testDivisionByZero() {
        // Existing test cases...

        // Test case for null: denominator is zero
        assertNull(Frac2Dec.fractionToDecimal(1, 0), "1/0 should return null due to invalid division.");
    }

 }