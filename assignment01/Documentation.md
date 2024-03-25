
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



## ATOI

### Specification testing

#### 1. Understanding the requirements


#### 2. Exploring the program 


#### 3. Analyze properties of inputs and outputs and find partitions



| partition | expected output |
| --- | --- |



#### 4. Analyze the boundaries

| partition | on-point | off-point |
| --- | --- | --- | 


#### 5. Devise test cases


#### 6. Implement the automated test cases


#### 7. Use creativity and experience to enhance the test suite


### Structural testing

### Mutation testing