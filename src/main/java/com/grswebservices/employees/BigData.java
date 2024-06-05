package com.grswebservices.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// WARNING: This file has 5 million records.
public class BigData {
    // record generates getters, setters, etc automatically
    record Person(String firstName, String lastName, long salary, String state) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
            Long result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    // .parallel() // parallel processing is supposed to reduce processing time but happens to increase it on my 4 core computer
                    .skip(1) // skip header row
//                            .limit(10) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
                    .mapToLong(Person::salary).sum();
            long endTime = System.currentTimeMillis();
            System.out.printf("$%,d.00%n", result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
