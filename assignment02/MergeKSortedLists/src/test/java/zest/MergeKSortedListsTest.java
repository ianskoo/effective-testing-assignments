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


public class MergeKSortedListsTest {

    @Test
    public void exceptionalValsTest(){
        ListNode[] listNodes = new ListNode[2];

        int N = (int) Math.pow(10, 4) + 1;  // Example: Number of nodes
        int M = 3;  // Example: Value to be repeated

        if (listNodes.length > 0) {
            ListNode head = new ListNode(M); // Create the head node with value M
            ListNode current = head; // Use current to traverse and build the list
            for (int i = 1; i < N; i++) { // Start from 1 because the head is already created
                current.next = new ListNode(M); // Create new node with value M and link it
                current = current.next; // Move to the new node
            }
            listNodes[0] = head; // Assign the populated list as the first element in the array
        }

        MergeKSortedLists merger = new MergeKSortedLists();

        // assertThrows(
        //     IllegalArgumentException.class, 
        //     () -> merger.mergeKLists(listNodes)
        // );
        // ListNode resultNode = merger.mergeKLists(listNodes);
        
        System.out.println(resultNode.val);
        assertEquals(new ListNode[0], resultNode);

    }

    // static Stream<Arguments> specificationTestCases() {
    //     return Stream.of(
    //         // Legal int boundaries
    //         of((int) Math.round(-Math.pow(2, 20)), false),
    //         of((int) Math.round(Math.pow(2, 20) - 1), false),

    //         // x < 0
    //         of(-1, false),
    //         of(-1, false),
    //         of(9, true),
    //         of(10, false),
    //         of(1231, false)
    //     );
    // }
    
    // @ParameterizedTest
    // @MethodSource("specificationTestCases")
    // public void specificationTest(int candidate, boolean expected) {
    //     assertEquals(expected, PalindromeOne.isPalindrome(candidate));
    // }


}