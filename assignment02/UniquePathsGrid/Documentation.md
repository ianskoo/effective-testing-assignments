# UniquePaths
The following description is given: A robot is located in the top-left corner of a m x n grid. The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid. How many possible unique paths are there?

## Task 1
By adding the given examples to the testsuite we achieve 100% line coverage:
![img.png](Assets/LineCoverage.png)
![img.png](Assets/LineCoverage2.png)

## Task 2
**pre-condition**: Input values m and n are in the range [1, 100].

**post-condition**: The final value returned by the function is non-negative.

**invariant**: For any cell in the grid, the value of that cell represents the unique paths to reach that cell from the top left corner using only downward or rightward moves.

## Task 3
The three example tests which already have been implemented validate normal operation when the pre-condition is met. To test if appropriate errors are thrown when the pre-condition is violated we added two tests 'preConditionViolated'. Two additional edge case tests were added to evaluate the minimum and maximum size grids. Through these tests, we discovered that the resulting value overflows for 32-bit signed integers in large grids. To address this issue, one could either change the type of dp and the returned value to double or implement a check that returns Integer.MAX_VALUE when this limit is reached.

## Task 4
One property which should always hold for this problem is that the result should not change when swapping m and n. To test this we added the testSymmetry Property test.

Another property which must hold is consistency. This means that the results for the same input values m and n should not change. See the testConsistency Property test.

To ensure the effectiveness of property testing, we restricted the input range using the @Provide annotation with arbitrary integers within the range [1, 17]. Expanding this range would lead to violations of the post-condition. Therefore, if we intend to reliably apply property testing within the range [1, 100], we would need to modify both the type of dp and the return type of the uniquePaths function to double.
