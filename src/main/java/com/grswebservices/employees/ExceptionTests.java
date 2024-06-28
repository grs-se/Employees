package com.grswebservices.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExceptionTests {
    public static void main(String[] args) {
        try {
            Files.lines(Path.of("blahblahblah"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("We were unable to open the file.");
        } finally {
            System.out.println("Make sure this runs no matter what...");
        }
    }
    // be careful with this
    // throws IOException will just result in the programming dying
//    public static void main(String[] args) throws IOException {
//            Files.lines(Path.of("blahblahblah")).parallel()
//                    .limit();
//    }

//    public static void main(String[] args) {
//        //        doSecondLevel(0);
////        System.out.println("You made it to the end");
//    }
//


    private static void doSecondLevel(int num) {
        String[] array = {"one", "two", "three"};
        try {
            System.out.println(array.length / num);
            System.out.println(array[3]);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException exception) {
            System.out.printf("Exception type: %s. Message: %s%n", exception.getClass(), exception.getMessage());
            exception.printStackTrace();
        }
    }
}
