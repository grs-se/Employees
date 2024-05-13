
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

