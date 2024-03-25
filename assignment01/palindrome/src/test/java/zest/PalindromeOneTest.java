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

public class PalindromeOneTest {


    @Test
    public void exceptionalValsTest(){
        // Exceptional values
        //     assertEquals(true, PalindromeOne.isPalindrome(1));
        //     assertEquals(null, PalindromeOne.isPalindrome(true));
        // assertEquals(null, PalindromeOne.isPalindrome(null));
        assertThrows(
            IllegalArgumentException.class, 
            () -> PalindromeOne.isPalindrome((int) Math.round(-Math.pow(2, 20) - 1))
        );

        assertThrows(
            IllegalArgumentException.class, 
            () -> PalindromeOne.isPalindrome((int) Math.round(Math.pow(2, 20)))
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
            of(10, false)
        );
    }
    
    @ParameterizedTest
    @MethodSource("specificationTestCases")
    public void specificationTest(int candidate, boolean expected) {
        assertEquals(expected, PalindromeOne.isPalindrome(candidate));
    }


}