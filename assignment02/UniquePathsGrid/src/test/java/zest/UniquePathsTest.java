package zest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import net.jqwik.api.*;

class UniquePathsTest {
    UniquePaths up = new UniquePaths();

    @Test
    void testExample1() {
        assertEquals(28, up.uniquePaths(3, 7));
    }

    @Test
    void testExample2() {
        assertEquals(3, up.uniquePaths(2, 3));
    }

    @Test
    void testExample3() {
        assertEquals(6, up.uniquePaths(3, 3));
    }

    @Test
    void test1x1Grid() {
        assertEquals(1, up.uniquePaths(1, 1));
    }

    // Overflows
    // @Test
    // void test100x100Grid() {
    // assertEquals(Integer.MAX_VALUE, up.uniquePaths(100, 100));
    // }

    @Test
    void testPreConditionViolation() {
        assertThrows(AssertionError.class, () -> {
            up.uniquePaths(-10, 10);
        });
    }

    @Test
    void testPreConditionViolation2() {
        assertThrows(AssertionError.class, () -> {
            up.uniquePaths(0, 101);
        });
    }

    @Test
    void testPostCondition() {
        assertTrue(up.uniquePaths(1, 6) > 0);
    }

    @Property
    void testSymmetry(@ForAll("gridSizes") int m, @ForAll("gridSizes") int n) {
        assertEquals(up.uniquePaths(m, n), up.uniquePaths(n, m));
    }

    @Property
    void testConsistency(@ForAll("gridSizes") int m, @ForAll("gridSizes") int n) {
        assertEquals(up.uniquePaths(m, n), up.uniquePaths(m, n));
    }

    @Provide
    Arbitrary<Integer> gridSizes() {
        return Arbitraries.integers().between(1, 17);
    }
}
