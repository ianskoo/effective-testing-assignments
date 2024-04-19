package zest;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int inputNodeCount = 0;
        
        for (ListNode node : lists) {
            if (node != null) {
                // Traverse all nodes in each list to check pre-conditions
                ListNode current = node;
                while (current != null) {
                    inputNodeCount++;
                    // The linked lists of the input array must be sorted in ascending order
                    if (current.next != null && current.val > current.next.val) {
                        throw new IllegalArgumentException("The linked lists of the input array must "+
                                                           "be sorted in ascending order");
                    }
        
                    // The values in the linked lists must be in the range [-10^4, 10^4]
                    if (current.val < -Math.pow(10, 4) || current.val > Math.pow(10, 4)) {
                        throw new IllegalArgumentException("A value in the provided lists is outside"+
                                                           " the allowed range [-10^4, 10^4]: " + current.val);
                    }
                    current = current.next;
                }
                // The total number of nodes of the input linked lists must not exceed 10^4
                if (inputNodeCount > Math.pow(10, 4)) {
                    throw new IllegalArgumentException("The total number of nodes of "+
                    "all linked lists cannot exceed 10^4.");
                }
                // Else, add it to the queue
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null) {
                queue.add(tail.next);
            }
        }

        // Traverse all nodes in the output list to check post-conditions
        ListNode current = dummy.next;
        int outputNodeCount = 0;
        while (current != null) {
            outputNodeCount++;
            
            // 1. The output linked list must be sorted in ascending order
            if (current.next != null && current.val > current.next.val) {
                throw new IllegalStateException("The linked lists of the input array must "+
                "be sorted in ascending order.");
            }
            current = current.next;
        }
        // 2. The output linked list must have the same number of nodes as the input linked lists
        if (inputNodeCount != outputNodeCount) {
            throw new IllegalStateException("The output linked list must have the same number "+
            "of nodes as the input linked lists.");
        }
        
        return dummy.next;
    }
}
