package com.devcors.javaacademy.lesson2;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DevCorsListIterator<T> implements Iterator<T> {
    private DevCorsList<T> devCorsList;
    private int index;
    public DevCorsListIterator(DevCorsList<T> list) {
        this.devCorsList = list;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < devCorsList.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return devCorsList.get(index++);
    }

}
