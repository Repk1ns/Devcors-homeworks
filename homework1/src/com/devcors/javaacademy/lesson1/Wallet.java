package com.devcors.javaacademy.lesson1;

public class Wallet {

    private int actualBalance = 0;
    public Wallet() {

    }

    public void saveMoney(int value) {
        this.actualBalance = this.actualBalance + value;
    }


    public void takeMoney(int value) {
        this.actualBalance = this.actualBalance - value;
    }


    public int getActualBalance() {
        return this.actualBalance;
    }


    public String toString() {
        return "Actual balance is: " + this.actualBalance;
    }
}
