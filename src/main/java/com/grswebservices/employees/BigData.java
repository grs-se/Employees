package com.grswebservices.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// WARNING: This file has 5 million records.
public class BigData {
    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
            Long result = Files.lines(Path.of("E:\\Java\\Hr5m.csv")).parallel()
                    .skip(1) // skip header row
//                            .limit(10) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(arr -> arr[25]) // index 25 = salary field
                    .mapToLong(Long::parseLong)
                    .sum();
//                    .collect(Collectors.summingLong(Long::parseLong));
            long endTime = System.currentTimeMillis();
            System.out.printf("$%,d.00%n", result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
