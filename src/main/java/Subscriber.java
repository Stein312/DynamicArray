package main.java;

public class Subscriber {

    private String FIO;
    private String number;
    private String address;


    public Subscriber(String FIO, String number, String address) {
        this.FIO = FIO;
        this.number = number;
        this.address = address;
    }
    public String getFIO() {
        return FIO;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Абонент:\n" +
                "ФИО=" + FIO + ";\n" +
                "Номер=" + number + ";\n" +
                "Адресс=" + address + ";\n";
    }
}
