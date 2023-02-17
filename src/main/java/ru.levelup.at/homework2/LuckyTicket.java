package ru.levelup.at.homework2;

public class LuckyTicket {
    //класс для проверки билета на то, является ли он "счастливым"
    // (сумма первых трёх цифр должна быть равна сумме последних трех цифр
    public static boolean isLucky(String ticketNumber) {

        if (ticketNumber == null) {
            throw new NullPointerException("Номер билета = null");
        } else {
            int intInput = Integer.parseInt(ticketNumber);
            int numberLength = (int) Math.floor(Math.log10(intInput) + 1);

            if (numberLength == 6) {

                int firstPart = intInput / 1000;
                int secondPart = intInput % 1000;
                int sum1 = firstPart / 100 + (firstPart / 10) % 10 + firstPart % 10;
                int sum2 = secondPart / 100 + (secondPart / 10) % 10 + secondPart % 10;

                if (sum1 == sum2) {
                    return true;
                } else {
                    return false;
                }
            }  else {
                throw new IllegalArgumentException("Номер билета содержит не 6 символов");
            }
        }
    }
}
