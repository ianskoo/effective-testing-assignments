package zest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.stream.Stream;

public class PalindromeTwoTest {


    @Test
    public void exceptionalValsTest(){
        // Exceptional values
        //     assertEquals(true, PalindromeTwo.isPalindrome(1));
        //     assertEquals(null, PalindromeTwo.isPalindrome(true));
        // assertEquals(null, PalindromeTwo.isPalindrome(null));
        assertThrows(
            IllegalArgumentException.class, 
            () -> PalindromeTwo.isPalindrome((int) Math.round(-Math.pow(2, 20) - 1))
        );

        assertThrows(
            IllegalArgumentException.class, 
            () -> PalindromeTwo.isPalindrome((int) Math.round(Math.pow(2, 20)))
        );
    }

    static Stream<Arguments> specificationTestCases() {
        return Stream.of(
            // Legal int boundaries
            of((int) Math.round(-Math.pow(2, 20)), false),
            of((int) Math.round(Math.pow(2, 20) - 1), false),

            // x < 0
            of(-1, false),
            of(-1, false),
            of(9, true),
            of(10, false),

            // Structural testing additions
            of(11, true),
            of(12, false),
            of(242, true),
            of(363, true),
            of(4321, false)
        );
    }
    
    @ParameterizedTest
    @MethodSource("specificationTestCases")
    public void specificationTest(int candidate, boolean expected) {
        assertEquals(expected, PalindromeTwo.isPalindrome(candidate));
    }


}