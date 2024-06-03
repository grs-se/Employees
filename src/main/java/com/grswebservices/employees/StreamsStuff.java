package com.grswebservices.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsStuff {

    private static String myVar;

    public static void main(String[] args) {
        String peopleText = """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=4000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=5000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=6000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=7000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=8000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmerzzzzz, {locpd=9000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
                Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
                Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
                Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
                Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
                Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
                Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
                Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
                """;

//        absoluteFileStream();

        tabulateEmployees(peopleText);

//        streamsPractice1();
//
//        streamsPractice2();

        String myVar = emptyStream();
//        nullableStream(myVar);
//
//        intStream();
//
//        arrayStream();

        sumSalaries(peopleText);

    }

    private static void sumSalaries(String peopleText) {
        int sum = peopleText
                .lines()
                .map(Employee::createEmployee)
                .mapToInt(StreamsStuff::showEmpAndGetSalary)
                .sum();
        System.out.println(sum);
    }

    private static int showEmpAndGetSalary(IEmployee emp) {
        System.out.println(emp);
        return emp.getSalary();
    }

    private static void absoluteFileStream() {
        try {
            Files.lines(Path.of("C:\\Users\\georg\\IdeaProjects\\Employees\\src\\main\\java\\com\\grswebservices\\employees\\employees.txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void arrayStream() {
        String[] names = {"bob", "tom", "jane"};
        Arrays.stream(names)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    private static void intStream() {
        IntStream.rangeClosed(1,5) // returns IntStream
                .boxed()// returns Stream<Integer> : a stream of integers
                .map(String::valueOf)
//                .map(String::valueOf) // mapToObj() - useful for converting values in IntStream to something that is probably not numerical
                .map(s -> s.concat("-"))
                .forEach(System.out::println);
    }

    private static void nullableStream(String myVar) {
        Stream.ofNullable(myVar) // get nothing - empty stream - subsequent steps of stream's pipeline don't execute because at end of stream already
//        Stream.of(myVar)
                .forEach(System.out::println);
    }

    private static String emptyStream() {
        myVar = null;
        Stream myStream = null;
        if (myVar == null) {
            myStream = Stream.empty();
        } else {
            myStream = Stream.of(myVar);
        }
        myStream
                .forEach(System.out::println);
        return myVar;
    }

    private static void streamsPractice2() {
        record Car(String make, String model){}

        Stream.of(new Car("Ford", "Bronco"), new Car("Tesla", "X"), new Car("Tesla", "3"))
//        Stream.of(1,2,3,4)
                .filter(c -> "Tesla".equals(c.make))
//                .filter(c -> c.make.equals("Tesla"))
                .forEach(System.out::println);
    }

    private static void streamsPractice1() {
        Collection<String> nums = Set.of("one", "two", "three", "four");
        nums.stream()
                .map(String::hashCode)
                .map(Integer::toHexString) // hashcode in decimal form
//                .map(String::length)
//                .map(String::toUpperCase)
                .forEach(h -> System.out.printf("%h%n", h));
//                .forEach(System.out::println);
    }

    private static void tabulateEmployees(String peopleText) {
        peopleText
                .lines()
                .map(Employee::createEmployee) // converting from string to type IEmployee
                .forEach(System.out::println); // System.out.println("hello"); - prints the string to the screen but it does not return any output
    }
}
