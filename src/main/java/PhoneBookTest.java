package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {
    PhoneBook phoneBook;
    @Test
    void put() {
        phoneBook=new PhoneBook();
        phoneBook.put("Иванова Ивана Инвановна","89895788645","Чиркизовска 123");
        phoneBook.put("Болда Иван Иванович","89658946845","Петропавловская 55");
        phoneBook.put("Кичн Нора Олог","87898454548","Петропавловская 55");
        phoneBook.put("Ылоп ЫДВЛАО ЛОВр","78421813516","Петропавловская 55");
        phoneBook.put("ЩШУАрц Ыра ВОцйу","89665955626","Петропавловская 55");
        for(String n:phoneBook.numberSet()){
            System.out.println(phoneBook.get(n).toString());
        }
    }

    @Test
    void remove() {
        phoneBook=new PhoneBook();
        phoneBook.put("Иванова Ивана Инвановна","89895788645","Чиркизовска 123");
        phoneBook.put("Болда Иван Иванович","89658946845","Петропавловская 55");
        phoneBook.put("Кичн Нора Олог","87898454548","Петропавловская 55");
        phoneBook.put("Ылоп ЫДВЛАО ЛОВр","78421813516","Петропавловская 55");
        phoneBook.put("ЩШУАрц Ыра ВОцйу","89665955626","Петропавловская 55");
        phoneBook.remove("87898454548");
        phoneBook.remove("78421813516");
        for(String n:phoneBook.numberSet()){
            System.out.println(phoneBook.get(n).toString());
        }
    }


}