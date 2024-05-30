# COLLECTIONS

- Collections are dynamic
- Collections have methods to add, subtract, filter, etc.
- Arrays not dynamic - do not have methods, can't change size of array but have to create new array.

## Lists:
- V similar to arrays in terms of functionality
- store strings, numbers, objects
- iterate over them or pass them around from method to method
- storing objects in order - main distinction of list compared to other types of collections
- List is an interface - so have to create implementation of class that implements the interface
- LinkedList and ArrayList - arrayList most popular - most devs go for arrayList to store generic collection of objects
- ArrayList uses an actual array - difference, array you have to initialise its size on creation and then cant change it's size
- List can grow or shrink dynaically as needed
- Lists can only hold objects not primtives - arrays can be ysed to tore primtiives, Lists cannot, have to store actual objects - not big deal, can just wrap objects in primtive wrapper -classes - though this is more heavy weight
- should genereally prefer to store object instances in a more generic type, or to store object instances in interfaces, i.e. List arrayList = new ArrayList(); not ArrayList arrayList = new ArrayList();
- typical pattern : in one part the program we will populate the collection with some data, and then in another part of the program we will iterate over the data in that collection to process it in some way

```java
// Generic type
List<IEmployee> employees = new ArrayList<>();
```
## Linked Lists:
- By using interfaces to store our variables it gives us the flexibility to swap in and out different implementations of that interface
- No errors. Get all of deferring benefits of a LinkedList over an ArrayList without having to change any code, because I used a more generic Interface type for the variable, and that is the power of coding to interfaces as opposed to implementation types. 

```java
List<IEmployee> employees = new LinkedList<>(); 
```

- ArrayList uses an array internally
- LinkedList does not use an array internally - instead requests individual pockets of memory for each element that we add to it and as it requests new memory locations it stores the address of the new memoruy locations in the previous memory locations, so it forms a linkedList.
- if we're got memory location A and then linkedList requests from the computer additional memory for location B, location B might be given an address of 20, and a 10, and so the linkedList will take the address of b and store it in a special pocket of storage in lcoation A, and that way when the linkedlist has to traverse the elements in the linkedList it can do so by simply followign the trail of addresses one at a time.
- if you're in element A and you need to get to element B it can look inside element A and see that the next adrdress of B is 20, and then it can jump to addres 20 to find B, and so on.
- Additionally LinkedList goes the other way as well. So not only does it store the next location in the previous location it also stores the previous location in th enextlocation so it's waht we call a "Doubly LinkedList".
- That makes it easy and fast to traverse forwards and backwards.
- One of the advtanges of a LinkedList is that it can work with a constant amount of time unlike a an ArrayList which uses an array to store its data, when the internal array of the array list gets full the array list has to create a new bigger array and then copy all the data from its orignal array  tot he new array and then let that oroginal array get garbage colected and proceed. If dealing with 0000000's of obects that could takes timet to do. 
- LinkedList more efficient in that regard. However a potential downside tot he linkedlist is that every single time you addanitem to the linkedList it maky need to take a hit of requesting new memoru allocation from the computer. May not do this everytime, may request some up front, but not go as far in advance as an an arraylist.
- Requesting memory from the computer and allocating obkects is considered to be an expensive operation - so need to pick which scenario do you want to tae the hit on.
- In general it's true everywhere that it's better to code to interfaces instead of being more specific
- enhanced for loop is primary preferred way of iterating
- ConcurrentModificationException = common error, novice devs: trying to remove something from the list while looping over the list, trying to change a tyre in the car while the car is moving. JDK - two or more threads. One thread loops over collection, but then another tries to remove items from the collection, and Java senses that something like that could be happenng, chaotic.
- ask first thread to remove when it has time.

## Looping with Iterators
- Another type of loop to iterate over a collection which enables us to remove items from that collection. 
- Enhanced for loop gives us the indvudal elements of a collecitoon, prefered loop in most scenarios, unless you need to remove items from the colletcion while you are iterating over them.
- In that case use another for loop which is closer to more traditional for loop but uses an iterator.
- 2 threads
- the enhanced for-loop uses thsi iterator internally
- collections have the ability to create an iterator for us and so that's what we put in here
- farily old school way of for lopp, not usually recommended, but if you have to remove items from a collection while loopong over collection, this is way to do that.
- increment section leave blank 
- iterator is not actual worker, so need to pull worker out of iterator
- .next() operator gives us the next element int he colleciton.

