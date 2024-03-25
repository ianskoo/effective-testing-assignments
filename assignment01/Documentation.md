
## Palindrome

### Specification testing

#### 1. Understanding the requirements

Quite straightforward. We have an integer as input and check whether it is a palindrome integer, returning true if so, and false otherwise. There is also the constraint that the input integer has to be between $-2^{20}$ and $2^{20} - 1$. The examples also show how negative numbers are automatically not palindromes due to the minus sign.

In Python, this program would be as simple as: `str(x) == str(x)[::-1]`

#### 2. Exploring the program 

The first implementation is very intuitive to me. It converts the integer into a string and iterates it from start to end and from end to start simultaneously, checking whether each iterated character is the same. The second implementation seems a bit messy and thus hard to understand. It tries to assess the "palindromity" of the integer without conversion using some mathematic principles.

#### 3. Analyze properties of inputs and outputs and find partitions

* Input: `int x`, $-2^{20} <= x <= 2^{20} - 1$
* Output type: `Bool`

From the requirements and the only input's type (int), we can at least find the following partitions:

| partition | expected output |
| --- | --- |
| `type(x) != int` | `null` |
| $x$ is `null`: |`null` (unspecified)|
| $x \in [-2^{20}, 2^{20}-1]$ | ? |
| $x = 0$ | `true` |
| $x = 1$ | `true` | 
| $x = 121$ | `true` | 
| $x = 123$ | `false` | 
| $x = -1$ | `false` | 
| $x = -121$ | `false` | 
| $x \notin [-2^{20}, 2^{20}-1]$ | `null` | 
| $x = -2^{20}-1$ | `null` | 
| $x = 2^{20}$ | `null` | 

On the other hand, finding all partitions of this function that make the return value change seems hard, as the numbers that return true are specific and sparse. It's also not clear to me whether they might follow some closed mathematical formula or not.


#### 4. Analyze the boundaries

Some on- and off-points are already in the shortlist created to test the partitions. We take partition $x \in [0,9]$ as example of positive numbers going from palindrome to non-palindrome.

| partition | on-point | off-point |
| --- | --- | --- | 
| $x \geq -2^{20}$ | $-2^{20}$ | $-2^{20}-1$ |
| $x \leq 2^{20}-1$ | $2^{20}-1$ | $2^{20}$ |
| $x < 0$ | -1 | 0 |
| $x \in [0,9]$ | 9 | 10 |


#### 5. Devise test cases

As the function only has one input, we don't have to combine the partitions. We will thus devise a parametrized test with all of the above on- and off-points and exceptional values (int, not int, null) once.

#### 6. Implement the automated test cases

See implementation.

#### 7. Use creativity and experience to enhance the test suite

I can't see additional meaningful specification tests. 


### Structural testing

<!-- Use Jacoco. Describe which conditions, branches, or lines did you miss with specification-based testing (if any), and what tests did you add to cover them -->



### Mutation testing


### Bugs found

#### Specification testing

Both PalindromeOne and Two were happy to return a result (false) by passing integers outside the specified range $x \in [-2^{20}, 2^{20}-1]$. They now throw an IllegalArgumentException. 



### ChatGPT prompts

* What's the simplest way to do automated testing with junit5 on linux?
* syntax for parametrized test junit5
* Unrecognised tag: 'dependency'
* How do I add jacoco to the pom.xml



# GenerateParentheses

### Specification testing

#### 1. Understanding the requirements
The general idea of the class is easy understandable. The method should generate all combinations of well-formed parentheses. And the only input is the amount of parentheses.
#### 2. Exploring the program 
Like this I looked at all the possible outputs the program should be able to produce:

    @Test
    void testRun() {
        for (int i = 1; i <= 8; i++) {
            List<String> result = GenerateParentheses.generateParentheses(i);
            System.out.println("n = " + i + ", size: " +  result.size() + ": " + result);
        }
    }


#### 3. Analyze properties of inputs and outputs and find partitions
There are not many partitions to identify in this step. There is only a single input. Therefore, there are also no variable Interactions. And the individual range of n is as stated: 1 <= n <= 8. 

#### 4. Analyze the boundaries
The boundaries of the parameter are 1 and 8. However i dont think that focusing on the boundaires is super important in this case as size is very straight forward and eight is alread very long and complicated. I think it makes more sense to identifying a pattern and see if the outputs are complete 
#### 5. Devise test cases
One test i have though of was to test for null. However the method already expects an int so therefore this can be skipped.
#### 6. Implement the automated test cases
I implemented test cases that check if the the output for n=1, 2 or 3 is correct. Also i check for numbers above 9.
Because of the assertThrows for numbers above 8 i added this snipped in the code which throws an error

    if (n > 8) {
        throw new IllegalArgumentException("Input must be below 9");
    }

#### 7. Use creativity and experience to enhance the test suite
After doing some research I found out that the amount of possible combinations should always be equal to the n-th Catalan number of the input.
Therefore I created a helper function to calculate the catalan number.
In a second step i create a test suite that runs throug inputs 1-8 and then checks if the size is equal to the calculated catalan number.
### Structural testing
THe only line which is not covered is the first line which should not be a problem as it is the class initialization 
![img.png](img.png)
Becuase all important lines were covered i did not have to add any new tests
### Mutation testing
All mutant were killed by my tests

>> Line Coverage: 18/19 (95%)
>> Generated 19 mutations Killed 19 (100%)
>> Mutations with no coverage 0. Test strength 100%
>> Ran 23 tests (1.21 tests per mutation)

## ChatGPT prompts
-What is the catalan number and how do I calculate it?
-Various Prompts were used to be able to install jacoco and pitest correctly


