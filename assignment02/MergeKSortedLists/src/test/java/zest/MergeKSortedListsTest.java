package zest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


public class MergeKSortedListsTest {

    MergeKSortedLists merger = new MergeKSortedLists();

    @Test
    public void emptyArrayTest(){
        assertEquals(null, merger.mergeKLists(new ListNode[0]));
    }

    @Test
    public void nullTest(){
        assertEquals(null, merger.mergeKLists(null),
                     "passing null should return null");
    }

    @Test
    public void nodeCountOverUpperBoundaryTest(){
        ListNode[] listNodes = new ListNode[1];

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
        // System.out.println(getLinkedListLength(listNodes[0]));

        assertThrows(
            IllegalArgumentException.class, 
            () -> merger.mergeKLists(listNodes)
        );
        
        // ListNode resultNode = merger.mergeKLists(listNodes);
        // System.out.println(resultNode.val);
        // assertEquals(new ListNode[0], resultNode);

    }

    @Test
    public void nodeValueOverUpperBoundaryTest(){
        ListNode[] listNodes = new ListNode[1];
        listNodes[0] = new ListNode((int) Math.pow(10, 4) + 1);

        assertThrows(
            IllegalArgumentException.class, 
            () -> merger.mergeKLists(listNodes)
        );
    }

    @Test
    public void nodeValueUnderLowerBoundaryTest(){
        ListNode[] listNodes = new ListNode[1];
        listNodes[0] = new ListNode((int) -Math.pow(10, 4) - 1);

        assertThrows(
            IllegalArgumentException.class, 
            () -> merger.mergeKLists(listNodes)
        );
    }


    @Test
    public void validInputTest(){
        ListNode[] listNodes = new ListNode[3];

        // Try out all ListNode constructors
        // listNodes will be: [[-1, 2, 3], [2], [0]]
        listNodes[2] = new ListNode();
        listNodes[1] = new ListNode(2);
        listNodes[0] = new ListNode(-1, new ListNode(2, new ListNode(3)));

        //  [-1, 0, 2, 2, 3]
        ListNode expected = new ListNode(
            -1, 
            new ListNode(
                0, 
                new ListNode(
                    2,
                    new ListNode(
                        2,
                        new ListNode(3)
                    )
                )
            )
        );
        ListNode actual = merger.mergeKLists(listNodes);
        // System.out.println("Expected linked list: ");
        // printLinkedList(expected);
        // System.out.println("Actual linked list: ");
        // printLinkedList(actual);

        assertTrue(areLinkedListsEqual(expected, actual));
    }


    @Test
    public void unSortedInputTest(){
        ListNode[] listNodes = new ListNode[2];

        // listNodes will be: [[-1, -2], [2]]
        listNodes[0] = new ListNode(-1, new ListNode(-2));
        listNodes[1] = new ListNode(2);

        assertThrows(
            IllegalArgumentException.class, 
            () -> merger.mergeKLists(listNodes)
        );
    }


    // Method to calculate the length of the linked list
    private static int getLinkedListLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;         // Increment the length counter
            current = current.next;  // Move to the next node
        }
        return length;
    }

    private static boolean areLinkedListsEqual(ListNode head1, ListNode head2) {
        ListNode current1 = head1;
        ListNode current2 = head2;

        while (current1 != null && current2 != null) {
            if (current1.val != current2.val) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        // Check if one of the lists still has a node
        return current1 == null && current2 == null;
    }

    private void printLinkedList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

}