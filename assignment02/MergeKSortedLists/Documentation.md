
# Testing documentation - MergeKSortedLists

## Specification testing

### Understanding the requirements

Given a list of linked lists containing integers in range [-10^4, 10^4], return a single sorted linked list. The total number of nodes of all linked lists cannot exceed 10^4.

### Analyze properties of inputs and outputs and find partitions

Single input: Array of linked lists, with nodes defined as:

```
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```

| partition | expected output |
| --- | --- |
| empty array | empty array |
| null | null |
| # nodes <= 10^4, values $\in [-10^4, 10^4]$ | merged sorted linked list |
| # nodes > 10^4 | error or null |
| some value $\notin [-10^4, 10^4]$ | error or null |


### Analyze the boundaries

| partition | on-point | off-point |
| --- | --- | --- | 
| # nodes <= 10^4, values $\in [-10^4, 10^4]$ | 10^4 | 10^4 + 1 |
| some value $\notin [-10^4, 10^4]$ | -10^4 - 1 | 10^4 + 1 |

### Devise test cases

No combinations of partitions needed, as there only is one input.

| input | expected output |
|--|--|
| [] | [] |
| null | null |
| [[1, 2, 3], \[2\]] | [1, 2, 2, 3] |
| [\[3\] * 10^4+1]] | error |
| [[10^4 + 1]] | error |
| [[-10^4 - 1]] | error |


## Code Coverage

<!-- Use Jacoco. Describe which conditions, branches, or lines did you miss with specification-based testing (if any), and what tests did you add to cover them -->


### Bugs found

1. The program does not check the required constraints. Given a linked list with 10^4+1 nodes, it returns a non-empty merged list.


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


### Q2

Explain this structure:
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

The `ListNode` class you provided is a fundamental data structure often used in the context of linked lists in programming. Each instance of `ListNode` represents an individual node in a linked list. Here’s a breakdown of each component and how they collectively form a usable structure for linked lists:

##### Class Structure

1. **Fields:**
   - `int val`: This field stores the integer value of the node. It represents the data held by the node.
   - `ListNode next`: This field is a reference (or pointer) to the next node in the linked list. It links one node to another, forming a chain of nodes.

2. **Constructors:**
   - `ListNode() {}`: This is a default constructor. It initializes a `ListNode` object without setting any initial values. The `val` field will default to `0` (the default value for integers in Java), and `next` will default to `null`, indicating that the node does not point to any subsequent node initially.
   - `ListNode(int val) { this.val = val; }`: This constructor initializes the node with a specified value for `val`. The `next` field is implicitly set to `null`, meaning this node does not yet link to any other node.
   - `ListNode(int val, ListNode next) { this.val = val; this.next = next; }`: This constructor initializes a node with both a value and a reference to the next node. It allows the creation of a new node that is immediately linked to an existing node chain.

##### Usage in Linked Lists

In the context of linked lists, `ListNode` serves as a building block. A linked list is a series of connected nodes where each node contains a piece of data and a reference to the next node in the sequence. Here’s how `ListNode` facilitates this structure:

- **Start of the List**: The beginning of the list is typically marked by a reference to the first `ListNode` (often called the head). If this reference is `null`, the list is considered empty.
- **Adding Elements**: When new elements are added to the list, a new `ListNode` is created with the element as its value, and it is connected to the rest of the list by adjusting the `next` pointers.
- **Traversal**: To access or print out all elements of the list, you start at the head and follow the `next` references from one node to the next until you reach a node where `next` is `null`, which signifies the end of the list.
- **Insertion and Deletion**: These operations involve changing the `next` references in one or more nodes to add or remove elements without disturbing the other elements in the list.

##### Benefits of Using ListNode in Linked Lists

- **Dynamic Size**: Unlike arrays, linked lists can grow and shrink during runtime by simply adding or removing nodes. There’s no need to define an initial size or reallocate space, as is necessary with arrays.
- **Efficient Insertions/Deletions**: Adding or removing nodes from a linked list can be done efficiently, especially at the beginning or within the list, without the need to shift elements as required in sequential data structures like arrays.

This structure, while simple, is powerful for creating flexible and dynamic data structures that can handle varying amounts of data efficiently.