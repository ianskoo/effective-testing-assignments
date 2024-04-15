package zest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.ArrayList;
import java.util.List;

class LongestIncreasingSubsequenceTest {
    LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();

    @Test
    void testEmptyArray() {
        int expected = 0;
        int actual = lis.lengthOfLIS(new int[]{});
        assertEquals(expected, actual, "Problem with empty list");
    }

    @Test
    void testSingleElementArray() {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int expected = 1;
        int actual = lis.lengthOfLIS(new int[]{0});
        assertEquals(expected, actual, "Problem with single element array");
    }

    @Test
    void testAllElementsSame() {
        int expected = 1;
        int actual = lis.lengthOfLIS(new int[]{2, 2, 2, 2, 2, 2, 2, 2});
        assertEquals(expected, actual, "Problem with array where all elements are the same");
    }

    @Test
    void testAlreadySorted() {
        int expected = 6;
        int actual = lis.lengthOfLIS(new int[]{3, 4, 5, 6, 7, 8});
        assertEquals(expected, actual, "Problem with already sorted array");
    }

    @Test
    void testDescendingOrder() {
        int expected = 1;
        int actual = lis.lengthOfLIS(new int[]{5, 4, 3, 2, 1});
        assertEquals(expected, actual, "Problem with descending order array");
    }

    @Test
    void testMixedOrder() {
        int expected = 4;
        int actual = lis.lengthOfLIS(new int[]{10, 7, 1, 3, 9, 5, 2, 13});
        assertEquals(expected, actual, "Problem with mixed order array");
    }

    @Test
    void testNull() {
        int expected = 0;
        /*
        int actual = lis.lengthOfLIS(null);
        assertEquals(expected, actual, "Problem with empty list");
        */
        Error error = assertThrows(Error.class, () -> lis.lengthOfLIS(null));
        assertTrue(error.getMessage().contains("Input cannot be null"));
    }

    @Property
    void testLongestIncreasingSubsequence(@ForAll @IntRange(min = 1, max = 30) int listSize) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(0); // Starting with [1]
        int counter = 1;
        int lastInLIS = 0; // Start tracking the last number that was part of the LIS

        for (int i = 1; i < listSize; i++) {
            int nextNumber = numbers.get(numbers.size() - 1) + (Arbitraries.of(1, 0, -1).sample() == 1 ? 1 : -1);
            if (nextNumber < 0){
                nextNumber = 0;
            }
            numbers.add(nextNumber);
            if (nextNumber > lastInLIS) {
                lastInLIS = nextNumber;
                counter++;
            }
        }

        // Debugging output
        System.out.println("Generated array: " + numbers);
        System.out.println("Expected LIS length: " + counter);

        int actualLIS = lis.lengthOfLIS(numbers.stream().mapToInt(i -> i).toArray());
        assertEquals(counter, actualLIS, "LIS length should match the counter");
    }
 
}