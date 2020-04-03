package com.example.programme_02;
public class Data {
    //路口
    private int crossroads;
    //红灯时长
    private int red_lamp;
    //黄灯时长
    private int yello_lamp;
    //绿灯时长
    private int green_lamp;

    public Data(int crossroads, int red_lamp, int yello_lamp, int green_lamp) {
        this.crossroads = crossroads;
        this.red_lamp = red_lamp;
        this.yello_lamp = yello_lamp;
        this.green_lamp = green_lamp;
    }

    public Data() {

    }

    public int getCrossroads() {
        return crossroads;
    }

    public void setCrossroads(int crossroads) {
        this.crossroads = crossroads;
    }

    public int getRed_lamp() {
        return red_lamp;
    }

    public void setRed_lamp(int red_lamp) {
        this.red_lamp = red_lamp;
    }

    public int getYello_lamp() {
        return yello_lamp;
    }

    public void setYello_lamp(int yello_lamp) {
        this.yello_lamp = yello_lamp;
    }

    public int getGreen_lamp() {
        return green_lamp;
    }

    public void setGreen_lamp(int green_lamp) {
        this.green_lamp = green_lamp;
    }
}
