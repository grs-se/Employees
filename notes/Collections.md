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
