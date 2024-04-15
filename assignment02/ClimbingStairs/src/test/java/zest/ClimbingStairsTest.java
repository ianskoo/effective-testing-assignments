package zest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import net.jqwik.api.*;

class ClimbingStairsTest {
    ClimbingStairs cs = new ClimbingStairs();
    /*
    @Test
    void testRun() {
        ClimbingStairs cs = new ClimbingStairs();
        for (int i = 1; i <= 8; i++) {
            int result = cs.climbStairs(i);
            System.out.println("n = " + i + ",result: " +  result);
        }
    }
    */

    @Test
    void climbStairsOne() {
        int expected = 1;
        int actual = cs.climbStairs(1);
        assertEquals(expected, actual, "Problem with n=1");
    }

    @Test
    void climbStairsTwo() {
        int expected = 2;
        int actual = cs.climbStairs(2);
        assertEquals(expected, actual, "Problem with n=2");
    }

    @Test
    void climbStairsThree() {
        int expected = 3;
        int actual = cs.climbStairs(3);
        assertEquals(expected, actual, "Problem with n=3");
    }

    @Test
    void climbStairsTen() {
        int expected = 89;
        int actual = cs.climbStairs(10);
        assertEquals(expected, actual, "Problem with n=10");
    }

    @Test
    void climbStairsNegativeInput() {
        // Use Error.class instead of Exception.class
        Error error = assertThrows(Error.class, () -> cs.climbStairs(-1));
        assertTrue(error.getMessage().contains("n must be positive"));
    }

    @Test
    void climbStairsPostCondition() {
        for (int i = 1; i <= 10; i++) {
            int result = cs.climbStairs(i);
            assertTrue(result >= 0, "Post-condition: number of ways must be non-negative");
        }
    }


    @Property
    boolean FibonacciSequencePBTest(@ForAll("randomIntegers") int n) {
        if (n <= 2) {

            return cs.climbStairs(n) == n;
        }
        return cs.climbStairs(n) == cs.climbStairs(n - 1) + cs.climbStairs(n - 2);
    }

    @Provide
    Arbitrary<Integer> randomIntegers() {
        return Arbitraries.integers().between(1, 30);
    }
}


