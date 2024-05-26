
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

### Records
- Java trying to keep things relevant
- Shorthand version of a class
- Boilerplate code - code that can be generated - code that isn't really adding value to anything - just the starting point
- the true value of object-orientation is the combining of data with behaviour together to allow you to model the real world or business concepts.
- when we just use an object oriented language like Java to just model data we're really missing out on a lot of the value
- sometimes there are programs that we need to write where thaere just arent many opportunities to get into awesome modelling and all we really needed were data holders
- however the architects of Java have become increasingly aware that there is negawtive sentiment with regards to Java and so they've come up with a solution of sorts - basicalyl a new data type
- this data type is really just a facade on top of a class.
```java
// this is equivalent to the class-based version
public record WeirdoRecord(String lastName, String firstName, LocalDate dob) implements Apple {
}

// RECORD VS CLASS
WeirdoRecord larry = new WeirdoRecord("David", "Larry", LocalDate.of(1951, 1, 1));
WeirdoClass larry2 = new WeirdoClass("David", "Larry", LocalDate.of(1951, 1, 1));

WeirdoRecord jake = new WeirdoRecord("Snake", "Jake");
jake.sayHello();
```
- Java generates for us the getters and setters, an implementation of the hashcode and equals methods, and a basic body of this constructor
- No setter methods with Records. 
- syntax slightly differnet: no larry.getLastName() just larry.lastName()
- no setter methods for record because records are final, not only the record is final, which means it cannot be extended, but the fields are also final, which means once they've been set by the constructor they can't be changed, and sicne they can't be changed after the creation of the obect, there is not point in having a setter method
- Immutability is a sought after quality of code - good idea to keep as much of your data as immutable as possible, unchangeable, create it one time and then it can't be accidentally changed, becaus that's how lots of bugs crop up in code.
- Records build immutability right in
- if you wanted to make a better Weirdo you make a new one
- Sometimes immutability can be a pain but often protects us against ourselves
- Records can implement an interface
- can create your own constructors and methods
- shorthand versions of classes - so that you can dispense with most of the boilerplate of classes but cant exntend any other classes
- pro Java development in business developer involves creating lots of shallow classes that model very simple concepts liek a Person, and it's rare that any one on teams where this type of JAV development takes place is trying to arhcitect really well thought out class hieracrchies where this extends that and this implements that and that fancy stuff, may not be super common in a lot of Java shops, and so using a record to do basic data carrying, go out tot eh db go grab some information plop it into some data holder, which in this case would just be this simple Java class or record, and then send it on its way to be utilized in some other wya, maybe it will be presented to a screen or webpage, sot hat s one of the real itnended use cases of records. 
- can define static fields on a record
- cannot be abstract because only thing you can do with an abstract class is extend it
- can be nested
- maybe be cautious making all models records.
- might not be a lot of teams that even know about records because they're new as of 2021.

### Lambda Expressions
- completely different paradigm to OOP - a concept popularised by languages that follow a functional paradigm
- Objects are the main currency of OOP - in functional programming functions are
- In Java prior 8 methods could not live on their own outside of a class
- With inclusion of Lambdas we now have the ability to do this and that in turn  allows our code to get much more concise
- To keep it easy Java is expecting that we're referign to an interface with only 1 method on it with the same signature as the lambda expression, 
- so we don't have to create all the boilerplate - new class, etc etc. 
- as minimal as can be.
- "magical"
- lamdbas work so perfectly in line with all the other concepts that have been leading up to here, with nested classes, inner classes, anonoymous classes, and the most logical conclusion of all of that in many cases is the Lambda - we just didnt have access to those before Java 8.

### Composition vs Inheritance
- although can't extend from multiple classes, can be composed of cmultiple classes
- delegate methods - methods that mirror the public methods of the Pilot class, just passing through
- if I want a class to be able to implement and sudo-inherit functionality from multiple other classes that are otherwise unrelated I can still do that with this technique of composition
- composition: CEO is composed from Pilot instance, and could have 1000 other classes and could compose CEO of those other classes too
- altought you do need to explicity add al delegate methods - but IDE can do this for you
- composition gets around problem of lack of multiple inheritance
- Java developers will say that we should actually prefer compositon over inheritance - better generally to imbue classes with functionality from other classes using this technique of composition, rather than just extending a class.
- in general if you wrote this super class and you are also are writing the potential sub class and you want your subclass to extend your super class and you wrote both of them, generally speaking go for it! Not many levels deep though, recpie for disaster, keep it relatively shallow, 3 levels at max.
- if you wrote everything in that hierarchy and wrote it intending to have a hierarchy you're fine.
- where this favouring of composition over inheritance really comes into play is in a different scenario where you did not write the class that you are considering extending, the class might be from ather developer from another team, even from another organisation, and ypou dont necesarily know if the author of that class intended it to be extended.
- sometimes frameworks creates classes that are itnended to be extended
- tread carefully - you may be making assumptions about how the methods in thtt class are operating, at any time the author of htat calss could decide to change those implementations and you may receive those upadtaes and your assumptions cause your code to be broken, fragile code.
- recommended if you are author of class and youd otn intend that class to be inherited by yourself or anywone esle you may explicty declare that class to be final, to prevent others from extending your class, to protect them from themsevles essentially.
- however you can still consider endowing your class with the functioanlity of another class by using composition. This is better because you have more control over what's gong on. You ave to explicitly in the delegate methods the other classes method and so you get the chance to override what it's doing, you could do this with inheritance as well, but its' even more explicity when you're using these delegate methods.
- And then obviously it allow you to get around the fact that Java is not gojng to allow you to inherit from more than one class. So Java developers have to pick wisely on what class you want to spend on your one and only inheritance shot, but with composition it doesnt matter, you can compse your class of as many classes as your want.
- Inheritance is a powerful technique that you should not be afraid to use, so if soemone tells you in a blanket statement that you should not use Inheritance your missing out on a lot o what an object oreitend language can giv you.
- When is it a good idea to use Inheritance and when abad idea?
- Why are even using Java if you're not going to use a lot of the oBject Oritended techniques? middle ground. 
- efficient and effective ways
- 
### Default Methods
- newer technique that you could consder using to get around the lack of traditional support for multiple inheritance in Java, though caution, tread lightly if using this. 
- wasn't primarily intended for getting around multiple inheritance, intended for somethign else.
- As of Java 8 interfaces have been expanded in tersm of what they can do and so now there is a way to supply an implementation with methods in interfaces.
- caveat: with interaces can't inherit data, can't hold state, which is a major aspect of oop and classes. When we put a field on an interface that field is made final by Java, and so can't change the value of it. default method can retrieve it but cant change it,
- original use case for default methods in interfaces: pre existing classes that implement interfaces, don't want to add impementations of new methods to all classes that implement interface, and so add implementation to interface.
- what is the difference between default methods on interfaces and abstract classes? extremely similar: abstract class is a class, one that cant be instantiated on its own, it can maintain state, can put instance variables or fields, whereas intrerfaces you can't, variables are final.
- beware using this technique for multiple inheritance on commercial projects where lots of developers.
- This is meant for people who are making frameworks and APIs, not so much intended for end user developers who writing in house code that they completely own on their own.
- if you own all the code othen you can design the code in such a way where either you wont needmultiple inheritance because you can just dbuild the functioanlity that you need right in to the classes where you need them in the first pace, or you can always use compositon. 