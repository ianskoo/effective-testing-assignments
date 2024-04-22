package zest;

public class FindDuplicate {
    public static int findDuplicate(int[] nums) {

        //check pre-conditions
        assert nums != null : "nums cannot be null";
        assert nums.length >= 2 : "nums mus be bigger than 2";
        for (int num : nums) {
            assert num < nums.length : "no number can be equal or bigger than length";
            assert num > 0 : "numbers have to be bigger than 0";

        }

        int tortoise = nums[0];
        int hare = nums[0];
        // Phase 1: Finding the intersection point of the two runners.
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Phase 2: Finding the "entrance" to the cycle.
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        assert hare > 0 && hare < nums.length : "return value not in range";
        return hare;
    }
}
