package zest;

public class ClimbingStairs {
    public int climbStairs(int n) {
        // pre condition
        assert n > 0 : "n must be positive";
        if (n <= 2) {
            return n;
        }
        int oneStepBefore = 2;
        int twoStepsBefore = 1;
        int allWays = 0;

        for (int i = 2; i < n; i++) {
            allWays = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = allWays;
            // invariant
            assert invariant(oneStepBefore, twoStepsBefore) : "Invariant does not hold";
        }
        // post condition
        assert allWays >= 0 : "number of ways must be positive";
        return allWays;
    }

    private boolean invariant(int oneStepBefore, int twoStepsBefore) {
        return oneStepBefore >= 0 && twoStepsBefore >= 0;
    }
}
