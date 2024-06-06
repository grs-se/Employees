package com.grswebservices.employees;

import com.sun.source.tree.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

// WARNING: This file has 5 million records.
public class BigData {
    // record generates getters, setters, etc automatically
    record Person(String firstName, String lastName, long salary, String state) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
//            Map<String, String> result =
                    Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .skip(1) // skip header row
//                    .limit(10) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
                    .collect(groupingBy(Person::state, TreeMap::new,
                            collectingAndThen(summingLong(Person::salary), NumberFormat.getCurrencyInstance()::format)))
//                            .entrySet().stream()
//                            .forEach();
                    .forEach((state, salary) -> System.out.printf("%s -> %s%n", state, salary));
//                          collectingAndThen(summingLong(Person::salary), s -> NumberFormat.getCurrencyInstance().format(s))));
//                          collectingAndThen(summingLong(Person::salary), s -> String.format("$%,d.00%n", s))));
//                    .collect(Collectors.groupingBy(Person::state, TreeMap::new, toList())); // convert default HashMap to TreeMap for alphabetized ordering
//                    .collect(Collectors.groupingBy(Person::state));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
//            System.out.println(result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
