package com.example.user.netbbangver1;

public class Cash {

    private int charge;
    private String value;
    private int total;

    public Cash(){
        charge = 0;
        value = null;
        total = 0;
    }

    public Cash(String value,int charge, int total) {
        this.charge = charge;
        this.value = value;
        this.total = total;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
