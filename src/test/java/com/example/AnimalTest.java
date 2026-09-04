package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AnimalTest {

    @Test
    public void getFamilyOfBaseAnimalReturnsAllFamilies() {
        Animal animal = new Animal();
        String expectedString = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";

        // Вызываем метод у родительского класса, чтобы JaCoCo зафиксировал покрытие этой строки
        assertEquals(expectedString, animal.getFamily());
    }
}
