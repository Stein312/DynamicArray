package main.java;

import java.util.Set;

public class PhoneBook {



    private AssociativeArray<String, Subscriber> book;


    public PhoneBook() {
        book=new AssociativeArray<>();
    }
    public PhoneBook(AssociativeArray<String,Subscriber> book) {
        this.book=book;
    }

    public int getSize() {
        return book.getSize();
    }
    public Subscriber put(String FIO,String number,String address){
        Subscriber s=new Subscriber(FIO, number, address);
        book.add(number, s);
        return s;
    }
    public Subscriber remove(String number){
       return book.remove(number);
    }
    public Subscriber get(String number){
        return book.get(number);
    }
    public Set<String> numberSet(){
        return book.keySet();
    }
    public AssociativeArray<String, Subscriber> getBook() {
        return book;
    }


}
