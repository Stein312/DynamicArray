package main.java;

import java.util.Objects;

public class EqualsViolation {
    private String name;
    private int age;
    private String lastName;
    public EqualsViolation(String name, int age,String lastName) {
        this.name = name;
        this.age = age;
        this.lastName=lastName;

    }

    @Override
    public boolean equals(Object o) {
        EqualsViolation that = (EqualsViolation) o;
        return lastName==that.name;
    }

}
