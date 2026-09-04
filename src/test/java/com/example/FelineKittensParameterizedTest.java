package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

/**
 *  FelineKittensParameterizedTest для проверки всех вариаций котят Feline
 */
@RunWith(Parameterized.class)
public class FelineKittensParameterizedTest {

    private final int inputKittens;
    private final int expectedKittens;

    // Конструктор сохраняет параметры для каждого прогона тестов
    public FelineKittensParameterizedTest(int inputKittens, int expectedKittens) {
        this.inputKittens = inputKittens;
        this.expectedKittens = expectedKittens;
    }

    // Набор тестовых данных: { переданное_значение, ожидаемый_результат }
    @Parameterized.Parameters(name = "Количество котят: переданное {0} -> ожидаемое {1}")
    public static Collection<Object[]> getKittensData() {
        return Arrays.asList(new Object[][]{
                {0, 0},                           // Ноль котят
                {1, 1},                           // Один котенок
                {5, 5},                           // Позитивный сценарий
                {-1, -1},                         // Негативный сценарий (если код не бросает Exception, вернет -1)
                {500_000_000, 500_000_000},       // Большое число
                {Integer.MAX_VALUE, Integer.MAX_VALUE} // Граничное значение (максимум для int: 2147483647)
        });
    }

    @Test
    public void getKittensWithArgsReturnsCorrectValue() {
        Feline feline = new Feline();
        assertEquals(expectedKittens, feline.getKittens(inputKittens));
    }
}
