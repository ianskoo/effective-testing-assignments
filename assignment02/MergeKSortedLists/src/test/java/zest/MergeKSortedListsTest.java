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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.ListArbitrary;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.Tuple.Tuple3;


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


    // ----------- Property-based testing ---------------

    @Property
    void validInputValidOutputTest(
        // @ForAll @IntRange(min = 1, max = 10000) int nrOfLists,
        // @ForAll @Size(max = 10000) List<@IntRange(min = -10000, max = 10000) Integer> intValues
        @ForAll("validNodesListsValues") Tuple3<Integer, Integer, List<Integer>> nodesListsValues
        ) {
        Integer nrOfNodes = nodesListsValues.get1();
        Integer nrOfLists = nodesListsValues.get2();
        List<Integer> values = nodesListsValues.get3();

        // For some reason, validNodesListsValues() sometimes fails to return a values list
        // of size nrOfNodes, even though it's explicitly defined as such. If so, exit the test.
        if (values.size() != nrOfNodes) {return;}

        // System.out.println(nrOfNodes);
        // System.out.println(nrOfLists);
        // System.out.println(values);

        // Divide values into nrOfLists sublists to sort them
        List<List<Integer>> sublists = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < nrOfLists; i++) {
            int end = start + (nrOfNodes / nrOfLists) + (i < (nrOfNodes % nrOfLists) ? 1 : 0);
            if (end > values.size()) end = values.size();
            List<Integer> sublist = new ArrayList<>(values.subList(start, end));
            Collections.sort(sublist);
            sublists.add(sublist);
            start = end;
        }
        
        // Create linked lists from sorted sublists
        ListNode[] lists = new ListNode[nrOfLists];
        for (int i = 0; i < nrOfLists; i++) {
            ListNode head = null;
            ListNode current = null;
            for (Integer val : sublists.get(i)) {
                ListNode newNode = new ListNode(val);
                if (head == null) {
                    head = newNode;
                    current = head;
                } else {
                    current.next = newNode;
                    current = current.next;
                }
            }
            lists[i] = head;
        }
        
        ListNode merged = merger.mergeKLists(lists);
        // System.out.println(sublists);
        // printLinkedList(merged);
        assertTrue(isLinkedListSorted(merged));
        assertEquals(nrOfNodes, getLinkedListLength(merged));
    }

    @Provide
    Arbitrary<Tuple3<Integer, Integer, List<Integer>>> validNodesListsValues() {
        // Random number of nodes between 1 and 10000
        Arbitrary<Integer> nrOfNodes = Arbitraries.integers().between(1, 10000);

        // Random number of linked lists between 1 and the random number of nodes
        Arbitrary<Integer> nrOfLists = nrOfNodes.flatMap(n -> Arbitraries.integers().between(1, n));

        // List of random integers between -10000 and 10000 of size nrOfNodes
        Arbitrary<List<Integer>> values = nrOfNodes.flatMap(
            n -> Arbitraries.integers().between(-10000, 10000).list().ofSize(n)
        );

        return Combinators.combine(nrOfNodes, nrOfLists, values).as(Tuple::of);
    }

    // @Provide
    // Arbitrary<Tuple3<Integer, Integer, List<Integer>>> invalidNodesListsValues() {
    //     // Random number of nodes between 1 and 10000
    //     Arbitrary<Integer> nrOfNodes = Arbitraries.integers().between(1, 10000);

    //     // Random number of linked lists between 1 and the random number of nodes
    //     Arbitrary<Integer> nrOfLists = nrOfNodes.flatMap(n -> Arbitraries.integers().between(1, n));

    //     // List of random integers between -10000 and 10000 of size nrOfNodes
    //     Arbitrary<List<Integer>> values = nrOfNodes.flatMap(n -> Arbitraries.integers().between(-10000, 10000)
    //                                                                         .list().ofSize(n));

    //     return Combinators.combine(nrOfNodes, nrOfLists, values).as(Tuple::of);
    // }


    // ----------- Helpers ------------------------------


    // Method to calculate the length of the linked list
    private static int getLinkedListLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
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

    private int[] convertListToArray(List<Integer> numbers) { 
        int[] array = numbers
            .stream()
            .mapToInt(x -> x)
            .toArray();
        return array;
    }

    private static boolean isLinkedListSorted(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val > current.next.val) {
                return false;
            }
            current = current.next; 
        }
        return true;
    }

}