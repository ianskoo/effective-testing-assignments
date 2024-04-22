package zest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import net.jqwik.api.*;

class UniquePathsTest {
    UniquePaths up = new UniquePaths();

    @Test
    void testExample1() {
        assertEquals(BigInteger.valueOf(28), up.uniquePaths(3, 7));
    }

    @Test
    void testExample2() {
        assertEquals(BigInteger.valueOf(3), up.uniquePaths(2, 3));
    }

    @Test
    void testExample3() {
        assertEquals(BigInteger.valueOf(6), up.uniquePaths(3, 3));
    }

    @Test
    void test1x1Grid() {
        assertEquals(BigInteger.ONE, up.uniquePaths(1, 1));
    }

    @Test
    void test100x100Grid() {
        assertEquals(new BigInteger(
                "22750883079422934966181954039568885395604168260154104734000"),
                up.uniquePaths(100, 100));
    }

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
        assertTrue(up.uniquePaths(1, 6).compareTo(BigInteger.ZERO) > 0);
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
        return Arbitraries.integers().between(1, 100);
    }
}
