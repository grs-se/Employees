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