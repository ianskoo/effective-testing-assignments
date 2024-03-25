
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
| $x = -1$ | `false` | 
| $x = -121$ | `false` | 
| $x \notin [-2^{20}, 2^{20}-1]$ | `null` | 
| $x = -2^{20}-1$ | `null` | 
| $x = 2^{20}$ | `null` | 


#### 4. Analyze the boundaries

Finding all the boundaries of this function seems hard, as the numbers that return true are specific and sparse. It's also not clear to me whether they might follow some closed mathematical formula or not.

| partition | on-points | off-points |
| --- | --- | --- | 
| $x \in [-2^{20}, 2^{20}-1]$ | 
| $x \notin [-2^{20}, 2^{20}-1]$ | $-2^{20}, 2^{20}-1$ | $-2^{20}-1, 2^{20}$
| $x$ is `null`: |
| $x < 0$ |
| $x \in [0,9]$ | 


#### 5. Devise test cases


#### 6. Implement the automated test cases


#### 7. Use creativity and experience to enhance the test suite


### Structural testing



### Mutation testing




## ChatGPT prompts

* What's the simplest way to do automated testing with junit5 on linux?