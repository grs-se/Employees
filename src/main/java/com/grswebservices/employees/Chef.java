package com.grswebservices.employees;

public interface Chef {

    String favoriteFood = "hamburger";

    // default methods can specify implementation
    default void cook(String food) {
        System.out.println("I am now cooking " + food);
    }

    default String cleanUp() {
        return "I'm done cleaning up";
    }

    // without default keyword method body not allowed
    // String yellAtPeople();

    default String getFavoriteFood() {
        return favoriteFood;
    }

    // can't do this with default methods - can't set data in interfaces
//    default void setFavoriteFood(String favoriteFood) {
//        this.favoriteFood = favoriteFood;
//    }

//    static int convertUnits()
}
