package ru.levelup.at.homework2;

public class BusTicket {
    //класс для генерации тестовых данных для метода isLucky класса LuckyTicket
    public int getTicketNumber(int max) {
        return (int) (Math.random() * max);

    }
}
