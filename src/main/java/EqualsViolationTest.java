package main.java;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EqualsViolationTest {

    @Test
    void testEquals() {
        EqualsViolation e=new EqualsViolation("Рука",23,"Нога");
        EqualsViolation a=new EqualsViolation("Нога",23,"Рука");
        EqualsViolation b=new EqualsViolation("Рука",23,"Нога");

        if(e.equals(e)){
            System.out.println("e=e");
        }
        else
            System.out.println("e!=e");
        if (e.equals(a)){
            System.out.println("e=a");
        }
        else
            System.out.println("e!=a");
        if(a.equals(b)){
            System.out.println("a=b");
        }
        else System.out.println("a!=b");
        if(e.equals(b)){
            System.out.println("e=b");
        }
        else System.out.println("e!=b");



    }
}