```java
// remove an element from a collection while iterating over the collection (traditional loop)
// better ways to do this
private static void removeUndesirables(List<IEmployee> employees, List<String> removalNames) {
    // old school way, lambdas make this easier
    for (Iterator<IEmployee> it = employees.iterator(); it.hasNext();) {
        IEmployee worker = it.next();
        if (worker instanceof Employee tmpWorker) {
            if (removalNames.contains(tmpWorker.firstName)) {
                it.remove();
            }
        }
    }
}
```
- try to keep methods short - 10 - 20 lines if possible
- can get an iterator from a Set

## Additional List Methods
- List.of() = unmodifiable list - can't add or remove
- considered dangerous or error prine to have objects including collections that can be modified after they have been initliaized
- Java has been adopting functional paradigms.
- can wrap List.of() in ArrayList and now this is modifiable

```java
private static List<String> createUndesirablesList() {
    return new ArrayList<>(List.of("Wilma5", "Barney4", "Fred2"));
}
```
- retainAll()
- set() - replace element in list

### List.contains() & Object.equals()

```java
 // List.contains() & Object.equals()
IEmployee myEmp = employees.get(5);
System.out.println(employees.contains(myEmp)); // true

IEmployee employee1 = Employee.createEmployee("Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}");
System.out.println(employees.contains(employee1)); // false - duplicated an employee verbatim using the same data pamameters but we get false

// compares individual fields
@Override
public boolean equals(Object o) {
    // if this object points to the same memory location as that object they are equal, don't need to test anymore
    if (this == o) return true;
    // if this other object that is being passed in for equality testing is not even of the same type as this object that I am in (Employee), then failed test.
    if (o == null || getClass() != o.getClass()) return false;
    // by this stage we know they are of same type and so we can cast this o to the type Employee so we can start drilling into it's fields which we couldn't do as an Object
    Employee employee = (Employee) o;
    // if all equal then return true
    return Objects.equals(lastName, employee.lastName) && Objects.equals(firstName, employee.firstName) && Objects.equals(dob, employee.dob);
}
```
- can't change the signature because then you're not overiding the equals method, you're creating your own equals method 
- the contains method only knows to look for the inherited equals method with this exact signature 
- so now cast generic Object ijnto specific type Employee

---

### Implementing Comparator to Sort Lists
- comparator - sorting algorithm (technique) - merge-sort - a number fo strategies of how to sort things

```java
private static void sortEmployees(List<IEmployee> employees) {
    employees.sort(new Comparator<IEmployee>() {
        @Override
        // comparator - sorting algorithm (technique) - merge-sort - a number fo strategies of how to sort things
        // returns an integer of which one comes first or second, o1 comes before o2? = -1, o2 before o1 = 1, both equal = 0
        public int compare(IEmployee o1, IEmployee o2) {
            // if o1 is an instance of Employee then go ahead and create a variable of type Employee and use that recast o1 as an actual Employee
            if (o1 instanceof Employee emp1 && o2 instanceof Employee emp2) {
                // if lastNames aren't equal then sort by lastName but if they are equal then sort by salary
                int lnameResult = emp1.lastName.compareTo(emp2.lastName);
                return lnameResult != 0 ? lnameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
                // return emp1.dob.compareTo(emp2.dob);
            }
            return 0;
        }
    });
}
```
- a lot of Java most commn data types actually already have a method on them which we can delegate down to to make the determination of which comes first or second
- all of this can be achieved much easier with lambdas.

```java
private static void sortEmployees(List<IEmployee> employees) {
    // Lambda version
      employees.sort((o1, o2) -> {
        if (o1 instanceof Employee emp1 && o2 instanceof Employee emp2) {
            int lnameResult = emp1.lastName.compareTo(emp2.lastName);
            return lnameResult != 0 ? lnameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
        }
        return 0;
    });
}
```

