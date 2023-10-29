package com.devcors.javaacademy.lesson2;

import java.util.Iterator;

public final class DevCorsListImpl<T> implements DevCorsList<T> {
    private Node<T> head;
    private int size;
    public DevCorsListImpl() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<T> currentNode = this.head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }

        this.size++;
    }

    @Override
    public T get(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("Node index is out of range!");
        } else if (i < 0) {
            throw new IllegalArgumentException("Node index must be positive!");
        }

        Node<T> currentNode = this.head;

        for (int j = 0; j < i; j++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getData();
    }

    @Override
    public void remove(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("Node index is out of range!");
        } else if (i < 0) {
            throw new IllegalArgumentException("Node index must be positive!");
        }

        if (i == 0) {
            this.head = head.getNext();
            this.size--;

            return;
        }

        if (this.head == null) {
            return;
        }

        Node<T> currentNode = head;
        Node<T> previousNode = null;
        for (int j = 0; j < i; j++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        previousNode.setNext(currentNode.getNext());
        this.size--;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new DevCorsListIterator<>(this);
    }
}
