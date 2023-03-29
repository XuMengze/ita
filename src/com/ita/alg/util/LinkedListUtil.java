package com.ita.alg.util;

import com.ita.alg.model.LinkedListNode;
import com.ita.alg.model.ListNode;
import com.ita.alg.model.TreeNode;

import java.util.*;

public class LinkedListUtil<T> {
    public static int[] marshalWithVal(ListNode head) {
        List<Integer> l = new ArrayList<>();
        while (head != null) {
            l.add(head.val);
            head = head.next;
        }
        return ArrayUtil.toBasicArray(l.toArray(new Integer[0]));
    }

    public static ListNode unmarshalWithVal(int[] nodes) {
        ListNode dummy = new ListNode(-1);
        ListNode start = dummy;
        for (int i : nodes) {
            start.next = new ListNode(i);
            start = start.next;
        }
        return dummy.next;
    }

    public static void printListNode(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        System.out.println(sb);
    }

    public LinkedListNode<T> reverse(LinkedListNode<T> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        LinkedListNode<T> start = head.getNext();
        head.setNext(null);
        while (start != null) {
            LinkedListNode<T> tmp = start.getNext();
            start.setNext(head);
            head = start;
            start = tmp;
        }
        return head;
    }

    public LinkedListNode<T> oddEvenList(LinkedListNode<T> head) {
        // write code here
        LinkedListNode<T> odd = new LinkedListNode<>(), even = new LinkedListNode<>();
        LinkedListNode<T> oddTail = odd, evenTail = even;
        int index = 1;
        while (head != null) {
            LinkedListNode<T> thisNode = head;
            head = head.getNext();
            thisNode.setNext(null);
            if (index++ % 2 == 1) {
                oddTail.setNext(thisNode);
                oddTail = oddTail.getNext();
            } else {
                evenTail.setNext(thisNode);
                evenTail = evenTail.getNext();
            }
        }
        oddTail.setNext(even.getNext());
        return odd.getNext();
    }

    public ListNode sortInList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode traverse = head;
        List<Integer> tmp = new ArrayList<>();
        while (traverse != null) {
            tmp.add(traverse.val);
            traverse = traverse.next;
        }
        tmp.sort((o1, o2) -> o1 < o2 ? -1 : 1);
        int index = 0;
        traverse = head;
        while (traverse != null) {
            traverse.val = tmp.get(index++);
            traverse = traverse.next;
        }
        return head;
    }

    public ListNode addInList(ListNode head1, ListNode head2) {
        // write code here
        head1 = reverse(head1);
        head2 = reverse(head2);
        ListNode dummy = new ListNode(-1);
        ListNode traverse = dummy;
        int carry = 0;
        while (head1 != null && head2 != null) {
            int trueResult = head1.val + head2.val + carry;
            ListNode digit = new ListNode(trueResult % 10);
            carry = trueResult / 10;
            traverse.next = digit;
            traverse = traverse.next;
            head1 = head1.next;
            head2 = head2.next;
        }
        if (head2 != null) {
            head1 = head2;
        }
        while (head1 != null) {
            int trueResult = head1.val + carry;
            ListNode digit = new ListNode(trueResult % 10);
            carry = trueResult / 10;
            traverse.next = digit;
            traverse = traverse.next;
            head1 = head1.next;
        }

        if (carry != 0) {
            ListNode digit = new ListNode(carry);
            traverse.next = digit;
            traverse = traverse.next;
        }
        return reverse(dummy.next);

    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode start = head.next;
        head.next = null;
        while (start != null) {
            ListNode tmp = start.next;
            start.next = head;
            head = start;
            start = tmp;
        }
        return head;
    }

    public ListNode duplicatesSaveOne(ListNode head) {
        Map<Integer, Boolean> store = new HashMap<>();
        ListNode start = head;
        store.put(head.val, true);
        while (start.next != null) {
            if (store.containsKey(start.next.val)) {
                start.next = start.next.next;
                continue;
            }
            store.put(start.next.val, true);
            start = start.next;

        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        }
        ListNode traverse = head;
        while (traverse.next != null) {
            if (traverse.val != traverse.next.val) {
                break;
            }
            traverse = traverse.next;
        }
        if (traverse.next == null) {
            return null;
        }
        return deleteDuplicates(traverse.next);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // write code here
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null || pHead.next.next == null) {
            return null;
        }
        ListNode fast = pHead, slow = pHead;
        ListNode meet;
        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null) {
                return null;
            }
            if (fast == slow) {
                meet = fast;
                break;
            }
        }
        while (pHead != meet) {
            pHead = pHead.next;
            meet = meet.next;
        }
        return meet;
    }

    private ListNode mergeSortedList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode start = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                start.next = l1;
                l1 = l1.next;
            } else {
                start.next = l2;
                l2 = l2.next;
            }
            start = start.next;
        }
        if (l1 != null) {
            start.next = l1;
        } else {
            start.next = l2;
        }
        return dummy.next;
    }

    private ListNode mergeKLists(ArrayList<ListNode> lists, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return lists.get(left);
        }
        int mid = (left + right) / 2;
        return mergeSortedList(mergeKLists(lists, left, mid), mergeKLists(lists, mid + 1, right));
    }

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        return mergeKLists(lists, 0, lists.size() - 1);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode firstPartEnd = findPreByPosition(dummy, m);
        ListNode secondPartEnd = findPreByPosition(dummy, n).next;
        ListNode thirdPart = secondPartEnd == null ? null : secondPartEnd.next;
        if (secondPartEnd != null) {
            secondPartEnd.next = null;
        }
        firstPartEnd.next = reverse(firstPartEnd.next);
        while (firstPartEnd.next != null) {
            firstPartEnd = firstPartEnd.next;
        }
        firstPartEnd.next = thirdPart;
        return dummy.next;
    }

    // Pre is the node before the node with target value, should be used with dummy ahead
    private ListNode findPreByTarget(ListNode head, int value) {
        while (head.next != null && head.next.val != value) {
            head = head.next;
        }
        if (head.next == null) {
            return null;
        }
        return head;
    }

    // Pre is the node before target position.
    // Unlike array, position starts from 1, should be used with dummy ahead
    private ListNode findPreByPosition(ListNode head, int pos) {
        for (int i = 0; i < pos - 1; i++) {
            if (head.next != null) {
                head = head.next;
            }
        }
        return head;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode start = head;
        while (start != null) {
            start = start.next;
            count++;
        }
        for (int i = 0; i < count / k; i++) {
            head = reverseBetween(head, i * k + 1, (i + 1) * k);
        }
        return head;
    }
}

