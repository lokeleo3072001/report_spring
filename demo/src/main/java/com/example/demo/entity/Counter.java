package com.example.demo.entity;

public class Counter {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Counter(int count) {
        this.count = count;
    }

    public Counter() {
    }

    public void increment(){
        count++;
    }
    
    
}