### Implementing Comparable to Sort Lists
```java

public static <T> void sort(List<T> list, Comparator<? super T> c) {
        // THIS IS CALLED...
        list.sort(c);
    }
    
private static void sortEmployeesV3(List<IEmployee> employees) {
    // ...BY THIS
    Collections.sort(employees,(o1, o2) -> {
        if (o1 instanceof Employee emp1 && o2 instanceof Employee emp2) {
            int lnameResult = emp1.lastName.compareTo(emp2.lastName);
            return lnameResult != 0 ? lnameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
        }
        return 0;
    });
}

```
- "computer natural ordering" - doesn't perceive 10 but 1 followed by 0, so why 10 often comes before 2 in natural ordering
- what does it actually mean to naturally order Employees?
- If we want our class Employee to be sortable with minimal effort on our part, from the perspective of calling the .sort() method, we need to implement the Comparable interface.
- a functional interface only defines one method. More than one abstract method, can no longer use interface with lambda expressions.
- if you don't want to have to define and create your own comparator you can avoid doing that, however the objects you plan to compare have to implement the Comparable interface

```java
public interface IEmployee extends Comparable<IEmployee> {
    int getSalary();
}
```
- string, dates, numeric wrapper classes - all implement Comparable - 790 classes implement Comparable
- more algorithms for sorting, for instance Collections.shuffle();

### Intro to Sets

#### Lists:
- A List is an interface that models a data structure that can hold a number of items or objects in some type of order 
- and we can access or get those items back out with an index. 
- Lists are all about keeping items in a list. 
- They can grow dynamically.
- ArrayLists, LinkedLists

#### Sets:
- models a structure that can also hold a number of items or objects however a set tries to guarantee there will be no duplicates.
- depends on how we wrote our code
- equals and hascode methods are more relevant with Sets
- Three primary implementations of the Set interface in Java, we have most commonly used which is HashSet, then TreeSet and LinkedHashSet.
- HashSet = fastest default implementation of Set
- all of the interfaces and classes that are related to Collection have enough in common with eachother that a lot of the time you don't need to make major changes in the code if you coded wisely to begin with
- Lists and Sets have a lot of methods that are common to eachother which is convenient
- Sets automatically filter out duplicates if code is written properly
- Items not comming out of loop in the order in which we added them and that is typical of the HashSet implementation of a Set. It does not guarantee a predictable order for the items that you're adding
- The reason for that is due tot he way the HashSet actually works. HashSet associates a so-called HashCode with each item that is added into it. This HashCode in turn can be added to a mathmatical process to derive an index which is really just a number, and that index or number becoems a real number in a table.
- a HashSet associates each item in it with a HashCode. A HashCode is really just a number htat should be fairly unique for each item that we're adding to the hashset, thoguh there can be so called collisions where two or more items result in the sam HashCode. 
- HashCode in turn is used to generate a row number which is effectively an index and it is by these indexes that the items that have been added to the HashSet are ultimately ordered. And because that happens via the hashcode genreally speaking th order in which we add items to a hashset is not the order in which they come back out when we're iterating over it, that's ebcasue those items result in a hashcode and that hashcode results in an index and only by that index is everything ordered generally.
- Under the hood the HashSet is actually utilizing another sturcture called a HashMap: basically a list with two columns (in this case)
- - first column has HashCode which are also though of as a Key (key = way to look up something. A smaller simpler represnetation of the bigger thing)
- Why do we bother with a HashCode at all? A List could just be used with just 1 column which is just the objects themselves. 
- - the issue here is when we want to use this type of collection to do a couple of types of operations: 1 to call the contains() method.
- - Let's say that we have an object in hand and we want to ask this collection if the object is contained within it. If we were just using a List what would generally have to happen is some code would have to iterate over each row in that List calling the equals method to detemrine if the object in hand is equal to the current object that we are iterating over and that code might have to oterate over 10000000 items, could take a while to findt he match, ssame true for the remove method. Amount of time that can is not constant. If the item you ar tying to find or remvoe is toward the end of the list then that will take longer than if it was at the beginning of the list adn you have to sit there and wait for it iterate through most of the list. 
- - HashSet - we first determine the hashcode for the item that we have in hand, then the hashset will take that hashcode and determine an index from that hashcode, and so now the code does't need to iterate over each and every item in the collection it can just jump straight to the 10th row and then simply determine if the item in hand is equal to the item in that 1th row and if so then you've got a match and then you can say it does contain or whateber.
- - beauty of that approach is whether you find a match on the first element or the 1000000000000 element it should take the same amount of time to find that match and so the access time is constant with a HashSet. And that's a huge advantage particularly if the collection is very large.

