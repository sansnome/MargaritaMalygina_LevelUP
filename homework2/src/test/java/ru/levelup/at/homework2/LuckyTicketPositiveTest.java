package ru.levelup.at.homework2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.levelup.at.homework2.LuckyTicket;

public class TicketPositiveTest {

    @Tag("Positive")
    @ParameterizedTest
    @ValueSource(ints = {5203})

    public void sixSymbolTicket(int input) {
        boolean expected = false;
        boolean actual = LuckyTicket.isLucky(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Tag("Positive")
    @ParameterizedTest
    @ValueSource(ints = {385916})

    public void isEqualSum(int input) {
        boolean expected = true;
        boolean actual = LuckyTicket.isLucky(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Tag("Positive")
    @ParameterizedTest
    @ValueSource(ints = {123344})

    public void isNotEqualSum(int input) {
        boolean expected = false;
        boolean actual = LuckyTicket.isLucky(input);
        assertThat(actual).isEqualTo(expected);
    }

}
