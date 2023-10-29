package com.devcors.javaacademy.lesson2;

public class Lesson2 {

    /**
     * <strong>Homework 2</strong>
     * Try to finish implementation of {@link DevCorsListImpl}.
     * Use DevCorsListTest.java test to verify correctness of your implementation.
     * <strong>Bonus</strong> - try to extend {@link DevCorsList} with {@link Iterable} interface and implement custom iterator {@link Iterable#iterator} for our collection.
     * @param args
     */
    public static void main(String[] args) {
        var myList = new DevCorsListImpl<String>();
        myList.add("Prvni");
        myList.add("Druhy");
        myList.add("Treti");
        myList.add("Ctvrty");

        for (String element : myList)
        {
            System.out.println(element);
        }
    }
}
