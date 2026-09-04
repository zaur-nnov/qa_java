package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Параметризованные тесты для класса Lion
 * (вынесены в отдельный класс)
 */
@RunWith(Parameterized.class)
public class LionParameterizedTest {

    // Передаваемый пол
    private final String sex;

    // Ожидаемый результат для гривы (null, если ждем ошибку)
    private final Boolean expectedHasMane;

    // Флаг: успешный конструктор или должен упасть
    private final boolean isSuccessScenario;


    // Конструктор параметров
    public LionParameterizedTest(String sex, Boolean expectedHasMane, boolean isSuccessScenario) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
        this.isSuccessScenario = isSuccessScenario;
    }

    // Настройка имени каждого теста в отчете: {0} - пол, {1} - наличие гривы, {2} - успешность
    @Parameterized.Parameters(name = "Тест {index}: Пол льва = [{0}], Наличие гривы = [{1}] -> Ожидаем успех конструктора? [{2}]")
    public static Collection<Object[]> getLionData() {
        return Arrays.asList(new Object[][]{
                {"Самец", true, true},      // Самец — грива есть
                {"Самка", false, true},     // Самка — гривы нет
                {"Инопланетянин", null, false}, // Некорректный пол — ждем ошибку
                {"", null, false},          // Пустая строка — ждем ошибку
                {null, null, false}         // Передача null — ждем ошибку
        });
    }

    @Test
    public void checkLionConstructorScenarios() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);

        if (isSuccessScenario) {
            // Если сценарий успешный, проверяем корректность инициализации гривы
            Lion lion = new Lion(sex, felineMock);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        } else {
            // Если сценарий негативный, проверяем, что конструктор выбрасывает Exception
            Exception exception = assertThrows(Exception.class, () -> {
                new Lion(sex, felineMock);
            });

            // Проверяем текст ошибки (для null будет NullPointerException, для строк — кастомный текст)
            if (sex == null) {
                // Если передали null, Java упадет с дефолтной ошибкой
                // Этот кейс защищает от падения самого теста
            } else {
                assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
            }
        }
    }
}
