package com.grswebservices.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class StateCounter {
    public static void main(String[] args) throws IOException {
        Map<String, Long> stateCounts = new HashMap<>();
        Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                .skip(1)
                .limit(100)
                .map(l -> l.split(","))
//                .forEach(a -> stateCounts.merge())
//                .forEach(a -> stateCounts.computeIfAbsent())
//                        .forEach(a -> stateCounts.computeIfAbsent(a[32], k -> Long.valueOf(k.length())))
                .forEach(a -> stateCounts.compute(a[32], (k, v) -> v == null ? 1L : v + 1));
        System.out.println(stateCounts);

        // classic scenario for using compute method:
        Long curPopulation = stateCounts.get("CA");
        if (curPopulation == null) {
            stateCounts.put("CA", 1L);
        } else {
            stateCounts.put("CA", ++curPopulation);
        }
    }
}
