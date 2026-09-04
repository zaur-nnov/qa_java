package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тесты для дополнительного задания: AlexLion
 * Дети, друзья, проживание
 */
@RunWith(MockitoJUnitRunner.class)
public class AlexLionTest {

    @Test
    public void getKittensAlwaysReturnsZero() throws Exception {
        AlexLion alex = new AlexLion();
        assertEquals(0, alex.getKittens());
    }

    @Test
    public void getFriendsReturnsCorrectList() throws Exception {
        AlexLion alex = new AlexLion();
        List<String> expectedFriends = List.of("Марти", "Глория", "Мелман");
        assertEquals(expectedFriends, alex.getFriends());
    }

    @Test
    public void getPlaceOfLivingReturnsNewYorkZoo() throws Exception {
        AlexLion alex = new AlexLion();
        assertEquals("Нью-Йоркский зоопарк", alex.getPlaceOfLiving());
    }

    @Test
    public void alexIsAlwaysMaleAndHasMane() throws Exception {
        AlexLion alex = new AlexLion();
        assertTrue(alex.doesHaveMane());
    }
}
