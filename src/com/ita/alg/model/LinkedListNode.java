package com.ita.alg.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkedListNode<T> {
    T val;
    LinkedListNode<T> next, prev;
}
