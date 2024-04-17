
# Testing documentation - MergeKSortedLists

<!-- ## Specification testing

### 1. Understanding the requirements

### 2. Exploring the program 

### 3. Analyze properties of inputs and outputs and find partitions

| partition | expected output |
| --- | --- |
| `type(x) != int` | `null` |
| $x$ is `null`: |`null` (unspecified)|

### 4. Analyze the boundaries

### 5. Devise test cases

### 6. Implement the automated test cases

See implementation.

### 7. Use creativity and experience to enhance the test suite -->

## Code Coverage


<!-- Use Jacoco. Describe which conditions, branches, or lines did you miss with specification-based testing (if any), and what tests did you add to cover them -->

In PalindromeOne, we missed one condition in the following `if` statement:
```
while (start < end) {
    if (numbers[start] != numbers[end]) return false;
    start++;
    end--;
``` 


### Bugs found

1. The program does not check the required constraints. Given two linked lists with 10^4 nodes in the first one, it returns a non-empty merged list.


### Specification testing

Both PalindromeOne and Two were happy to return a result (false) by passing integers outside the specified range $x \in [-2^{20}, 2^{20}-1]$. They now throw an IllegalArgumentException. 




## LLM prompts

### Q1 

How do I instantiate an array of linked list objects defined as follows, and populate the first list with N times some integer?
```
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```

#### Answer

 To instantiate an array of `ListNode` objects, where each element in the array is the head of a linked list, and then populate the first linked list with \(N\) times some integer \(M\), you can follow these steps in Java. This will involve creating the array and then populating the first list as specified.

Here is a step-by-step guide and the code to accomplish this:

##### Step 1: Create the Array of ListNode

You declare an array of `ListNode` objects. The size of the array depends on how many linked list heads you want to store.

```java
ListNode[] listNodes = new ListNode[10]; // Example: array of 10 linked list heads.
```

##### Step 2: Populate the First List

To populate the first list in the array with \(N\) times some integer \(M\), you can create a loop that generates \(N\) new `ListNode` objects, each initialized with the value \(M\), and link them together.

```java
int N = 5;  // Example: Number of nodes
int M = 3;  // Example: Value to be repeated

// Check if there's at least one list in the array
if (listNodes.length > 0) {
    ListNode head = new ListNode(M); // Create the head node with value M
    ListNode current = head; // Use current to traverse and build the list
    for (int i = 1; i < N; i++) { // Start from 1 because the head is already created
        current.next = new ListNode(M); // Create new node with value M and link it
        current = current.next; // Move to the new node
    }
    listNodes[0] = head; // Assign the populated list as the first element in the array
}
```

##### Explanation:

1. **Array Initialization**: An array of `ListNode` is created. Initially, all entries are `null`.
2. **List Population**: If the length of the array is positive, we initialize the first linked list. The head of the list is created and filled with the value \(M\). A loop runs \(N-1\) more times (since the head is already created) to add \(N-1\) more nodes, each with the value \(M\).
3. **Linking Nodes**: Each new node is linked to the previous node through the `next` pointer.
4. **Storing Head**: The head of the newly created list is stored in the first position of the array.

This setup will create a single linked list with \(N\) nodes, all containing the value \(M\), and store the head of this list in the first slot of an array of `ListNode`. The rest of the array remains filled with `null` unless further populated. This approach is modular and can be expanded to populate more lists in the array in a similar fashion.

