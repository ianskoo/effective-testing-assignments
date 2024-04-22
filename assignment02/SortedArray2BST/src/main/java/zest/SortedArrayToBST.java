package zest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class TreeNode {
    int val;
    zest.TreeNode left;
    zest.TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SortedArrayToBST {

    public zest.TreeNode sortedArrayToBST(int[] nums) {
        // Check if input is null
        if (nums == null) {throw new IllegalArgumentException("Input array cannot be null");}

        // Check if array has legal length
        if (nums.length > Math.pow(10, 4)) {
            throw new IllegalArgumentException("Input array must not be longer than 10^4");
        }

        // Check if array is sorted
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                throw new IllegalArgumentException("Input array must be sorted ascendingly"); 
            }
        }

        TreeNode result = constructBSTRecursive(nums, 0, nums.length - 1);
        
        // Check if input and output contain the same elements
        if (!doArraysContainSameValues(nums, convertListToArray(levelOrderTraversal(result)))){
            throw new RuntimeException("Invariant breached: Input and output values are not the same!");
        }

        // Check if the BST is valid and balanced
        if (!isBSTValid(result).isValid) {
            throw new RuntimeException("Post-condition breached: BST is invalid.");
        }

        return result;
    }

    private zest.TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        zest.TreeNode node = new zest.TreeNode(nums[mid]);
        node.left = constructBSTRecursive(nums, left, mid - 1);
        node.right = constructBSTRecursive(nums, mid + 1, right);
        return node;
    }

    public List<Integer> levelOrderTraversal(zest.TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<zest.TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            zest.TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }


    // Contract helpers -----------------------------------

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
