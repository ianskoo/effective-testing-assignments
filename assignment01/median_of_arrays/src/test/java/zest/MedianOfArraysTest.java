package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianOfArraysTest {
    MedianOfArrays medianOfArrays = new MedianOfArrays();
    @Test
    void testNullArrays() {
        int[] nonNullArray = {1, 2};

        assertEquals(0.0, medianOfArrays.findMedianSortedArrays(null, nonNullArray));
        assertEquals(0.0, medianOfArrays.findMedianSortedArrays(nonNullArray, null));
        assertEquals(0.0, medianOfArrays.findMedianSortedArrays(null, null));
    }

    @Test
    void testNonSortedArray() {
        int[] sortedArray = {1, 2, 3, 4};
        int[] nonSortedArray = {4, 3, 2, 1};
        assertEquals(0.0, medianOfArrays.findMedianSortedArrays(sortedArray, nonSortedArray));
        assertEquals(0.0, medianOfArrays.findMedianSortedArrays(nonSortedArray, sortedArray));
        assertEquals(0.0, medianOfArrays.findMedianSortedArrays(nonSortedArray,nonSortedArray));
    }

    @Test
    void oneElementArray() {
        int[] oneElem = {1};
        int[] multipleElem = {5,6};
        assertEquals(5, medianOfArrays.findMedianSortedArrays(oneElem, multipleElem));
        assertEquals(5, medianOfArrays.findMedianSortedArrays(multipleElem, oneElem));
        assertEquals(1, medianOfArrays.findMedianSortedArrays(oneElem, oneElem));
    }

    @Test
    void emptyArray() {
        int[] empty = {};
        int[] nonEmpty = {1,2,3};
        assertEquals(2, medianOfArrays.findMedianSortedArrays(empty, nonEmpty));
        assertEquals(2, medianOfArrays.findMedianSortedArrays(nonEmpty, empty));
        assertEquals(-1, medianOfArrays.findMedianSortedArrays(empty, empty));
    }

    @Test
    void evenAmountOfNumbers() {
        int[] even = {2, 5};
        assertEquals(3.5, medianOfArrays.findMedianSortedArrays(even, even));
    }

    @Test
    void oddAmountOfNumbers() {
        int[] even = {2, 4};
        int[] odd = {1, 3, 5};
        assertEquals(3, medianOfArrays.findMedianSortedArrays(odd, even));
    }
}