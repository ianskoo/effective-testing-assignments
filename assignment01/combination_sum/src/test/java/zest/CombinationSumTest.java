package zest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static zest.CombinationSum.*;

import org.junit.jupiter.api.Test;

class CombinationSumTest {
    // Specification Testing

    @Test
    void TestCandidatesIsEmptyList() {
        int[] candidates = {};
        assertEquals(combinationSum(candidates, 2), new ArrayList<List<Integer>>());
    }

    @Test
    void TestOneCandidateMatchingTarget() {
        int[] candidates = { 3 };
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(Arrays.asList(3));
        assertEquals(combinationSum(candidates, 3), res);
    }

    @Test
    void TestExample1() {
        int[] candidates = { 2, 3, 6, 7 };
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(Arrays.asList(2, 2, 3));
        res.add(Arrays.asList(7));
        assertEquals(combinationSum(candidates, 7), res);
    }

    @Test
    void TestExample2() {
        int[] candidates = { 2, 3, 5 };
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(Arrays.asList(2, 2, 2, 2));
        res.add(Arrays.asList(2, 3, 3));
        res.add(Arrays.asList(3, 5));
        assertEquals(combinationSum(candidates, 8), res);
    }

    @Test
    void TestOneCandidateNotMatchingTarget() {
        int[] candidates = { 2 };
        assertEquals(combinationSum(candidates, 1), new ArrayList<List<Integer>>());
    }

    @Test
    void TargetIsZero() {
        int[] candidates = { 1, 2, 3 };
        assertEquals(combinationSum(candidates, 0), new ArrayList<List<Integer>>());
    }

    @Test
    void CandidatesContainsNegatives() {
        int[] candidates = { -1, 2, 3 };
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(Arrays.asList(-1, 2));
        res.add(Arrays.asList(-1, -1, 3));
        assertEquals(combinationSum(candidates, 1), res);
    }

    @Test
    void LargeCandidates() {
        int[] candidates = new int[100];
        for (int i = 1; i <= 100; i++) {
            candidates[i - 1] = i;
        }
        assertTrue(combinationSum(candidates, 100).size() <= 150);
    }

    @Test
    void TestCandidatesIsNull() {
        assertEquals(combinationSum(null, 1), null);
    }

    

}