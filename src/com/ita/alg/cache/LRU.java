package com.ita.alg.cache;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    int capacity, size;
    Map<Integer, BiListNode> storage;
    BiListNode head, tail;

    public LRU(int capacity) {
        // write code here
        this.capacity = capacity;
        size = 0;
        BiListNode dummyHead = new BiListNode();
        BiListNode dummyTail = new BiListNode();
        head = dummyHead;
        tail = dummyTail;
        head.next = tail;
        tail.prev = head;
        storage = new HashMap<>();
    }

    public int get(int key) {
        // write code here
        if (!storage.containsKey(key)) {
            return -1;
        }
        BiListNode target = storage.get(key);
        moveToHead(target);
        return target.val;
    }

    private void moveToHead(BiListNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
        insertToHead(node);
    }

    private void insertToHead(BiListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void delTail() {
        BiListNode delNode = tail.prev;
        if (delNode == head) {
            return;
        }
        storage.remove(delNode.key);
        size--;
        delNode.prev.next = delNode.next;
        delNode.next.prev = delNode.prev;
        delNode.next = null;
        delNode.prev = null;
    }

    public void set(int key, int value) {
        // write code here
        if (storage.containsKey(key)) {
            BiListNode node = storage.get(key);
            node.val = value;
            moveToHead(node);
        } else {
            BiListNode node = new BiListNode();
            node.val = value;
            node.key = key;
            insertToHead(node);
            size++;
            storage.put(key, node);
            if (size > capacity) {
                delTail();
            }
        }
    }
}

class BiListNode {
    BiListNode prev, next;
    int key;
    int val;
    int freq;
}
