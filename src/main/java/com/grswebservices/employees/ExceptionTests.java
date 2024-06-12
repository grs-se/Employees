package com.grswebservices.employees;

public class ExceptionTests {
    public static void main(String[] args) {
        doSecondLevel(0);
        System.out.println("You made it to the end");
    }

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
