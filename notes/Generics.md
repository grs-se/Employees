# Generics

- most commonly used with Collections, though can be used elsewhere
- allow us to constrain the types of data that can be associated with the class
- List<Person> 
- if we then try to add objects to that list that are not of that type the ide, java, etc will let us know we cant
- allow us to avoid having to cast types
- can use generics on methods that return data types
- protect us from certain types of runtime errors
- we can make our own classes generic
- we can make the repository class generic, so that for a given instance of a repository we can only use one data type, so if you want to use another data type you have to create another instance of the repository
- Repository<T> = tells Java that this class is generic, but this alone doesnt; do anythign, we must use this T in various places where we were hardcoding the data type, we dont just want our repo to be able to store and work with strings we want it to be able to store and work with whatever data type we specify later on.
- List<T> = interesting because we are going 2 levels deep into generics, we're delaying telling the List what it's data type is going to be

```java
// poor man's implementation of a repository
public class Repository {
    private List<String> records = new ArrayList<>();

    List<String> findAll() {
        return records;
    }

    String save(String record) {
        records.add(record);
        return record;
    }

    String findById(long id) {
        return records.get(Long.valueOf(id).intValue());
    }

    public static void main(String[] args) {
        Repository repo = new Repository();
        repo.save("House");
        repo.save("Tree");
        repo.save("Boat");

        System.out.println(repo.findAll());
    }
}
/// becomes....

public class Repository<T> {
    private List<T> records = new ArrayList<>();

    List<T> findAll() {
        return records;
    }

    T save(T record) {
        records.add(record);
        return record;
    }

    T findById(long id) {
        return records.get(Long.valueOf(id).intValue());
    }

    public static void main(String[] args) {
        Repository repo = new Repository();
        repo.save("House");
        repo.save("Tree");
        repo.save("Boat");

        System.out.println(repo.findAll());
    }
}
// [House, Tree, Boat]
```
- nothing breaks and this is by design by the creators of Java
- this generic functionality was actually added later into Java in version 5, so the creators knew that if they added this type of powerful funtionality then they needed to implement it in such a way that it would not break existing code.
- So we can instantiate instances of this class without any generic mentioning and so this code that was workign previously is still working right now.
- one of the reaosns why this can work is that at the end of the day Java essentially just erases this information and replacing it witj Object.

```java
public class Repository<T> {
    record Person(String firstName, String lastName){}
    
    private List<T> records = new ArrayList<>();

    List<T> findAll() {
        return records;
    }

    T save(T record) {
        records.add(record);
        return record;
    }

    T findById(long id) {
        return records.get(Long.valueOf(id).intValue());
    }

    public static void main(String[] args) {
        Repository<String> repo = new Repository<>();
        repo.save("House");
        repo.save("Tree");
        repo.save("Boat");
        
        Repository<Person> pRepo = new Repository<>();
        pRepo.save(new Person("Jake", "Johnson"));
        pRepo.save(new Person("Mary", "Johnson"));
        pRepo.save(new Person("Jerry", "Johnson"));

        System.out.println(repo.findAll());
        System.out.println(pRepo.findAll());
    }
}
//[House, Tree, Boat]
//[Person[firstName=Jake, lastName=Johnson], Person[firstName=Mary, lastName=Johnson], Person[firstName=Jerry, lastName=Johnson]]
```
- We didn't have to change the underlying class of repo other than to just make it generic, that's one of the beautiful aspects of working with generics
- interfaces can also be generic
- when we are creatign generic classes our generic types can have more than one parameter.
- we can have multiple generic parameters
- some of the functional interfaces work with 2 and maybe even 3 parameters.
- we can add another parameter to our genereic repository type and that parameter will be used to specify the type for the ids

```java
// poor man's implementation of a repository
public class Repository<T extends Repository.IDable<V>, V> {
    record Person(String firstName, String lastName, Long id) implements IDable<Long>{}
    interface IDable<U> {
        U id();
    }

    private List<T> records = new ArrayList<>();

    List<T> findAll() {
        return records;
    }

    T save(T record) {
        records.add(record);
        return record;
    }

    T findById(long id) {
        return records.stream().filter(p -> p.id().equals(id)).findFirst().orElseThrow();
    }

    // consider psvm to not be part of Repository class
    public static void main(String[] args) {
//        Repository<String> repo = new Repository<>();
//        repo.save("House");
//        repo.save("Tree");
//        repo.save("Boat");

        Repository<Person, Long> pRepo = new Repository<>();
        pRepo.save(new Person("Jake", "Johnson", 10L));
        pRepo.save(new Person("Mary", "Johnson", 20L));
        pRepo.save(new Person("Jerry", "Johnson", 30L));

        Person foundPerson = pRepo.findById(30L);
        System.out.println(foundPerson);

//        System.out.println(repo.findAll());
        System.out.println(pRepo.findAll());
    }
}
// Person[firstName=Jerry, lastName=Johnson, id=30]

```
- type T is "bounded" in that it has to be something that extends / implements the IDable
- occasionally in Java you will see interfaces that dont implement a method at all, they care called "flag interfaces" - and their purpose is simply to flag that this class is of a certain type
- you can really really constrain your generic types if you want to or need to
- by using generics not needing to cast anything and actually keeping utilzation of repository class relatively simple, even though implementation may look complicated
- most Java developers in a company are not having to do a whole lot of this: when you're using classes that are as generic as this using all these fancy techniques, then you're typically creating APIs, makign the tools that programmers use, most of us not likely to start off ith that
- rarely use any of these techniques

```java
package datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// poor man's implementation of a repository
public class Repository<T extends Repository.IDable<V> & Repository.Saveable, V> {
    record Person(String firstName, String lastName, Long id) implements IDable<Long>, Saveable{}
    interface Saveable {}
    interface IDable<U> {
        U id();
    }

    private List<T> records = new ArrayList<>();

    List<T> findAll() {
        return records;
    }

    T save(T record) {
        records.add(record);
        return record;
    }

    T findById(long id) {
        return records.stream().filter(p -> p.id().equals(id)).findFirst().orElseThrow();
    }

    // generic static functional method
    static <T, V> V encrypt(T data, Function<T, V> func) {
        return func.apply(data);
    }

    // consider psvm to not be part of Repository class
    public static void main(String[] args) {
//        Repository<String> repo = new Repository<>();
//        repo.save("House");
//        repo.save("Tree");
//        repo.save("Boat");

        Repository<Person, Long> pRepo = new Repository<>();
        pRepo.save(new Person("Jake", "Johnson", 10L));
        pRepo.save(new Person("Mary", "Johnson", 20L));
        pRepo.save(new Person("Jerry", "Johnson", 30L));

        Person foundPerson = pRepo.findById(30L);
        System.out.println(foundPerson);

//        System.out.println(repo.findAll());
        System.out.println(pRepo.findAll());

        System.out.println(Repository.<String, String>encrypt("Hello", String::toUpperCase));
        System.out.println(Repository.<String, Integer>encrypt("world", String::hashCode));
    }
}


```