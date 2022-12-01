package ru.levelup.at.homework2;

public class App {
    //класс для проверки работоспособности метода isLucky класса LuckyTicket
    public static void main(String[] args) {

        BusTicket busTicket = new BusTicket();
        //LuckyTicket luckyTicket = new LuckyTicket();
        int maxLength = 1000000; //максимальное значение билета, не включительно
        String ticketNumber = Integer.toString(busTicket.getTicketNumber(maxLength));

        System.out.println("Your ticket number is: ");
        System.out.println(ticketNumber);
        System.out.println("Is it lucky?");
        System.out.println(LuckyTicket.isLucky(ticketNumber));


    }
}
