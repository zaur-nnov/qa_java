package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CatParameterizedTest {

    // Тип диеты ("Хищник", "Травоядное", "Шоколад")
    private final String animalKind;

    // Что должен вернуть мок Feline (при успехе)
    private final List<String> mockFoodResult;

    // Флаг: ожидаем ли мы успех или ошибку
    private final boolean isSuccessScenario;

    // Конструктор JUnit 4 для передачи параметров в каждый тест
    public CatParameterizedTest(String animalKind, List<String> mockFoodResult, boolean isSuccessScenario) {
        this.animalKind = animalKind;
        this.mockFoodResult = mockFoodResult;
        this.isSuccessScenario = isSuccessScenario;
    }

    // Набор тестовых данных для всех сценариев питания
    @Parameterized.Parameters(name = "Тест диеты: {0} -> Ожидаем успех? {2}")
    public static Collection<Object[]> getCatDietData() {
        return Arrays.asList(new Object[][]{
                // 1. Успех: Хищник ест мясо, птицу, рыбу
                {"Хищник", List.of("Животные", "Птицы", "Рыба"), true},

                // 2. Неуспех: Кошка хищник и не ест траву и растения (именно такая у нас)
                {"Хищник", List.of("Трава", "Различные растения"), false},

                // 3. Неуспех: Рыба вместо вида животного
                {"Рыба", null, false},

                // 4. Неуспех: Пустая строка вместо вида животного
                {"", null, false}
        });
    }

    @Test
    public void testCatFoodViaEatMeatMethod() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        Cat cat = new Cat(felineMock);

        if (isSuccessScenario) {
            // Обучаем метод eatMeat() возвращать успешный список
            Mockito.when(felineMock.eatMeat()).thenReturn(mockFoodResult);
            assertEquals(mockFoodResult, cat.getFood());
        } else {
            // Обучаем eatMeat() выбрасывать ошибку
            Exception expectedException = new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник");
            Mockito.when(felineMock.eatMeat()).thenThrow(expectedException);

            Exception actualException = assertThrows(Exception.class, cat::getFood);
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", actualException.getMessage());
        }
    }
}