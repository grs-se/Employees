package com.grswebservices.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public abstract class Employee implements IEmployee {
    protected final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    private static final String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?";
    public static final Pattern PEOPLE_PAT = Pattern.compile(PEOPLE_REGEX);
    protected final Matcher peopleMat;
    protected String lastName;
    protected String firstName;
    protected LocalDate dob;

    // can be called by subclasses but not outside the class hierarchy
    protected Employee() {
        peopleMat = null;
        lastName = "N/A";
        firstName = "N/A";
        dob = null;
    }

    protected Employee(String personText) {
        // refer to full qualified class name if field is static
        peopleMat = Employee.PEOPLE_PAT.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
        }
    }

    // employeeText = "Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}"
    // want to force everyone to only use this factory method then make constructors protected
    public static final IEmployee createEmployee(String employeeText) {
        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(employeeText);
        if (peopleMat.find()) {
            return switch (peopleMat.group("role")) {
                case "Programmer" -> new Programmer(employeeText);
                case "Manager" -> new Manager(employeeText);
                case "Analyst" -> new Analyst(employeeText);
                case "CEO" -> new CEO(employeeText);
                default -> new DummyEmployee();
                // Lambda
                // default -> () -> 0;
                /*
                // Anonymous class
                default -> new Employee() {
                    @Override
                    public int getSalary() {
                        return 0;
                    }
                };
                */

            };
        } else {
            return new DummyEmployee();
            // default -> () -> 5;
        }
    }

    // "template method pattern"
    public abstract int getSalary();

    public double getBonus() {
        return getSalary() * 1.10;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

        @Override
    public String toString() {
        return String.format("%s, %s: %s - %s", lastName, firstName, moneyFormat.format(getSalary()), moneyFormat.format(getBonus()));
    }

    // STATIC NESTED CLASS
    private static final class DummyEmployee extends Employee {
        @Override
        public int getSalary() {
            // can access firstname because it is extending Employee
            // System.out.println(firstName);
            return 0;
        }
    }

    @Override
    public int compareTo(IEmployee o) {
        Employee other = (Employee) o;
        return this.lastName.compareTo(other.lastName);
    }

        // NON-STATIC / INNER NESTED CLASS
    private final class MyInnerClass {
        public int getStuff() {
            // isn't inheriting firstname, it can access firstname from the enclosing class
            System.out.println(firstName);
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(lastName, employee.lastName) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(dob, employee.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dob);
    }
}