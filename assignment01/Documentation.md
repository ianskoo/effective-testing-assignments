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

In PalindromeOne, we missed one condition in the following `if` statement:
```
while (start < end) {
    if (numbers[start] != numbers[end]) return false;
    start++;
    end--;
``` 

We added the following parameters for the parameterized test to reach 100% coverage:
```
of(1231, false)
```

In PalindromeTwo, we missed one of four branches in both:
```
if (x < 100 && x % 11 == 0) return true;
if (x < 1000 && ((x / 100) * 10 + x % 10) % 11 == 0) return true;
```
and one out of two in:
```
if (v > x) {
    v /= 10;
}
```

To address this, we added the following parameters for the parameterized test:
```
of(11, true),
of(12, false),
of(242, true),
of(363, true)
```

We couldn't find a value to make condition `if (v > x)` evaluate to false.

### Mutation testing

Results of PItest mutation testing:

<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Line Coverage</th>
            <th>Mutation Coverage</th>
            <th>Test Strength</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><a href="./PalindromeOne.java.html">PalindromeOne.java</a></td>
            <td><div class="coverage_percentage">91% </div><div class="coverage_bar"><div class="coverage_complete width-91"></div><div class="coverage_legend">10/11</div></div></td>
            <td><div class="coverage_percentage">93% </div><div class="coverage_bar"><div class="coverage_complete width-93"></div><div class="coverage_legend">13/14</div></div></td>
            <td><div class="coverage_percentage">93% </div><div class="coverage_bar"><div class="coverage_complete width-93"></div><div class="coverage_legend">13/14</div></div></td>
        </tr>
        <tr>
            <td><a href="./PalindromeTwo.java.html">PalindromeTwo.java</a></td>
            <td><div class="coverage_percentage">93% </div><div class="coverage_bar"><div class="coverage_complete width-93"></div><div class="coverage_legend">14/15</div></div></td>
            <td><div class="coverage_percentage">60% </div><div class="coverage_bar"><div class="coverage_complete width-60"></div><div class="coverage_legend">24/40</div></div></td>
            <td><div class="coverage_percentage">60% </div><div class="coverage_bar"><div class="coverage_complete width-60"></div><div class="coverage_legend">24/40</div></div></td>
        </tr>
     </tbody>
</table>

With palindrome1 having 93\% coverage, and palindrome2 only 60\%. This is due to the latter having a way more complex code, with many more conditions that can break after a mutation. This means that palindrome2 actually has a lot more possible partitions from a structural point of view than palindrome1. Considering that we would not have implemented the palindrome function like in palindrome2, as we deem it hard to understand and very bug prone (bugs love boundaries), we decided not to come up with many additional partitions for it.


### Bugs found

#### Specification testing

Both PalindromeOne and Two were happy to return a result (false) by passing integers outside the specified range $x \in [-2^{20}, 2^{20}-1]$. They now throw an IllegalArgumentException. 




### LLM prompts

For palindrome, we refrained from asking testing-related questions to an LLM. The only questions asked were about the configuration of Maven, which gave as a lot of trouble: 

* What's the simplest way to do automated testing with junit5 on linux?
* syntax for parametrized test junit5
* Unrecognised tag: 'dependency'
* How do I add jacoco to the pom.xml
* How do I make sure jacoco runs with `mvn test`? It is not producing a report. this is my pom.xml: [...]



## GenerateParentheses

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
![img.png](img.png) (can be seen in the generate_parentheses folder)
Becuase all important lines were covered i did not have to add any new tests
### Mutation testing
All mutant were killed by my tests

>> Line Coverage: 18/19 (95%)
>> Generated 19 mutations Killed 19 (100%)
>> Mutations with no coverage 0. Test strength 100%
>> Ran 23 tests (1.21 tests per mutation)

### ChatGPT prompts
-What is the catalan number and how do I calculate it?
-Various Prompts were used to be able to install jacoco and pitest correctly


# frac2vec

### Specification testing

#### 1. Understanding the requirements
Its also straight forward. Convert a fraction into a string format. And if theres a repeating part it should be enclosed.
#### 2. Exploring the program
I realized there are different scenarios: positive and negative fractions, zero numerator, non-repeating, and repeating decimals.
#### 3. Analyze properties of inputs and outputs and find partitions
Inputs:
Positive and negative values for numerator and denominator.
Zero numerator.
Denominator as zero (invalid scenario).
Fractions resulting in finite decimals.
Fractions resulting in repeating decimals.

Outputs:
A string of a non-repeating decimal.
A string of a of a repeating decimal with parentheses around the repeating part.
"0" if the numerator is zero.
null if the denominator is zero.
#### 4. Analyze the boundaries
going from non-repeating to repeating decimals (e.g., 1/2 vs. 1/3).
Small fractions vs. large ones.

#### 5. Devise test cases
I added this line to return null if theres a division by zero
        if (denominator == 0) return null;
#### 6. Implement the automated test cases

#### 7. Use creativity and experience to enhance the test suite

### Structural testing
I only missed the class initialization which is not a problem
And only one branch on line 13

![img.png](img.png) check in the folder
### Mutation testing
- Statistics
  ================================================================================
>> Line Coverage: 24/25 (96%)
>> Generated 20 mutations Killed 18 (90%)
>> Mutations with no coverage 0. Test strength 90%
>> Ran 22 tests (1.1 tests per mutation)

## ChatGPT prompts
- can you help me find missing properties inputs and outputs
