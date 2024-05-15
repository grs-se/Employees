package com.grswebservices.employees;

import java.time.LocalDate;
import java.util.Objects;

public class WeirdoClass {
    private String lastName;
    private String firstName;
    private LocalDate dob;

    public WeirdoClass() {}

    public WeirdoClass(String lastName, String firstName, LocalDate dob) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeirdoClass weirdo = (WeirdoClass) o;
        return Objects.equals(lastName, weirdo.lastName) && Objects.equals(firstName, weirdo.firstName) && Objects.equals(dob, weirdo.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dob);
    }
}
