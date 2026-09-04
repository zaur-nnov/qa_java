package com.example;

import java.util.List;

public class Feline extends Animal implements Predator {

    @Override
    public List<String> eatMeat() throws Exception {
        return getFood("Хищник");
    }

    @Override
    public String getFamily() {
        return "Кошачьи";
    }

    public int getKittens() {
        // To-Do: Уточнить у разработчика почему по дефолту есть котята в количестве 1 а не 0 или 8?
        // To-Do: Уточнить у разработчика почему нет способа добавить детей в действующей жизни а не при рождении взрослой особи (addChuldren/addChilds)?

        return getKittens(1);
    }

    public int getKittens(int kittensCount) {
        return kittensCount;
    }
}
