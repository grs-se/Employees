
# NOTES: Object-Oriented Programming

## INTERFACES

- related to classes, like hollowed out classes
- describe some or all of the public methods of a class
- hide or abstract details of one or more classes
- one or more classes can have a relationship to an interface
- classes implement an interface
- so that when other code wants to use those classes that code doesnt have to know specifically what classes they are, but can just know the interface
- like electrical outlets at wall - interfaces devices you want to plugin to the electricity your house is carrying - gives a standard way to interface with that electricity
- also serves to abstract away some of the complexity of interacting with the electricity because you certainly could just have the bare wires that are behind that outlet exposed and someone could splice those wires together, but that would be a level of implementation detail that most poeple would not want to get into
- when you identify methods that are in common between classes that can be a sign that you could abstract those classes behind an interface
- because an interface defines all of the public methods that you might want a class to implemenet we don't need the public keyword. It's not wrong, but we don't need it.
- general purpose of an interface is to simply describe what methods should be implemented in classes of this interface, so it's just a description for the most part, althoguh we can provide implementation details in certain cases (new feature - historically just a description).
- "public interface"
- can have lots of methods on an interface
- for every method that you define on an interface, all of the classes that implement that interface must also implement those methods, if it is a concrete class.
- abstract classes don't have to implement the methods of an interface
- classes can implement multiple interfaces
- you can then refer to instances of a class by any of its interfaces
- if you have multiple classes that are not related to each other, in other words they don't all inherit from the same parent class, they're completely seperate classes, but they do all implement some version of the same function or functionality, in our case the programmer, manager, CEO, and analyst, all are capable of calculating a salary, but they're not related to each other, they just happen to have this ability to calculate a salary, you can make those classes all look like they are the same type by making them all implement that interface

## CLASS HIERARCHIES
- properties and methods of a super class can be inherited by sub-classes (child classes) as long as those methods and properties are visible (protected or public)
- what is left in sub-classes are just the fields and code that are unique to that particular sub-class, and sub-class constructor is only concerned with what it takes to parse out those fields and so those on its internal fields.
- all of the other parts - what it takes to parse out the dob, firstname, lastname - common to different employee subtypes therefore we put all that code intot he employee superclass and handle it universally for all employee types.
- in order to benefit from that all sub-classes need to call super(). When doing hierarchical coding have to remember to call super()  
- Easy to forget to call super() (Java should make it automatically)

### Super-Class
- used to pull up any potentially duplicated code into one central place in the class hierarchy

### Compare and Contrast Interface approach vs SuperClass or Hierarchy approach
| Interface                                                                                                                                      | SuperClass or Hierarchy                                                                                                                  |
| ---------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| Lightweight                                                                                                                                    |                                                                                                                                          |
| Makes sense if each of the classes don’t have implementation details common to each other, and also don’t have data in common with each other. | If classes DO share data and or implementation details then consider introducing superclass, especially if you have duplication of code. |
| Can implement multiple interfaces                                                                                                              | Can only extend one class                                                                                                                |

---
- Object orientation: Clean up code, simplify it, get rid of duplication.
- Java classes does not allow ‘multiple inheritance’ only ‘single inheritance’ – doesn’t bring enough value to overcome all the headaches. 
- But to meet us in the middle the creators of Java did allow multiple interfaces.
- Classes can only extend one class but can implement multiple interfaces.

### Dealing with the Null Case
- everytime we add a conditional block in it increases "Cyclomatic complexity" (is a software metric used to indicate the complexity of a program).
- New hotness in Java: better to return an optional rather than a null
                  
