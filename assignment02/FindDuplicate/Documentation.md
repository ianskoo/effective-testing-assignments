Task 1:
To achieve maximum line coverage in this example it is essentially enough to test one case without cycles and one case with a cycle

Task 2:
From the constraints we can design the following contracts:

pre-conditions:
1 list of edge pairs: every list in list must have size 2
2 numCourses is a positive integer
4 each element in list needs to be 0 <= a,b < numCourses
3 no element in list can be equal

which gives us 4 additional test cases to make sure the pre-conditions hold up
since we don't alter the values we get, there is not really a need for invariants here. Also since java enforces return types, there is also no need to check whether a boolean was returned or not

Task 3:

This gives us 4 tests to test each pre-condition. one with a prerequisite with 3 integers, one with numCourses as a negative integer, one with elements that are bigger or equal to numCourses and one with a prerequisite that has the same number.

Task 4:

we check the property that if we have a range of numbers, then add one number that is a duplicate our method should always return this duplicate number

Task 1:
To achieve maximum coverage it is enough if we come up with a few "standard" cases. This means one number is duplicate once, or one number appears more than 2 times.

Task 2:
from the constraints we come up with the following contracts:

pre-condition:
the biggest number in the array has to be less than array.length
nums cannot be null
nums.length has to be at least 2
each number has to be between 0 and array.length -1

post-condition:
return type must be an integer
return value must be between 1 and array.length -1

Task 4:
Since it is hard to generate directed graphs without cycles (without being prone to errors in implementation) we generate a random list of edges, and add a cycle to it to check if the cycle is recognized.