### Sets & HashSets
- IDE doesn't give you the option to generate a hashcode() without an equals() or vice versa. 
- Whenever creating a class always a good idea to generate an equals and hashcode, even toString() method as well, advice straight from godfather of Java programming Joshua Bloch,
- not necessary to know in low level how hashcode() method is working
- what underlying properties of this class do you want me to use to generate this hashcode, this is important because what we're trying to do wwith this hascode is to essneitalyl generate a number, in this case an itneger, that will hopefully be v unique for any combination fo propeties for an an instance of this class.
- in the real world what would it take to hopefully ensure that any employee is unique and differentiated from other employees of a company. Generalyl speaking your not likely to have a high level of collisions if you take into accout an employees firstname, lastname and dob, there shouldbt be an lot of collisison or any at all, and so these become great properties on this particular clasto use for generating a hashcode. 
- So ideally what we want the hascode method to do is that for the combination of properties that we care about, that we think will ensure a level of uniqueness for various instances of our class, that the hashcode method would return a unique number .
- conversely, if you did have two isntances of any employee that did happent ot have the same firstname, lastname, dob, then definitely the hashcode for those two instances should match, otherwise we've got problems.

```java
   @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dob);
    }
    
    // jumps into ...
    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }
    
    // in turn jumps into ...
    public static int hashCode(Object[] a) {
        if (a == null)
            return 0;

        int result = 1;

        for (Object element : a)
            // if element isn't null then calls elements own hashcode method and then adds that to a running total which is stored in 'result' and then multiplies that by 31 and then keeps doing that for each element
            // so passing in lastname, firstname, dob, this for loop would first hit lastname, call the hashcode on the lastname, lastname is a string, so hascode method of the String class is going to get called, that will take into account all of the letters in whatever that lastname is and that will get added to the result, added to 1 because result is intialised to 1.
            // and then whatever that result is, that will be multiplied by 31, stored in the result again, then go to firstname, get the hashcode for firstname, add that tot he running total and multply by 31, and keep gojng.
            // result returns a fairly large number because we kept multiplying some number by 31
            result = 31 * result + (element == null ? 0 : element.hashCode());

        return result;
    }
```
- never needed to generate custom hashcode.
- try to ensure thate hashcode generated is unique and minimal number of collisions
- if we disable hascode() method even though we are using HashSet to store employees it's behaving as the code behaved when we used a List.
- What's happening here? some might asusme that because we got rid of the hashcode method on EMployee class that there may not be any hashcode beign generated for any of our emloyees but that is actually not the case. We are still gerating hascode for our employees however we're now using the default implementation for the hascode method.
- all classes derive from the Object class and the Object class gives us default implemetnations of toString, equals, hashcode. The default implemtation of thehascode method actualyl gernates it's hashcode ina p arotcular way, it attempts to ensure that the hashcode should ideally return a unique value for each object. 
- default hashcode method not looking at any properties, so instead essentially returns a randomly generated number of sufficient length to reduce collisions, and if each and ever one of the hascodes are unqiue then none of them will get filtered out.
- default hascode implementation arguably not that useful. 
- a Set or specificalyla HashSet is only as good at filtering duplicates out as your code allows it to be. So when you remove a meaningful implementation of the hascode from the class the hashset is no longer able to meanigfully filter out duplicates

---
- Hashcode and equals methods need to work well together, whcih is why IDE generates them together
- they need to ideally be using the same concepts of uniqueness. 
- If each of these duplicates results in the same hashcode with a good hashcode implementation then once one of them is stored in teh hashset then when the hashset encoutners one of these duplicates it will take that duplicates hashcode, it will determine if that hashcode already exists in its hastable, and when it finds that there is a match, it's not done yet, ut will then call the equals() method to compare the duplicate object to the object that's already in the hashtable and if they are equal then it is at that point that the hashset knows it does not need to store this duplicate, it can just ignore the duplicate.
- So that's why it's so important for the hashcode() and equals() methods to be implemented essentially in tandem and for those methods to be used in the same philosophy.
- If your equals method cares about firstname, lastname, dob then the hashcode method should also care about those same three fields specifically. 