## Abstract Classes
- when we implement an interface on a class we are forced to make sure that we have an implementation of the methods on that interface
- when we implement that method we (or IDE) can flag it with an override annotation 
- this won't work if Employee class is abstract, as abstract classes are not meant to be instances of
- meant only to serve as a super class for other classes, but not meant to create standalone object instances on its own
- Error: 'Employee is abstract cannot be instantiated';
- Good for: if i have a method that I want to make use of in the superclass for example but I don't want to implement it in the superclass, I want to make sure that sub class provide an implementation of that method
- i first must declare that superclass as abstract, and secondly declare the method as abstract too, now the mehtod cannot have an implementation.e
- looks like a method defined on an interface - like a hybrid of a regular superclass and an interface together.
- flag this method so that subclasses of this employee class must implement this method in order for the class hierarchy to be valid.
- can't accidentally forget to implement this, because whenever a new programmer comes along someone they are forced to implement  the getSalary method -
- "template method pattern" - design pattern

### Interface vs Abstract vs Concrete SuperClass
#### Interface
- multiple classes that implements one or more methods that implement slightly differently
- don't share data or implementation but do share some general characteristic
- interface can tie them all together so that cabe interfaced by client code (Main class)
- refer to all these classes via the interface

#### SuperClass
- two or more classes that share some of the same data and or some of the implementation 

#### Abstract Class
- v similar to superclass - but don't want to permit anyone to instantiate the abstract class itself
- want to force some classes to implement certain methods

### Factory Methods
- we want to always ask ourselves where is the most appropriate place for certain code to live and how can we get to the point where any given class has as fewer responsibilities as possible and ideally just one responsibility.
- Number.getCurrencyInstance() = getCurrencyInstance = a factory method (public static final), NumberFormat is an abstract class.
- static members cannot directly interact with non-static members, because static members exist without an instance of the class being created, but these non-static members can only exist when an isntance of the class is created
- so it's like the static method exists before, during, and after the creation of any class instances so how can this static method refer to a field which itself will only exist when an isntance o fthis class is created
- the problem is that the mathcer we need to use in the constructor can only be created at the time when we're rnning the constructor, and tha'ts a slightly differnet timing to when we are creating this EMployee 
- want to force everyone to only use this factory method then make constructors protected
- 
### Nested Classes
```java
// NESTED CLASS
// minimal - only implementing getSalary method
// private because no external code needed to instantiate it
// static because of way compiler handles nested classes - highly recommended
// inherited no arg default constructor is called
// final because we prevent any other class from extending it
// read 'Effective Java' by Joshua Bloch
private static final class DummyEmployee extends Employee {
    @Override
    public int getSalary() {
        return 0;
    }
}
 ```
#### Other types of nested classes
- few different kinds of nested classes:
- static nested class
- non-static nested or 'inner' class
- when no static = inner
- if your nested class needs to access fields of the enclosing class then you may need to consider using an inner class. 
- one of the main differences between a static nested class and an inner class is the fact that since a static nested class is static it is not allowed to access fields of its enclosing class.
- because fields of that class only exist once you create an instance of that class but static members of that class exist prior to, during and after the class has been instantiated
- however, non-static nested classes or inner classes actually can access their enclosing classes members. 
- So that would be one reason why you would want to create an inner class - if your inner class actually needed access to the enclosing member fields or methods.

#### Anonymous Class
- if I only wanted to instantiate DummyClass once, then could have used an Anonymous class.
- without a name
- all other respects similar 
- don't need private static final class because can't even refer to it - has no bearing.
- you refer to either an Interface or a SuperClass that this new anonoymous class is a subclass of.
```java
// Anonymous class
default -> new Employee() {
    @Override
    public int getSalary() {
        return 0;
    }
};
// default -> new DummyEmployee(); 
```
- Anonymous classes do both the declaration and the instantiation all in one
- syntax with new operator and what looks like a constructor, curly braces and defining methods inside, so all wrapped in one.
- could also have done this with the interface IEmployee
- One-off
- Anonymous classes look noisy - not a huge fan, but have a long history of use in Java particularly in graphics coding.

#### Local Nested Class
- never really use them in 20 years of pro experience
- local variable is a variable defined in code block
- can also define an entire nested class inside of a method
- Narrow scoped class
- not adviseable - looks like too much noise, although limiting the scope is good

#### Overview
- Probably never going to be wrong if you stick with using standard classes but in cases where you dont want others to be able to instantiate the class you can look at nested classes as an alternative.
