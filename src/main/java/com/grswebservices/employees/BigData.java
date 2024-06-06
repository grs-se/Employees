package com.grswebservices.employees;

import com.sun.source.tree.Tree;

import java.io.IOException;
import java.math.BigDecimal;
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
    record Person(String firstName, String lastName, BigDecimal salary, String state, char gender) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
//            Map<String, String> result =
            // Map<String(state), Map<chat(gender), String(formatted-salary)>>
            // might be preferable to use more generic Map type rather than TreeMap
            TreeMap<String, Map<Character, String>> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .skip(1) // skip header row
                    .limit(100) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)))
                    .collect(
                            groupingBy(Person::state, TreeMap::new,
                                    groupingBy(Person::gender,
                                            collectingAndThen(
                                                    reducing(BigDecimal.ZERO, Person::salary, BigDecimal::add),
//                                                    reducing(BigDecimal.ZERO, Person::salary, (a, b) -> a.add(b)),
//                                                    summingLong(Person::salary),
                                                    NumberFormat.getCurrencyInstance()::format))
                            )
                    );
//                            .entrySet().stream()
//                            .forEach();
//                    .forEach((state, salary) -> System.out.printf("%s -> %s%n", state, salary));
//                          collectingAndThen(summingLong(Person::salary), s -> NumberFormat.getCurrencyInstance().format(s))));
//                          collectingAndThen(summingLong(Person::salary), s -> String.format("$%,d.00%n", s))));
//                    .collect(Collectors.groupingBy(Person::state, TreeMap::new, toList())); // convert default HashMap to TreeMap for alphabetized ordering
//                    .collect(Collectors.groupingBy(Person::state));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
            System.out.println(result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
