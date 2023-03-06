package com.ita.alg.dict;

import com.ita.alg.model.LinkedListNode;

public class MyDict<K, V> {
    private LinkedListNode<Entry<K, V>>[] dict;
    private int sizeBase = 2;
    private int originalSetSizeBase = 2;

    private int entryCount;

    public MyDict() {
        dict = new LinkedListNode[2 << sizeBase];
    }

    public MyDict(int sizeBase) {
        this.sizeBase = sizeBase;
        originalSetSizeBase = sizeBase;
        dict = new LinkedListNode[2 << sizeBase];
    }

    public void put(K key, V value) {
        int hashCode = hash(key);
        if (dict[hashCode] == null) {
            dict[hashCode] = newNode(key, value);
            changeBase(true);
            return;
        }
        LinkedListNode<Entry<K, V>> start = dict[hashCode];
        while (start != null) {
            if (start.getVal().key.equals(key)) {
                start.setVal(new Entry<>(key, value));
                return;
            }
            start = start.getNext();
        }
        LinkedListNode<Entry<K, V>> node = newNode(key, value);
        node.setNext(dict[hashCode]);
        dict[hashCode] = node;
        changeBase(true);
    }

    private LinkedListNode<Entry<K, V>> newNode(K key, V value) {
        LinkedListNode<Entry<K, V>> res = new LinkedListNode<>();
        res.setVal(new Entry<>(key, value));
        return res;
    }

    public V get(K key) {
        int hashCode = hash(key);
        if (dict[hashCode] == null) {
            return null;
        }
        LinkedListNode<Entry<K, V>> start = dict[hashCode];
        while (start != null) {
            if (start.getVal().key.equals(key)) {
                return start.getVal().value;
            }
            start = start.getNext();
        }
        return null;
    }

    public void delete(K key) {
        int hashCode = hash(key);
        if (dict[hashCode] == null) {
            return;
        }
        LinkedListNode<Entry<K, V>> start = dict[hashCode];
        if (start.getVal().key.equals(key)) {
            dict[hashCode] = dict[hashCode].getNext();
            changeBase(false);
            return;
        }
        while (start.getNext() != null) {
            if (start.getNext().getVal().key.equals(key)) {
                start.setNext(start.getNext().getNext());
                changeBase(false);
                return;
            }
        }
    }

    private int hash(K key) {
        return key.hashCode() % (2 << sizeBase);
    }

    private void changeBase(int n) {
        if (n < originalSetSizeBase) {
            return;
        }

        sizeBase = n;
        entryCount = 0;
        LinkedListNode<Entry<K, V>>[] newDict = new LinkedListNode[2 << n];
        LinkedListNode<Entry<K, V>>[] tempDict = this.dict;
        this.dict = newDict;
        for (LinkedListNode<Entry<K, V>> entryLinkedListNode : tempDict) {
            LinkedListNode<Entry<K, V>> first = entryLinkedListNode;
            while (first != null) {
                put(first.getVal().key, first.getVal().value);
                first = first.getNext();
            }
        }
    }

    private void changeBase(boolean add) {
        if (add) {
            entryCount++;
            if (getLoadFactor() > 2) {
                changeBase(sizeBase + 1);
            }
        } else {
            entryCount--;
            if (getLoadFactor() < 0.25) {
                changeBase(sizeBase - 1);
            }
        }
    }

    private double getLoadFactor() {
        return (double) entryCount / (double) (2 << sizeBase);
    }

    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
