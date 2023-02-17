package ru.levelup.at.homework2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LuckyTicketNegativeTest {
    //Класс для проверки негативных тестовых сценариев метода isLucky класс LuckyTicket
    @Tag("Negative")
    @ParameterizedTest
    @NullSource

    public void ticketNumberIsNull(String input) {

        String expectedMessage = "Номер билета = null";
        LuckyTicket luckyTicket = new LuckyTicket();
        assertThatThrownBy(() -> luckyTicket.isLucky(input))
            .isInstanceOf(NullPointerException.class).hasMessage(expectedMessage);

    }

    @Tag("Negative")
    @ParameterizedTest
    @ValueSource (strings = {"5203"})

    public void isSixSymbolTicket(String input) {
        String expectedMessage = "Номер билета содержит не 6 символов";
        LuckyTicket luckyTicket = new LuckyTicket();
        assertThatThrownBy(() -> luckyTicket.isLucky(input))
            .isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }
}

