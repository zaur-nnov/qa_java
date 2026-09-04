package com.example;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


/**
 * Тесты для класса Feline
 * Здесь проверяем семейство, еду и количество котят.
 * Для 100% JaCoCo нужно вызвать метод getKittens() как без параметров, так и с параметрами.
 */
public class FelineTest {

    @Test
    public void eatMeatReturnsPredatorFood() throws Exception {
        Feline feline = new Feline();
        // Зависит от реализации Animal.getFood("Хищник")
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
    }

    @Test
    public void getFamilyReturnsCatsGroup() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void getKittensWithoutArgsReturnsOne() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    /**
     * Этот тест здесь для покрытия единственного сценария с положительным параметром в аргументе.
     * Более детально эта часть кода протестируется в параметризированном тесте
     * (по условиям задания спринта он создан в отдельном файле)
     */
    @Test
    public void getKittensWithPositiveArgsReturnsArgumentValue() {
        Feline feline = new Feline();
        assertEquals(5, feline.getKittens(5));
    }


    // --- ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ ДЛЯ 100% ПОКРЫТИЯ КЛАССА ANIMAL ---
    @Test
    public void getFoodWithHerbivoreKindReturnsPlants() throws Exception {
        Feline feline = new Feline();
        List<String> expectedFood = List.of("Трава", "Различные растения");
        // Вызываем базовый метод Animal с параметром "Травоядное"
        assertEquals(expectedFood, feline.getFood("Травоядное"));
    }

    @Test
    public void getFoodWithUnknownKindThrowsException() {
        Feline feline = new Feline();
        // Проверяем ветку else в Animal.java, которая выбрасывает Exception
        Exception exception = assertThrows(Exception.class, () -> {
            feline.getFood("Камни и песок");
        });

        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }
}
