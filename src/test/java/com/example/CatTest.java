package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Тесты для класса Cat (с использованием Mockito)
 * Класс Cat зависит от Feline, поэтому мокаем Feline.
 */

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline feline;

    @Test
    public void getSoundReturnsMiu() {
        Cat cat = new Cat(feline);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void getFoodReturnsListViaEatMeat() throws Exception {
        Cat cat = new Cat(feline);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        // Обучаем мок на метод eatMeat(), так как кошка вызывает именно его
        Mockito.when(feline.eatMeat()).thenReturn(expectedFood);

        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void getFoodWhenFelineThrowsExceptionThenCatThrowsExceptionToo() throws Exception {
        Cat cat = new Cat(feline);

        // Симулируем ошибку внутри Feline при вызове eatMeat
        Mockito.when(feline.eatMeat())
                .thenThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"));

        // Проверяем, что кошка транслирует эту ошибку наружу
        Exception exception = assertThrows(Exception.class, () -> {
            cat.getFood();
        });

        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }

    @Test
    public void getFoodReturnsList() throws Exception {
        Cat cat = new Cat(feline);
        // Задаем список еды для хищника, который заложен в Animal
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        // Обучаем мок на метод eatMeat(), так как Cat вызывает именно его
        Mockito.when(feline.eatMeat()).thenReturn(expectedFood);

        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void getFoodWithUnknownAnimalKindThrowsException() throws Exception {
        Cat cat = new Cat(feline);

        // Симулируем ситуацию, когда кошке пытаются подсунуть неизвестный вид животного,
        // и метод getFood("Неизвестный") должен выбросить исключение.
        Mockito.when(feline.getFood("Неизвестный"))
                .thenThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"));

        // Проверяем, что исключение действительно выбрасывается с правильным текстом
        Exception exception = assertThrows(Exception.class, () -> {
            feline.getFood("Неизвестный");
        });

        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }
}
