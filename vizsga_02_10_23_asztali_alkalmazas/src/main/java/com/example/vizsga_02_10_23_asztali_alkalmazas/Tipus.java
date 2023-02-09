package com.example.vizsga_02_10_23_asztali_alkalmazas;

public class Tipus {
    String name;

    public Tipus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tipus{" +
                "name='" + name + '\'' +
                '}';
    }
}
