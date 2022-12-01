package ru.levelup.at.homework2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LuckyTicketPositiveTest {
    //Класс для проверки позитивных тестовых сценариев метода isLucky класс LuckyTicket
    @Tag("Positive")
    @ParameterizedTest
    @ValueSource(strings = {"385916"})

    public void isEqualSum(String input) {
        boolean expected = true;
        LuckyTicket luckyTicket = new LuckyTicket();
        boolean actual = luckyTicket.isLucky(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Tag("Positive")
    @ParameterizedTest
    @ValueSource(strings = {"123344"})

    public void isNotEqualSum(String input) {
        boolean expected = false;
        LuckyTicket luckyTicket = new LuckyTicket();
        boolean actual = luckyTicket.isLucky(input);
        assertThat(actual).isEqualTo(expected);
    }

}
