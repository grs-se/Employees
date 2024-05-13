package com.grswebservices.employees;

public interface Employee {
    public int getSalary();
}

//////////
// INTERFACES
//////////
// related to classes, like hollowed out classes
// describe some or all of the public methods of a class
// hide or abstract details of one or more classes
// one or more classes can have a relationship to an interface
// classes implement an interface
// so that when other code wants to use those classes that code doesnt have to know specifically what classes they are, but can just know the interface
// like electrical outlets at wall - interfaces devices you want to plugin to the electricity your house is carrying - gives a standard way to interface with that electricity
// also serves to abstract away some of the complexity of interacting with the electricity because you certainly could just have the bare wires that are behind that outlet exposed and someone could splice those wires together, but that would be a level of implementation detail that most poeple would not want to get into
// when you identify methods that are in common between classes that can be a sign that you could abstract those classes behind an interface
// because an interface defines all of the public methods that you might want a class to implemenet we don't need the public keyword. It's not wrong, but we don't need it.
// general purpose of an interface is to simply describe what methods should be implemented in classes of this interface, so it's just a description for the most part, althoguh we can provide implementation details in certain cases (new feature - historically just a description).
// "public interface"
// can have lots of methods on an interface
// for every method that you define on an interface, all of the classes that implement that interface must also implement those methods, if it is a concrete class.
// abstract classes don't have to implement the methods of an interface
// classes can implement multiple interfaces
// you can then refer to instances of a class by any of its interfaces
// if you have multiple classes that are not related to each other, in other words they don't all inherit from the same parent class, they're completely seperate classes, but they do all implement some version of the same function or functionality, in our case the programmer, manager, CEO, and analyst, all are capable of calculating a salary, but they're not related to each other, they just happen to have this ability to calculate a salary, you can make those classes all look like they are the same type by making them all implement that interface
