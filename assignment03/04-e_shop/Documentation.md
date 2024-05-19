# Documentation e-shop testing

### A. Number of invocations
*First, test whether the `onOrderPlaced` method is called as many times as it should.*

For this task, I decided to use a parametrized test with some example values in different partitions for the Order array:

- A normal Order array with three Orders, non-empty IDs, and non-empty amount
- An empty array of Orders
- An array with one Order, with empty ID string and amount 2 --> specification unclear
- An array with one Order, non-empty ID string but amount 0 --> specification unclear

### B. Content of invocations—`ArgumentCaptor`
*Counting the number of invocations may not be enough; additionally, test whether the contents of the order are as expected. Use `ArgumentCaptor` to do this.*

### C. Content of invocations—Increasing observability
*Instead of using `ArgumentCaptor`, you could increase the observability of one or more classes to achieve the same goal.
Implement the necessary code for increasing the observability and write additional test(s) to test whether the content of the messages is as expected.*

### D. Comparison
*What are the advantages and what are the disadvantages of the techniques you used in B. and C.?*