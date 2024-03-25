package zest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Arrays;

class GenerateParenthesesTest {
    private int catalanNumber(int n) {
        long result = 1;
        for (int i = 0; i < n; ++i) {
            result *= 2 * (2 * i + 1);
            result /= (i + 2);
        }
        return (int) result;
    }
/*
    @Test
    void testRun() {
        for (int i = 1; i <= 8; i++) {
            List<String> result = GenerateParentheses.generateParentheses(i);
            System.out.println("n = " + i + ",size: " +  result.size() + ": " + result);
        }
    }
    */

    @Test
    void rejectIllegalInput() {
        assertThrows(IllegalArgumentException.class, () -> GenerateParentheses.generateParentheses(9));
        //assertThrows(IllegalArgumentException.class, () -> GenerateParentheses.generateParentheses(null));

    }

    @Test
    void testGenerateParenthesesSizes() {
        for (int n = 1; n <= 8; n++) {
            List<String> combinations = GenerateParentheses.generateParentheses(n);
            int expectedSize = catalanNumber(n);
            assertEquals(expectedSize, combinations.size(), "Wrong number of combinations for input: " + n);
        }
    }

    @Test
    void testGenerateParenthesesForOne() {
        List<String> expected = Arrays.asList("()");
        List<String> actual = GenerateParentheses.generateParentheses(1);
        assertEquals(expected, actual, "Problem with n=1");

    }

    @Test
    void testGenerateParenthesesForTwo() {
        List<String> expected = Arrays.asList("(())", "()()");
        List<String> actual = GenerateParentheses.generateParentheses(2);
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual), "Problem with n=2");
    }


    @Test
    void testGenerateParenthesesForThree() {
        List<String> expected = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        List<String> actual = GenerateParentheses.generateParentheses(3);
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual), "Problem with n=3");
    }
}