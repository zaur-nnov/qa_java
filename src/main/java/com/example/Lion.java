package com.example;

import java.util.List;

/**
 * Чтобы Lion не зависел напрямую от Feline,
 * мы передаем экземпляр Feline через конструктор
 * (внедрение зависимости).
 */

public class Lion {

    private final Feline feline;
    boolean hasMane;

    public Lion(String sex, Feline feline) throws Exception {
        this.feline = feline;
        if ("Самец".equals(sex)) {
            this.hasMane = true;
        } else if ("Самка".equals(sex)) {
            this.hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
    }

    public int getKittens() {
        return feline.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }
}
