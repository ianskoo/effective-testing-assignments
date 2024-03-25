# Documentation CombinationSum

## Understanding the requirements, inputs and outputs

### What must the program do:
The goal of the method is finding all the unique combinations of numbers from an array that sum up to a specific integer.

### Input:
Two parameters:

candidates, the array containing distinct integers which have to be combined

target, the integer to which the combinations have to sum up to

### Output:
The program returns a list of lists containing the different combinations of Integers found by the porgram.

## Explore possible inputs/outputs and find partitions
One point which directly came to mind, are negative values. In the parameters The specifications only talk about integers so there have to be tests for negative values aswell. From the specifications it is also clear that we need to check if the number of combinations can exceed 150.

### Input: candidates parameter
- Null
- Empty array
- Array of length 1
- Array of length > 1
- contains negatives
### Input: target parameter
- Null
- target < 0
- target = 0
- target > 0
### Input: combination
- large candidates array and large value for target
- candidates contains one int matching target
- candidates contains one int not matching target
### Outputs
- Empty list
- List containing exactly one list
- List containing multiple lists
- Null 

## Devise the test cases 
The examples given in the specification are used as tests. In addition to them we create tests for the given partitions.

The current implementation breaks for negative values. The null input test fails too as well as the test for the max of 150 combinations. 



