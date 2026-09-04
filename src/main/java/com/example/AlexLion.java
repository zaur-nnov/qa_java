package com.example;

import java.util.List;

/**
 * Реализация дополнительного задания: Лев AlexLion
 * Наследуемся от Lion,
 * фиксируем пол "Самец",
 * заменяем котят на 0,
 * добавляем методы для друзей и места жительства.
 */

public class AlexLion extends Lion {

    // Вызываем конструктор суперкласса, передавая пол "Самец"
    public AlexLion() throws Exception {
        super("Самец", new Feline());
    }

    // У Алекса нет львят
    @Override
    public int getKittens() {
        return 0;
    }

    // Возвращает список друзей Алекса
    public List<String> getFriends() {
        return List.of("Марти", "Глория", "Мелман");
    }

    // Возвращает место проживания
    public String getPlaceOfLiving() {
        return "Нью-Йоркский зоопарк";
    }
}
