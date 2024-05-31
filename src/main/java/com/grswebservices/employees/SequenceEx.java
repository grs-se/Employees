package com.grswebservices.employees;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;

public class SequenceEx {
    public static void main(String[] args) {
        SequencedCollection<String> words = new LinkedHashSet<>(List.of(
                "one",
                "one",
                "two",
                "three",
                "three",
                "four",
                "five"
        ));

        System.out.println(words.getFirst());
        System.out.println(words);
    }
}
