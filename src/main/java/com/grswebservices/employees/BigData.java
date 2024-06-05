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
            Long result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .limit(2) // limit processing of lines to 2
                    .skip(1) // skip header row
                    .collect(Collectors.counting());
//                    .count()
//                    .collect(Collectors.toList());
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
