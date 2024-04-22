package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

class SortedArrayToBSTTest {

    SortedArrayToBST converter = new SortedArrayToBST();

    @Test
    public void emptyArrayTest(){
        int[] nums = {};
        TreeNode bst = converter.sortedArrayToBST(nums);
        List<Integer> traversalResult = converter.levelOrderTraversal(bst);

        assertTrue(traversalResult.size() == 0);
    }

    @Test
    public void unsortedTest(){
        int[] nums = {0, 2, 1, 3};
        assertThrows(IllegalArgumentException.class, () -> converter.sortedArrayToBST(nums));
    }

    @Test
    public void nullTest(){
        assertThrows(IllegalArgumentException.class, () -> converter.sortedArrayToBST(null));
    }

    @Test
    public void singleInputTest(){
        int[] nums = {42};
        TreeNode bst = converter.sortedArrayToBST(nums);
        List<Integer> traversalResult = converter.levelOrderTraversal(bst);
        assertEquals(nums[0], bst.val);
        assertEquals(nums[0], traversalResult.get(0));
    }

    @Test
    public void tooLargeInputTest(){
        int upperLimit = (int) Math.pow(10, 4);
        int[] nums = new int[upperLimit + 1];

        for (int i = 1; i <= upperLimit + 1; i++) {
            nums[i-1] = i;
        }

        assertThrows(IllegalArgumentException.class, () -> converter.sortedArrayToBST(nums));
    }

    @Test
    public void validLargeInputTest(){
        int upperLimit = (int) Math.pow(10, 4);
        int[] nums = new int[upperLimit];

        for (int i = 1; i <= upperLimit; i++) {
            nums[i-1] = i;
        }

        TreeNode bst = converter.sortedArrayToBST(nums);
        List<Integer> traversalResult = converter.levelOrderTraversal(bst);
        assertEquals(nums.length, traversalResult.size());
    }

    @Test
    public void allInputIsInOutputTest(){
        int[] nums = {-1, 0, 36};
        TreeNode bst = converter.sortedArrayToBST(nums);
        List<Integer> traversalResult = converter.levelOrderTraversal(bst);
        assertTrue(doArraysContainSameValues(nums, convertListToArray(traversalResult)));
    }    

    @Test
    public void isBSTValidTest(){
        int[] nums = {-1, 0, 2, 3, 4, 43, 1243};
        TreeNode bst = converter.sortedArrayToBST(nums);
        assertTrue(isBSTValid(bst).isValid);
    } 
    


    // ----------- Property-based testing ---------------

    @Property
    void validInputValidBSTTest(
        @ForAll @Size(max = 10000) Set<@IntRange Integer> numsSet
        ) {
        ArrayList<Integer> numsList = new ArrayList<>(numsSet);
        Collections.sort(numsList);
        int[] nums = convertListToArray(numsList);
        TreeNode bst = converter.sortedArrayToBST(nums);
        assertTrue(isBSTValid(bst).isValid);
    }

    @Property
    void outputValuesEqualInputValuesTest(
        @ForAll @Size(max = 10000) Set<Integer> numsSet
        ) {
        ArrayList<Integer> numsList = new ArrayList<>(numsSet);
        Collections.sort(numsList);
        int[] nums = convertListToArray(numsList);
        TreeNode bst = converter.sortedArrayToBST(nums);
        List<Integer> traversalResult = converter.levelOrderTraversal(bst);
        assertTrue(doArraysContainSameValues(nums, convertListToArray(traversalResult)));
    }


    // Helpers --------------------------------------

    private int[] convertListToArray(List<Integer> numbers) { 
        int[] array = numbers
            .stream()
            .mapToInt(x -> x)
            .toArray();
        return array;
    }

    private static boolean doArraysContainSameValues(int[] source, int[] destination) {
        Set<Integer> sourceSet = new HashSet<>();
        Set<Integer> destinationSet = new HashSet<>();

        for (int num : source) {
            sourceSet.add(num);
        }

        for (int num : destination) {
            destinationSet.add(num);
        }

        return destinationSet.containsAll(sourceSet);
    }


    private Result isBSTValid(TreeNode node) {
        if (node == null) {
            return new Result(true, -1); // Empty trees are balanced and height -1
        }

        // Check recursively if the left and right subtrees are balanced
        Result leftResult = isBSTValid(node.left);
        Result rightResult = isBSTValid(node.right);

        // Check if current node's children's values are valid and if the tree is balanced
        boolean isBstValid = leftResult.isValid && rightResult.isValid &&
                             Math.abs(leftResult.height - rightResult.height) <= 1 &&
                             (node.left == null || node.left.val < node.val) &&
                             (node.right == null || node.right.val > node.val);

        // Calculate the current node's height
        int height = 1 + Math.max(leftResult.height, rightResult.height);

        return new Result(isBstValid, height);
    }

    // Helper class to store the result of subtree checks
    class Result {
        boolean isValid;
        int height;

        Result(boolean isValid, int height) {
            this.isValid = isValid;
            this.height = height;
        }
    }

 
}