package com.grswebservices.employees;

import java.time.LocalDate;

// this is equivalent to the class-based version
public record WeirdoRecord(String lastName, String firstName, LocalDate dob) implements Apple {
    // alternative constructor
    public WeirdoRecord(String lastName, String firstName) {
        this(lastName, firstName, null);
    }

    public String sayHello() {
        return "Hello world";
    }
}
