package com.example.adaptersandlists;

public class Product {
    private String name;
    private int count;
    private String unit;

    Product(String name, String unit) {
        this.name = name;
        this.count = 0;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }
}
