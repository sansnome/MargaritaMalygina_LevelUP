package ru.levelup.at.homework2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.levelup.at.homework2.LuckyTicket;

public class TicketNegativeTest {

    @Tag("Negative")
    @ParameterizedTest
    @ValueSource(ints = {5203})

    public void sixSymbolTicket(int input) {
        boolean expected = false;
        boolean actual = LuckyTicket.isLucky(input);
        assertThat(actual).isEqualTo(expected);
    }
}
