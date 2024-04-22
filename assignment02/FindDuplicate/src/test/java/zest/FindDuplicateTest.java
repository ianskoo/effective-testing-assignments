package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

public class FindDuplicateTest {

    @Test
    public void findDuplicateTest() {
        assertEquals(FindDuplicate.findDuplicate(new int[]{1,1}), 1);
        assertEquals(FindDuplicate.findDuplicate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10}), 10);
        assertEquals(FindDuplicate.findDuplicate(new int[]{1, 2, 3, 4, 5, 6, 1}), 1);
    }

    @Test
    public void ThreeOrMore() {
        assertEquals(FindDuplicate.findDuplicate(new int[]{1, 2, 3, 4, 5, 1, 6, 7, 1}), 1);
        assertEquals(FindDuplicate.findDuplicate(new int[]{1,2,3,4,5,6,7,6,6,6,6,6,6,6}), 6);
    }

    @Test
    public void testPreconditions() {
        assertThrows(AssertionError.class, () -> FindDuplicate.findDuplicate(new int[]{1, 4}));
        assertThrows(AssertionError.class, () -> FindDuplicate.findDuplicate(null));
        assertThrows(AssertionError.class, () -> FindDuplicate.findDuplicate(new int[]{1}));
        assertThrows(AssertionError.class, () -> FindDuplicate.findDuplicate(new int[]{-1, 4}));
    }

    //check if property holds that if there is a list with all elements and one duplicate, it returns the duplicate
    @Property
    public void findDuplicateTest2(@ForAll @IntRange(min = 1, max = 999) int duplicate, @ForAll @IntRange(min = 0, max = 999) int index) {
        int[] array = IntStream.rangeClosed(1, 1000).toArray();
        array[999] = duplicate;
        assertEquals(FindDuplicate.findDuplicate(array), duplicate);
    }

}