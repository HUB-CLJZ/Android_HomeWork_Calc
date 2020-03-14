package com.example.bianchen_1;

public class Car {
    private int number;
    private String recharge;
    private String time;

    public Car() {};
    public Car(int number, String recharge, String time) {
        this.number = number;
        this.recharge = recharge;
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
