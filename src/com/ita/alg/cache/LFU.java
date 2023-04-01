package com.ita.alg.cache;

import com.ita.alg.util.ArrayUtil;

import java.util.*;

public class LFU {
    Map<Integer, BiListNode> storage;
    Map<Integer, FreqStruct> freq;
    int capacity;
    int size;

    public LFU(int k) {
        storage = new HashMap<>();
        freq = new HashMap<>();
        capacity = k;
        size = 0;
    }

    public int get(int key) {
        // write code here
        if (!storage.containsKey(key)) {
            return -1;
        }

        BiListNode node = storage.get(key);
        FreqStruct fs = freq.get(node.freq);
        rmFromList(node);
        if (fs.isEmpty()) {
            freq.remove(node.freq);
        }
        node.freq = node.freq + 1;
        if (!freq.containsKey(node.freq)) {
            freq.put(node.freq, new FreqStruct(node.freq));
        }
        freq.get(node.freq).insertToHead(node);
        return node.val;
    }

    private void rmFromList(BiListNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
    }


    public void set(int key, int value) {
        if (storage.containsKey(key)) {
            BiListNode node = storage.get(key);
            node.val = value;
            get(key);
            return;
        }

        BiListNode node = new BiListNode();
        node.key = key;
        node.val = value;
        node.freq = 1;
        storage.put(key, node);
        size++;
        if (size > capacity) {
            OptionalInt minFreq = freq.entrySet().stream().mapToInt(entry -> entry.getValue().isEmpty() ? Integer.MAX_VALUE : entry.getKey()).min();
            assert minFreq.isPresent();
            int rvdKey = freq.get(minFreq.getAsInt()).rmTail();
            storage.remove(rvdKey);
        }
        if (!freq.containsKey(node.freq)) {
            freq.put(node.freq, new FreqStruct(node.freq));
        }
        freq.get(node.freq).insertToHead(node);
    }
}

class FreqStruct {
    int freq;
    BiListNode head;
    BiListNode tail;

    public FreqStruct(int freq) {
        this.freq = freq;
        head = new BiListNode();
        tail = new BiListNode();
        head.next = tail;
        tail.prev = head;
    }

    boolean isEmpty() {
        return head.next == tail;
    }

    void insertToHead(BiListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    int rmTail() {
        BiListNode delNode = tail.prev;
        if (delNode == head) {
            return -1;
        }
        delNode.prev.next = delNode.next;
        delNode.next.prev = delNode.prev;
        delNode.next = null;
        delNode.prev = null;
        return delNode.key;
    }
}
