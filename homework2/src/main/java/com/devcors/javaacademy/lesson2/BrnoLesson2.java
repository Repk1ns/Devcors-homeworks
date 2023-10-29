package com.devcors.javaacademy.lesson2;

public class BrnoLesson2 {

    /**
     * Fill main method with your calls of {@link Conditions} and {@link Loops} methods.
     *
     * <p>Output results of methods calls using System.out.println with reasonable message.
     */
    public static void main(String[] args) {

        //
        // For all test cases run ConditionsTest and LoopsTest
        //

        //
        // Conditions calls
        //
        Conditions cond = new Conditions();

        //Print expected TRUE to STDOUT
        System.out.println(cond.isGreaterThan(5,1));

        //Print expected -1 to STDOUT
        System.out.println(cond.compare(15,20));

        //Print expected TRUE to STDOUT
        System.out.println(cond.isWorkingDay(Constants.FRIDAY));

        //Print expected 6 to STDOUT
        System.out.println(cond.dayOfWeekUsingIf(Constants.SATURDAY));

        //Print expected -1 to STDOUT
        System.out.println(cond.dayOfWeekUsingSwitch("AnotherDayInParadise"));

        //
        // Loops Calls
        //

        Loops loops = new Loops();

        //Print expected 6 to STDOUT
        System.out.println(loops.sum(3));

        //Print expected 56 to STDOUT
        System.out.println(loops.sumEven(14));

        //Print expected 7 to STDOUT
        System.out.println(loops.hailstoneProblem(3));

        // Triangles

        //Print triangle 1
        System.out.println("Triangle 1");
        loops.triangle1(4);

        System.out.println("---------------");

        //Print triangle 2
        System.out.println("Triangle 2");
        loops.triangle2(3);

        System.out.println("---------------");

        //Print triangle 3
        System.out.println("Triangle 3");
        loops.triangle3(5);

    }

}
