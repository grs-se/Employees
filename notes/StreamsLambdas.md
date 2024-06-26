# STREAMS & LAMBDAS

## Intro to Streams & Lambdas
- Streams API largely can be thought of as a more streamlined and more advanced way to do a lot of the same techniques that we've been doing with Collections and Loops - so for eample if we want to build a Collection of Employees we want to have some kind of loop that loops over things and then creates a collection of Employees and will pass out our Employee text string for example. 
- Streams API can do the same thing but more streamlined, where we dont have to explicitly create a loop, almost like magic, allows us to engage in a more 'functional' style of programming.

## First Steps into Streams API
- Reimplement some of the functionality in Main.java but with Streams API.
- not using any loops at all.
- people.lines() returns a Stream of strings - essentially breaking down the mulit-line string into indivudal lines, feeds the individual lines of the string into a 'Pipeline', and that pipeline is what we will use to process the strings and do lots of cool things to them, essentially all in one go.
- .forEach() requires a Consumer - a type of functional interface called a Consumer, an interface with one method defined on it that takes input but doen't return any output, so it 'consumes'.

```java
public class StreamsStuff {
    public static void main(String[] args) {
        String peopleText = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=4000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=5000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=6000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=7000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=8000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmerzzzzz, {locpd=9000,yoe=10,iq=140}
            Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
            Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
            Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
            Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
            Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
            Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
            Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
            Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
            Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
            Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
            Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
            Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
            """;
        
        peopleText
                .lines()
                .forEach(System.out::println); // System.out.println("hello"); - prints the string to the screen but it does not return any output
        // .forEach((s) -> System.out.println(s));
        /*
          void printStuff(String s) {
              System.out.println(s);
          }
         */
    }
}

//Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
//Flinstone, Fred, 1/1/1900, Programmer, {locpd=4000,yoe=10,iq=140}
//Flinstone, Fred, 1/1/1900, Programmer, {locpd=5000,yoe=10,iq=140}
//Flinstone, Fred, 1/1/1900, Programmer, {locpd=6000,yoe=10,iq=140}
//Flinstone, Fred, 1/1/1900, Programmer, {locpd=7000,yoe=10,iq=140}
//Flinstone, Fred, 1/1/1900, Programmer, {locpd=8000,yoe=10,iq=140}
//Flinstone, Fred, 1/1/1900, Programmerzzzzz, {locpd=9000,yoe=10,iq=140}
//Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
//Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
//Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
//Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
//Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
//Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
//Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
//Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
//Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
//Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
//Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
//Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
//Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
//Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
//Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
```
- prints each of the lines fromt eh string but one line at a time isntead of printing out entire string as one string.
- lines method able to split multi line string into individual strings for each line.
- definition of a line her eis all of the text hta comes before a newline character (or os char combo - lines method smart enought o undertand those differences)
- feeds each line into a stream
- lines method returns a type of Stream.
- streams methods take output from previous streams method and do further processing.
- each string is being fed into the next method of the pipeline

```java
// Consumer.java
// no doing anything, just defining a method
@FunctionalInterface
public interface Consumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
```
- New syntax = "Method Reference" - it refers to an existing method: input is implied = (System.out::println); // System.out.println("hello"); 
- Java is able to determine that becauase we're refering to this method println that takes one input it should take the indvidual strings one at a time from this Stream of strings and pass them one at a time into a call of the println method

```java
private static void tabulateEmployees(String peopleText) {
    peopleText
            .lines()
            .map(Employee::createEmployee) // converting from string to type IEmployee
            // .map(s -> Employee.createEmployee(s)) // converting from string to type IEmployee
            .forEach(System.out::println); // System.out.println("hello"); - prints the string to the screen but it does not return any output
}
```
- forEach method doesn't return anything as it is a "terminal operation" - the end of the line. Can only be used at the end of a Streams API pipeline.

---
### Creating Streams
- Other ways to create a stream.
- most common place to get a stream started is any of the Collections classes - great support for streams API - even go so far as to say the Streams API was really created with the Collections API in mind

```java
 private static void practiceCreatingStreams() {
        List<String> nums = List.of("one", "two", "three", "four");
        nums
                .stream()
                .forEach(System.out::println);
    }
```
- Any types of objects that would be valid in a Collection can be manipulated or worked with in the Streams API, don't have to be strings.
- generic Stream type doesn't care 
- context aware methods that exist exclusively on these numerical streams such as IntStream - maxNum(), summing up the total of all numbers, cannot do these operations on a generic stream
- generic Stream type doesn't care what type of data is in the stream - can be Cars, Strings, Ints - doens't make sense to call the sum() method on a generic Stream
- IntStream - not dealijng with a Stream of integers, we're dealing with somethig that is its own type, an IntStream, first must get our hands on the idnividual numbers n teh int stream so that we can convert them to something else

```java
   private static void intStream() {
        IntStream.rangeClosed(1,5) // returns IntStream
                .mapToObj(String::valueOf) // mapToObj() - useful for converting values in IntStream to something that is probably not numerical
                .map(s -> s.concat("-"))
                .forEach(System.out::println);
    }
    //equivalent to
    private static void intStream() {
        IntStream.rangeClosed(1,5) // returns IntStream
                .boxed()// returns Stream<Integer> : a stream of integers
                .map(String::valueOf)
//                .map(String::valueOf) // mapToObj() - useful for converting values in IntStream to something that is probably not numerical
                .map(s -> s.concat("-"))
                .forEach(System.out::println);
    }
```
- IntStream is not a Stream, it's an IntStream
- an IntStream doesn't have numbers in it - it has things in it we can;t know about - but it can give us numbers if we either call the boxed() method or the mapToObj()
- boxed() method returns a traditional stream of integers
- boxing our Integers usng the numerical wrapper classes
- wrapper classes are quite expensive - why Java has primitive data types to avoid the expense of having to use full on objects for osmething so fundamnental as numbers
- mapToObject avoids the unecessary step of having a stream of integers and then only to covert them to strings anyway
- 
```java
  private static void arrayStream() {
        String[] names = {"bob", "tom", "jane"};
        Arrays.stream(names)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

```
---
```java
        try {
            Files.lines(Path.of("C:\\Users\\georg\\IdeaProjects\\Employees\\src\\main\\java\\com\\grswebservices\\employees\\employees.txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
```
---
### Summing with Streams
- Map method allows us to convert streams of one type to streams of another type

```java
// NOT VALID   
private static void getSalaries(String peopleText) {
        peopleText
                .lines()
                .map(Employee::createEmployee) // Stream<IEmployee>
                .map(Employee::getSalary) // yet we're calling the getSalary method of the Employee class, not referencing getSalary method declared on IEmployee interface
                .forEach(System.out::println);
    }

// VALID
private static void getSalaries(String peopleText) {
    peopleText
            .lines()
            .map(Employee::createEmployee)
            .map(IEmployee::getSalary)
            .forEach(System.out::println);
}


```
- mapToInt() - returns an IntStream
- sum() is also a terminal operation meaning we cannot finish with both sum and forEach
- Streams gets heavy into functional programming
```java
private static void sumSalaries(String peopleText) {
    int sum = peopleText
            .lines()
            .map(Employee::createEmployee)
            .mapToInt(Employee::getSalary)// Non-static method cannot be referenced from a static context
//            .mapToInt(IEmployee::getSalary)
            .sum();
    System.out.println(sum);
} 
```
- why you can't reference the getSalary method on the EMployee class in this case, because objects coming out of map are of type IEmployee and that makes the streams api think that it needs to call a static method called getSalary because IEmployee doesnt match Employee. 
- calls the getSalary method individually on each of these obkects because they're instances, whereas if its thinking that hte getSalary method is a static method, it's not going to call the getSalary method on the individual instances, instead it's going to try to call a static getSalary methd, and there is no static getSalary method, and thus we get this error.
- Java and the Streams API are trying ot be super smart to figure out when we pass in this method references what should it actually do.
- complex ways in which Java can map data from one stage in pipeline to next

```java
 private static void sumSalaries(String peopleText) {
        int sum = peopleText
                .lines()
                .map(Employee::createEmployee)
                .mapToInt((IEmployee emp) -> {
                    System.out.println(emp);
                    return emp.getSalary();
                })
                .sum();
        System.out.println(sum);
    }
    
// Can be replaced with nice readable pipeline using Streams API
private static void sumSalaries(String peopleText) {
    int sum = peopleText
            .lines()
            .map(Employee::createEmployee)
            .mapToInt(StreamsStuff::showEmpAndGetSalary)
            .sum();
    System.out.println(sum);
}

private static int showEmpAndGetSalary(IEmployee emp) {
    System.out.println(emp);
    return emp.getSalary();
}

```
- In this case it is a static reference, because showEmp... is a static method and also Java is recogniseing that th einput is of type IEmployee so those data types are matching
- now covered most of the functionality of the revious implemetnation excpet tthe number formating but that is not related to streams API
---
## Sorting with Streams
- .sorted() - only works properly because Employee class is implementing Comparable

```java
// Sorted by Salary
private static void sumSalaries(String peopleText) {
    int sum = peopleText
            .lines()
            .map(Employee::createEmployee)
            .sorted((x,y) -> Integer.compare(x.getSalary(), y.getSalary()))
            .mapToInt(StreamsStuff::showEmpAndGetSalary)
            .sum();
    System.out.println(sum);
}
// IDE suggests converting to this...
private static void sumSalaries(String peopleText) {
    int sum = peopleText
            .lines()
            .map(Employee::createEmployee)
            .sorted(Comparator.comparingInt(IEmployee::getSalary))
            .mapToInt(StreamsStuff::showEmpAndGetSalary)
            .sum();
    System.out.println(sum);
}

```
- Comparator.comparing() - key reference, pass in a method reference to a method that returns a field that we want to sort by

```java
private static void sumSalaries(String peopleText) {
    int sum = peopleText
            .lines()
            .map(Employee::createEmployee)
            .map(e -> (Employee)e)// cast as Employee
            .sorted(comparing(Employee::getLastName) // (x,y) -> x.getLastName().compareTo(y.getLastName())
                    .thenComparing(Employee::getFirstName)
                    .thenComparingInt(Employee::getSalary)
                    .reversed())
            .mapToInt(StreamsStuff::showEmpAndGetSalary)
            .sum();
    System.out.println(sum);
}

    // sorted alphabetically and then by salary
//Flinstone, Fred: £2,803,000.00 - £3,083,300.00
//Flinstone, Fred: £5,603,000.00 - £6,163,300.00
//Flinstone, Fred: £7,003,000.00 - £7,703,300.00
//Flinstone, Fred: £8,403,000.00 - £9,243,300.00
//Flinstone, Fred: £9,803,000.00 - £10,783,300.00
//Flinstone, Fred: £11,203,000.00 - £12,323,300.00
//Flinstone, Wilma: £2,506.00 - £2,756.60
//Flinstone2, Fred2: £1,823,000.00 - £2,005,300.00
//Flinstone2, Wilma2: £2,508.00 - £2,758.80
//Flinstone3, Fred3: £1,935,000.00 - £2,128,500.00
//Flinstone3, Wilma3: £2,510.00 - £2,761.00
//Flinstone4, Fred4: £565,350.00 - £621,885.00
//Flinstone4, Wilma4: £2,512.00 - £2,763.20
//Flinstone5, Fred5: £8,000.00 - £8,800.00
//Flinstone5, Wilma5: £2,518.00 - £2,769.80
//N/A, N/A: £0.00 - £0.00
//Rubble, Barney: £6,500.00 - £7,150.00
//Rubble, Betty: £1,500,000.00 - £1,650,000.00
//Rubble2, Barney2: £3,900.00 - £4,290.00
//Rubble3, Barney3: £3,900.00 - £4,290.00
//Rubble4, Barney4: £7,500.00 - £8,250.00
//Rubble5, Barney5: £7,000.00 - £7,700.00
//        50690704


```
- by default the natural ordering that we get from computers is not actually what we humans would normally consider to be natural at all in the sense that the number 10 expressed as a string would actualyl become before the number 2, because the 1 in the 10 becomes before the 2, and so that ordering isnt natural.
- however if we use the numerical aware versions of the comparing methods then they will sort in what humans consdier to be a more natural orering:
- thenComparingInt() - 
- how is comparing method actually working? the sorted method takes an input simply the Comparator, so what this static comparing method does is it builds a comparing method for us,
- turns it intoa template, avoid boilerplate, allows us to think about just the bits we need to think about which is just the field, and then the comparing method spits back out an instance of a comparator which can be plugged intot he sorted methd.
- much more readable code - more beautiful when using the Streams API.
- not so foucssed on how to write that coed to extract these things and instead just focussed on what we want to do, more declarative - we're declaring what we want to do but not how to do it - and this is more typical of functional programming.

### Filtering with Streams
- the definition of a duplicate tends to be predicated on the way that the objects have been coded in terms of their equals and hashcode methods, especially getting rid of duplicates by storing objcets in a Set and effectively what ends up happing their is each obect thtat goes into the Set has a hashcode and the set has the ability to check that hashcode and filter out any objects whose hashcode matches with objects that are already in their, and then it also calls teh equals method too to really make sure.
- Similar things we can do here without even having to put aything into a Set necessarily. 
- .distinct()// Returns a stream consisting of the distinct elements (according to Object. equals(Object)) of this stream.
- duplicates are defined by however oru equals method is implemented 

```java
    private static void sumSalaries(String peopleText) {
    int sum = peopleText
            .lines()
            .map(Employee::createEmployee)
            .map(e -> (Employee)e) // cast as Employee
            .distinct()// Returns a stream consisting of the distinct elements (according to Object. equals(Object)) of this stream.
            .sorted(comparing(Employee::getLastName) // (x,y) -> x.getLastName().compareTo(y.getLastName())
                    .thenComparing(Employee::getFirstName)
                    .thenComparingInt(Employee::getSalary))
            .mapToInt(StreamsStuff::showEmpAndGetSalary)
            .sum();
    System.out.println(sum);
}
// duplicates are defined by however the equals method is implemented
// based on lname, fname, and dob, if those 3 fields are the same between any 2 or more objects then you've got duplicates
 @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(lastName, employee.lastName) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(dob, employee.dob);
    }
```
- Can also use a Set() so do the same thing. Collect them into a List, a Set
- .collect() is a terminal operation meaning it is usually used at the end
```java
private static void streamPractice(String peopleText) {
    int sum = peopleText
            .lines()
            .map(Employee::createEmployee)
            .map(e -> (Employee)e) // cast as Employee
            .collect(Collectors.toSet()) // Set<Employee>
            .stream()// have to convert back to Stream after toSet()
            // .distinct()// Returns a stream consisting of the distinct elements (according to Object. equals(Object)) of this stream.
            .sorted(comparing(Employee::getLastName) // (x,y) -> x.getLastName().compareTo(y.getLastName())
                    .thenComparing(Employee::getFirstName)
                    .thenComparingInt(Employee::getSalary))
            .mapToInt(StreamsStuff::showEmpAndGetSalary)
            .sum();
    System.out.println(sum);
}
// Would normally use collect at the end, but not wrong or right.
private static void setOfEmployees(String peopleText) {
    Set<Employee> empSet = peopleText
            .lines()
            .map(Employee::createEmployee)
            .map(e -> (Employee)e) // cast as Employee
            .collect(Collectors.toSet()); // Set<Employee>
    System.out.println(empSet);
}
```
- .filter() takes what iscalled a predicate - a predicate is another functional interface that has one abstract method defined on it adn that method returns a true of false.
- returns true or false because usually its implemented by a lambda expression in order to decide what object should or should not get filtered out of a stream.
```java
private static void streamPractice(String peopleText) {
    int sum = peopleText
            .lines()
            .map(Employee::createEmployee)
            .map(e -> (Employee)e) // cast as Employee
            .filter(not(e -> e.getLastName().equals("N/A"))) // filter out Programmerzzzzz
            .collect(Collectors.toSet()) // Set<Employee> - terminal operation
            .stream()// have to convert back to Stream after toSet()
            // .distinct()// Returns a stream consisting of the distinct elements (according to Object. equals(Object)) of this stream.
            .sorted(comparing(Employee::getLastName) // (x,y) -> x.getLastName().compareTo(y.getLastName())
                    .thenComparing(Employee::getFirstName)
                    .thenComparingInt(Employee::getSalary))
            .mapToInt(StreamsStuff::showEmpAndGetSalary)
            .sum();
    System.out.println(sum);
} 
```
#### Benefits of Streams API
- Streams API has a lot of power and benefits:
- Streams API is so easy
- Streams API is also highly optimized, faster than traditional way, Java gets a much better opportunity to optimize the code before it executes
- Much more readable, easier to maintain, actually runs faster
- also opens up our code for the ability to take full advtantage of multiple processing cores on the computer so that we can actually process data in parallel streams simultaneously, and we don't have to be experts in multi-processing and what it takes to do all that, Streams API makes it extremely easy for us to do this without much understanding.
- Streams API has a lot of power and benefits in a lot of cases, espeically cases where you have a lot of data that you need to sift through and relate in various ways, convert into some oher form, or filter out, or do a lot of comobinations of those sorts of things.

### Flattening Streams of Streams
- flatMap
- anytime you have a situation with the Streams API where you end up with a stream of streams that's probably not what you wanted in many cases and you want to flatten that structure into one stream, and that is what the flatMap does.
```java
private static void flattenStreamsOfStreams(String peopleText) {
    peopleText
            .lines()
            .map(Employee::createEmployee)
            .map(e -> (Employee) e)
            .map(Employee::getFirstName)
            .map(firstName -> firstName.split(""))
            .flatMap(Arrays::stream) // flattens redundant child streams into one super stream with whatever the original contents were emdedded
            .map(String::toLowerCase)
            .distinct() // get rid of duplicate letters
            .forEach(System.out::print);
}
    // fredn/a2345bywilmt

```
---
### Alternatives to Filter
- do all of the employees have a salary higher than? allMatch() returns a boolean checks every item that comes through the stream, after we have done any filtering
- do any of the employees have a salary higher than? 
- what's the advantage of these methods over a filter? 
  - a situation where you really just want to know yes or know, and don't want to count up all the objects that passed throguh all the filters
  - these methods provide short-circuiting. Drop-while, do-while, will iterate over the stream until a predicate is satisfied and then stop traveersing the stream at that point and either just return the rest of it or return everything up until that pointn, the point is they are able to short-circuit some part of the stream, and the benefit of that prevents iterating over 100000000s of records iflooking for first match
- .nonMatch() 
- .findFirst() returns an Optional - Optional allows us to avoid having null variables or values - if used properly can allow us to avoid null pointer exceptions
- can't call a method on an object that isn't there - that will result in a null pointer exception - basically an error, you're program will blow up
  - this is why the recommendation of taking the string constant and putting it at the front exists
  - i.e. preferred: Predicate<Employee> dummyEmployeeSelector = employee -> "N/A".equals(employee.getLastName());
  - rather than: Predicate<Employee> dummySelector = e -> e.getLastName().equals("N/A");
  - or more modern solution is to use an Optional which allows us to say it might or might not have a value, we'll find otu at runtime. Key thing is it will alwauys be Optional, Optioal is an actual object, fields that support optionals should always at least have the Optional object existing, Optional is like a box, a box capable of housing whatever the Optional's genereic type is, Optional<Employee>
```java
private static void alternativesToFilter(String peopleText) {
    Predicate<Employee> dummySelector = e -> e.getLastName().equals("N/A");
    Optional<Employee> optionalEmployee = peopleText
            .lines()
            .map(Employee::createEmployee)
            .map(e -> (Employee) e)
            .filter(dummySelector.negate())
            .findAny()
//                .findFirst();
    System.out.println(optionalEmployee // If there is an employee then get the first name out
            .map(Employee::getFirstName)
            .orElse("Nobody")); // otherwise return nobody
//                .noneMatch(e -> e.getSalary() < 0);
//                .anyMatch(e -> e.getSalary() > 10000000);
//                .allMatch(e -> e.getSalary() > 2500);

        /*
        if (employee != null) {
            System.out.println(employee.getFirstName());
        } else {
            System.out.println("Nobody");
        }
         */
}
```
- differnece between findAny and findFirst is to do with how the Streams API handles the ordering within and how it can optimize how to float through the streams depending on whether we care about the order or not
- More of an impact if you are using the streams API to do parallel processing where we allow multiple processors or cores within a computer to execute a pipeline concurrently or at the samet time. In that scenario, if we're using findAny it doesnt matter which one we find that satisfies the predicate even across multiple processors
---
### The MapReduce Pattern
- The Map Reduce pattern is a very commonly used pattern in functional programming to allow developers to take large amounts of data and sift through it and reduce it down to its essence or a summary.
- An example: we wrote a pipeline to process our employees and total up their salaries. The sum function was the reduce function. 
- Many other things we can do with the map reduce pattern besides summing up grand total of objects, we can count the num of objects, total, maxValue, minValue, and also doesnt only apply to numerical summaries - can use other types of data as long as the end goal is to summarise or condense the data down.
- a number of additional methods besides jsut the sum for reducing.

```java
    private static void mapReducePattern(String peopleText) {
        Predicate<Employee> dummyEmployeeSelector = employee -> "N/A".equals(employee.getLastName());
        Predicate<Employee> overFiveKSelector = e -> e.getSalary() > 5000;
        Predicate<Employee> noDummiesAndOverFiveK = dummyEmployeeSelector.negate().and(overFiveKSelector);
        OptionalDouble result = peopleText
                .lines()
                .map(Employee::createEmployee)
                .map(e -> (Employee)e)
                .filter(noDummiesAndOverFiveK)
                .collect(Collectors.toSet())
                .stream()
                .sorted(comparing(Employee::getLastName)
                        .thenComparing(Employee::getFirstName)
                        .thenComparingInt(Employee::getSalary))
                .skip(5)
                .mapToInt(StreamsStuff::showEmpAndGetSalary)
                // Reduce 
                .average(); // determines the number of items (in this case integers) that are coming out of mapToInt stream and also determining the sum and then dividing those to get the average
        
        System.out.println(result.orElse(0));
    }

```
- .reduce() function is a lower level function
  - takes an int binary
- .reduce(0, (a, b) -> a + b) - 0 = identity - plugs it in to a, then takes first number and plugs that into b, then takes two values 0 + 10, then takes that result = 10, and calls the lambda expression again, but this time calls int her esult of the previous iteration which is 10 and plugs that in to a, then takes next value in stream which is 9, and then executes the lamdba which is to add thse whcih is 19,
- the operation we're doing is pluggable - what operation are we providing between a and b, all the rest is boilerplate, doing something with those 2 values and doing somehting with those values and the result of that is going to be plugged back in as one of the 2 values for the next cycle.
- the identity is a starting value, for a given operation an identity will give you whatever value you are plugging in for that very first iteration
- because we're passing in the identity it should not be possbile to return null 
- there is an overloaded version of reduce which doesn't require an identity - this simply takes the first value in the list and then each subsequent iteration takes in where the last iteration took off
- has a side effect - 
- pretty much any method that takes a lambda will also take a method reference
- reduce functions used for more than just numbers
- as long as we're taking the list of things and spitting out one combined or summarised output

```java
private static void reduceNonNumericValues() {
  Optional<String> output = Stream.of("tom", "jerry", "mary", "sam")
          // reduce down to one concatenated string
          .reduce((a, b) -> a.concat("_").concat(b));
  System.out.println(output.orElse(""));
}
```
- what happens if only one value?
- proves that when we don't supply an identity, that very first iterationt hat the reduce fucntion is going to do simply takes that first value in.
- Map Reduce pattern can be highly effective when needing to process large amounts of data. 
- Popular because can easily be parallelized, can process lots of data across multiple processors.
- a large list of 1 billion items can be segmented into groupings depending on how many processors available and each processor has its own groupings and sum up whatever property and reduce down to one value on that processor and other processors do the same and then all of their values get summed up togehter as well and then you've got the grand total value - presumambly in 1/10 of the time if 10 processors.

### Intro to More Advanced Streams
- CSV - Comma Separated Values - very commonly used in business especially among programmers but lvoe to use excel to programme data, but usally they need to give that data over to developers to do more meanningful programming takss around it, and so typically they export that data in the CSV format
- simple text file that is comma separated. 
- different methods are capable of generating different kinds of exceptions when things dontwork out correctly
- we can limit the number of streams that we process with .limit()
- one of the great things abouts the Files.lines() capabality is that it is highly efficient in that certain types of file input/output programming one common pattern that wouldn't work very effectively herewould be to open up the file, read the entire contents of the file into a variable or buffer or array and then close the file.
- The problem with that approach here is that because this file has 5 million records that would mean that we would have to load up all 5million of those records into the memory of the computer while the programming is running, and that would be very wasteful of memory, if we don't absolutely need all that data in memory all at once.
- Fies.lines() capbility to retrn back a string is very streamlined in that it doesnt need to load the entire file into memory, instead it literally reads one line at a time and streaming those lines through for us to process and so they come in get proecssed and go back out of the memory again, and that alows us to very efficiently work with incredibly large files if we need to without impacting the memory of the computer signinfanctly at all.
- So whether we were loading 5 or 5 million lines it wouldnt effect the memory much however it would effect the time, as we dont want to sit there and wait for it to happen.

```java
public class BigData {
  public static void main(String[] args) throws IOException {
    try {
      Long result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
              .limit(2) // limit processing of lines to 2
              .skip(1) // skip header row
              .collect(Collectors.counting());
//                    .count()
//                    .collect(Collectors.toList());
      System.out.println(result);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
```
- difference between .count() and .collect() - .collect is an entire framework to enable us to do powerful query type things with the streams api. all kinds of capabilities that have been implemented ina fairly generic type of way in an interface whereas the .count method and many of the other temrinal methods in the streams API are more purpose built, so in some cases they might actually be more effciient.
- some things that you can only do with the .collect() method that you can;t do in any other way.

---
### Big Data Summing
#### Process of real world programming:
- In programming think of the end goal first and then work back from there. 
- So let's say the end result should be a really big number, so how do I get that from whatever the starting thing is?
- we want to be able to isolate out these values programmatically
- the nature of these lines is that they are text not numbers, we can't do math on strings
- so we need to seperate out the salary
- we know that the salary will be text so we need to convert some text to numbers
- if you can't eyeball the data can be v helpful to paste it into a spreadsheet and seperate it out 
- we know the .lines() method returns a stream of string so we know fundamentally what we are dealing with is strings.
- So the first step we need to take each of these lines of text and break them apart into fields according to the commas
- Here's another aspect of being a real-world programmer: you're gojng to have to remember not the details of how to do each and eveyr thing, but you will have to be able to remember genreally peaking what can be done, what kinds of operations are possible.
- Dont have to remember waht that method is called but that there is some kind of method available - and then google it
- You could think of each row as 1 array, and each element in the array is each cell, and that allows us to seperate out the individual parts and access the elemenets of the arrayvery easily
- to convert one data type to another data type we use the .map() method, that's what it does.
- a map function is simple an interface that defines one method on it and method takes one input and returns one output

```java
// return type of .collect() method is highly dynamic, it's based on the return type of the internal Collector
public abstract <R, A> R collect(     java. util. stream. Collector<? super T, A, R> collector )
```

```java
// WARNING: This file has 5 million records.
public class BigData {
  public static void main(String[] args) throws IOException {
    try {
      long startTime = System.currentTimeMillis();
      Long result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
              .skip(1) // skip header row
//                            .limit(10) // limit lines because file has 5 million records
              .map(s -> s.split(","))
              .map(arr -> arr[25]) // index 25 = salary field
              .collect(Collectors.summingLong(Long::parseLong));
      long endTime = System.currentTimeMillis();
      System.out.printf("$%,d.00%n", result);
      System.out.println(endTime - startTime);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

// $599,962,473,668.00
// 18182
// VS:

public class BigData {
  public static void main(String[] args) throws IOException {
    try {
      long startTime = System.currentTimeMillis();
      Long result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
              .skip(1) // skip header row
//                            .limit(10) // limit lines because file has 5 million records
              .map(s -> s.split(","))
              .map(arr -> arr[25]) // index 25 = salary field
              .mapToLong(Long::parseLong)
              .sum();
//                    .collect(Collectors.summingLong(Long::parseLong));
      long endTime = System.currentTimeMillis();
      System.out.printf("$%,d.00%n", result);
      System.out.println(endTime - startTime);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
//$599,962,473,668.00
//18180
```
- barely any difference in speed between two implementations.
- Java manages parallel processing for us magically 
- by just using on littl ekeyword here we are able to cut the processing time by more than half (doesn't work on my computer! - twice as long!!)

```java
public class BigData {
    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
            Long result = Files.lines(Path.of("E:\\Java\\Hr5m.csv")).parallel()
                    .skip(1) // skip header row
//                            .limit(10) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(arr -> arr[25]) // index 25 = salary field
                    .mapToLong(Long::parseLong)
                    .sum();
//                    .collect(Collectors.summingLong(Long::parseLong));
            long endTime = System.currentTimeMillis();
            System.out.printf("$%,d.00%n", result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// Took almost twice as long with parallel processing!!! 
// 38667


```
### Domain Models with Streams API
- Streams API lends itself very well towards the data that we are processing being stored in actual objects of a domain model.
- Difference between using a domain model to process 5million records vs using an array and other techniques.
- be aware that essentially we've got a pipeline here and you can imagine that ther eis a for loop happenng 5 million times. 
- when we call split an instance of an array is being created and that means 5million arrays are being created during the lifetime of this programme, and with the addition of the Person class, 5 million instance of the Person class will also be created, and if were not careful udner ertain cricumstances that could be quite eassteful of the moemory of the computer.
- however these arrays and instances won't actualyl live very long, because in Java whenever an object i created the JVM is constnatly montoring and tracking the creation of objcets, and when it realised that an objcet is no longer being referenced, i.e. used, and doesn't have any possiblity of being used again, then the JVM will falg that object for garbage collection, nd sot here is this garbage collection thread that periodoiically comes looking around for objects that have been flagged for garbgae ollection and it will colect them and clear them out of system meory
- main point is that in this programme we are not likely to ever see 5miilion arrays and 5million People intances all sititng in memory at the same time. Maybe 100-1000 at one time.

### Grouping Records
- Advanced techniques on .collect() methods, one of which has the ability not only to sum up data from a column or a field but to actually be able to group data by a field, or in other words to categorize data.
- group 2-dimensional table of records by some category or field
- .collect() method is gateway into much more advanced capabilities like grouping data
```java
public class BigData {
  // record generates getters, setters, etc automatically
  record Person(String firstName, String lastName, long salary, String state) {}

  public static void main(String[] args) throws IOException {
    try {
      long startTime = System.currentTimeMillis();
      Map<String, List<Person>> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
              .skip(1) // skip header row
              .limit(10) // limit lines because file has 5 million records
              .map(s -> s.split(","))
              .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
              .collect(Collectors.groupingBy(Person::state));
      long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
      System.out.println(result);
      System.out.println(endTime - startTime);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
// {TX=[Person[firstName=Imogene, lastName=Hagopian, salary=55761, state=TX]], LA=[Person[firstName=Walker, lastName=Wallach, salary=197519, state=LA]], OH=[Person[firstName=Lizeth, lastName=Mccoll, salary=147446, state=OH], Person[firstName=Fausto, lastName=Esqueda, salary=60101, state=OH], Person[firstName=Vanda, lastName=Komar, salary=115639, state=OH]], NJ=[Person[firstName=Destiny, lastName=Nicholson, salary=126048, state=NJ], Person[firstName=Evie, lastName=Hamby, salary=193757, state=NJ]], CA=[Person[firstName=Damian, lastName=Patillo, salary=158746, state=CA], Person[firstName=Jesusita, lastName=Hollie, salary=103839, state=CA]], DC=[Person[firstName=Argentina, lastName=Hern, salary=129174, state=DC]]}
// 106


```
- {TX=[Person[firstName=Imogene = Imogene from Texas,
- .groupingBy() - 3 overloaded versions - a mapFactory() just another lambda or method reference that allows us to return a specific instance of a map. 
- when we're using the version of groupingBy that only takes 1 input which is basically the field that we want to group by, by default it will take all of the records that got grouped and it will throw them into a list and return them.

```java
public class BigData {
    // record generates getters, setters, etc automatically
    record Person(String firstName, String lastName, long salary, String state) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
            Map<String, List<Person>> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .skip(1) // skip header row
                    .limit(10) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
                    .collect(Collectors.groupingBy(Person::state, TreeMap::new, toList())); // convert default HashMap to TreeMap for alphabetized ordering
//                    .collect(Collectors.groupingBy(Person::state));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
            System.out.println(result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//{CA=[Person[firstName=Damian, lastName=Patillo, salary=158746, state=CA], Person[firstName=Jesusita, lastName=Hollie, salary=103839, state=CA]], DC=[Person[firstName=Argentina, lastName=Hern, salary=129174, state=DC]], LA=[Person[firstName=Walker, lastName=Wallach, salary=197519, state=LA]], NJ=[Person[firstName=Destiny, lastName=Nicholson, salary=126048, state=NJ], Person[firstName=Evie, lastName=Hamby, salary=193757, state=NJ]], OH=[Person[firstName=Lizeth, lastName=Mccoll, salary=147446, state=OH], Person[firstName=Fausto, lastName=Esqueda, salary=60101, state=OH], Person[firstName=Vanda, lastName=Komar, salary=115639, state=OH]], TX=[Person[firstName=Imogene, lastName=Hagopian, salary=55761, state=TX]]}
//3058
```
---
### Summing by Groups
- sum total by state - which state makes the most money?
- in Excel every one of the sum numeric functions exists on the Collectors class
- when using the 3 parameter version of groupingBy() the first parameter is asking what field do you want to group by? the 2nd parameter in this case lets us specify what map do we want to use, we supply the map that we want to make use of. 3rd column is what do you want me to do with all of the resulting records for each categorization?


```java
public class BigData {
    // record generates getters, setters, etc automatically
    record Person(String firstName, String lastName, long salary, String state) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
            Map<String, String> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .skip(1) // skip header row
//                    .limit(10) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
                    .collect(groupingBy(Person::state, TreeMap::new,
                            collectingAndThen(summingLong(Person::salary), s -> String.format("$%,d.00%n", s))));
//                    .collect(Collectors.groupingBy(Person::state, TreeMap::new, toList())); // convert default HashMap to TreeMap for alphabetized ordering
//                    .collect(Collectors.groupingBy(Person::state));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
            System.out.println(result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//{AK=$3,858,792,792.00
//, AL=$12,021,106,044.00
//, AR=$10,167,623,203.00
//, AZ=$7,520,643,811.00
//, CA=$38,526,260,270.00
//, CO=$9,378,153,793.00
//, CT=$6,205,579,091.00

```
.collect().forEach take BiConsumer - two inputs = key and value - each key value pair the foreach method will call your function passing in these two values and do whatever you want

```java
public class BigData {
    // record generates getters, setters, etc automatically
    record Person(String firstName, String lastName, long salary, String state) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
//            Map<String, String> result =
                    Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .skip(1) // skip header row
//                    .limit(10) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32]))
                    .collect(groupingBy(Person::state, TreeMap::new,
                            collectingAndThen(summingLong(Person::salary), NumberFormat.getCurrencyInstance()::format)))
                    .forEach((state, salary) -> System.out.printf("%s -> %s%n", state, salary));
//                          collectingAndThen(summingLong(Person::salary), s -> NumberFormat.getCurrencyInstance().format(s))));
//                          collectingAndThen(summingLong(Person::salary), s -> String.format("$%,d.00%n", s))));
//                    .collect(Collectors.groupingBy(Person::state, TreeMap::new, toList())); // convert default HashMap to TreeMap for alphabetized ordering
//                    .collect(Collectors.groupingBy(Person::state));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
//            System.out.println(result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//VA -> £18,689,390,844.00
//VT -> £4,250,722,586.00
//WA -> £10,323,935,354.00
//WI -> £12,811,058,437.00
//WV -> £13,773,233,536.00
//WY -> £2,790,845,726.00
```
- the most popular use for java programmes is to transfer data back and forth between various data systems, between members in an organisation or between organisations
- or to convey data, to process data to be interacted with in the web browser in the form of a web application

---
### Nested Groupings
- Problem: how much money in total do women in each state make vs men in each state?
- 2nd level of categorization or grouping
```java
 public class BigData {
  // record generates getters, setters, etc automatically
  record Person(String firstName, String lastName, long salary, String state, char gender) {}

  public static void main(String[] args) throws IOException {
    try {
      long startTime = System.currentTimeMillis();
//            Map<String, String> result =
      // Map<String(state), Map<chat(gender), String(formatted-salary)>>
      // might be preferable to use more generic Map type rather than TreeMap
      TreeMap<String, Map<Character, String>> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
              .skip(1) // skip header row
              .limit(100) // limit lines because file has 5 million records
              .map(s -> s.split(","))
              .map(a -> new Person(a[2], a[4], Long.parseLong(a[25]), a[32], a[5].strip().charAt(0)))
              .collect(
                      groupingBy(Person::state, TreeMap::new,
                              groupingBy(Person::gender,
                                      collectingAndThen(
                                              summingLong(Person::salary),
                                              NumberFormat.getCurrencyInstance()::format))
                      )
              );
//                            .entrySet().stream()
//                            .forEach();
//                    .forEach((state, salary) -> System.out.printf("%s -> %s%n", state, salary));
//                          collectingAndThen(summingLong(Person::salary), s -> NumberFormat.getCurrencyInstance().format(s))));
//                          collectingAndThen(summingLong(Person::salary), s -> String.format("$%,d.00%n", s))));
//                    .collect(Collectors.groupingBy(Person::state, TreeMap::new, toList())); // convert default HashMap to TreeMap for alphabetized ordering
//                    .collect(Collectors.groupingBy(Person::state));
      long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
      System.out.println(result);
      System.out.println(endTime - startTime);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

// {AL={F=£258,618.00, M=£395,247.00}, AR={M=£58,910.00}, CA={F=£467,901.00, M=£510,934.00}, CO={F=£171,496.00, M=£90,660.00}, DC={F=£129,174.00}, GA={F=£214,796.00, M=£163,300.00}, IL={M=£454,970.00}, KS={F=£325,792.00, M=£119,606.00}, KY={F=£340,971.00}, LA={M=£612,031.00}, MA={M=£74,095.00}, MD={F=£144,914.00, M=£95,566.00}, ME={F=£96,736.00}, MI={F=£178,512.00, M=£358,338.00}, MN={F=£326,712.00}, NC={F=£236,730.00, M=£107,120.00}, NJ={F=£319,805.00}, NM={F=£274,535.00, M=£62,402.00}, NV={F=£150,529.00}, NY={F=£205,192.00, M=£326,947.00}, OH={F=£263,085.00, M=£290,046.00}, OK={F=£175,660.00}, PA={F=£248,366.00, M=£919,688.00}, RI={F=£149,128.00}, SC={F=£144,167.00}, SD={M=£186,834.00}, TN={M=£134,139.00}, TX={F=£560,909.00, M=£370,453.00}, VA={F=£92,436.00, M=£310,115.00}, WA={F=£178,965.00}, WI={F=£263,771.00, M=£41,892.00}, WV={F=£62,618.00, M=£87,190.00}, WY={F=£103,833.00}}
// 74

```
- first group by state, then do a secondary level of grouping by gender, then take all the records for that and do two things, sum them up and then format them as currency.
- you can keep nesting groups into further levels.
- generics cannot actually hve generic data types so Java will automatically map those primitive data types to class wrap types.
```java
public class BigData {
  // record generates getters, setters, etc automatically
  record Person(String firstName, String lastName, BigDecimal salary, String state, char gender) {}

  public static void main(String[] args) throws IOException {
    try {
      long startTime = System.currentTimeMillis();
//            Map<String, String> result =
      // Map<String(state), Map<chat(gender), String(formatted-salary)>>
      // might be preferable to use more generic Map type rather than TreeMap
      TreeMap<String, Map<Character, String>> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
              .skip(1) // skip header row
              .limit(100) // limit lines because file has 5 million records
              .map(s -> s.split(","))
              .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)))
              .collect(
                      groupingBy(Person::state, TreeMap::new,
                              groupingBy(Person::gender,
                                      collectingAndThen(
                                              reducing(BigDecimal.ZERO, Person::salary, BigDecimal::add),
//                                                    reducing(BigDecimal.ZERO, Person::salary, (a, b) -> a.add(b)),
//                                                    summingLong(Person::salary),
                                              NumberFormat.getCurrencyInstance()::format))
                      )
              );
//                            .entrySet().stream()
//                            .forEach();
//                    .forEach((state, salary) -> System.out.printf("%s -> %s%n", state, salary));
//                          collectingAndThen(summingLong(Person::salary), s -> NumberFormat.getCurrencyInstance().format(s))));
//                          collectingAndThen(summingLong(Person::salary), s -> String.format("$%,d.00%n", s))));
//                    .collect(Collectors.groupingBy(Person::state, TreeMap::new, toList())); // convert default HashMap to TreeMap for alphabetized ordering
//                    .collect(Collectors.groupingBy(Person::state));
      long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
      System.out.println(result);
      System.out.println(endTime - startTime);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
} 
```

---
### Reducing with Collect
- Scenario:
- what if you wanted to do some other kind of operation on your data, like the salary field, an operation that doesn't come pre-packaged with the Collectors out of the box.

---
### Partitioning vs Grouping
- partitionBy - a hybrid of the filter function of the streams api and the groupingBy
- partitionBy takes a predicate (predicate an interface that takes an input and returns a boolean)
- One of the difference between filter and partitionBy is that filter simpyl returns a modified stream that does not contain whatever items did not pas the filter, but with the partitionBy just like a groupingBy actually returns a map, and that map will alwyas have no more than two groupings - the items that did match the predicate and then the items that didn't match the predicate.
- 

```java
public class BigData {
    // record generates getters, setters, etc automatically
    record Person(String firstName, String lastName, BigDecimal salary, String state, char gender) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
//            Map<String, String> result =
            // Map<String(state), Map<chat(gender), String(formatted-salary)>>
            // might be preferable to use more generic Map type rather than TreeMap
            Map<Boolean, List<Person>> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .skip(1) // skip header row
                    .limit(100) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)))
                    .collect(partitioningBy(p -> p.gender()== 'F'));
            long endTime = System.currentTimeMillis();
            System.out.println(result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// {false=[Person[firstName=Damian, lastName=Patillo, salary=158746, state=CA, gender=M], Person[firstName=Walker, lastName=Wallach, salary=197519, state=LA, gender=M], Person[firstName=Fausto, lastName=Esqueda, salary=60101, state=OH, gender=M], Person[firstName=Carlton, lastName=Friedrich, salary=43826, state=OH, gender=M], Person[firstName=Carmelo, lastName=Haynie, salary=84036, state=MI, gender=M], Person[firstName=Burton, lastName=Lowry, salary=181592, state=PA, gender=M], Person[firstName=Mickey, lastName=Bassham, salary=131922, state=MI, gender=M], Person[firstName=Ruben, lastName=Pastor, salary=41892, state=WI, gender=M], Person[firstName=Donnell, lastName=Wolff, salary=190587, state=AL, gender=M], Person[firstName=Guadalupe, lastName=Ketron, salary=122513, state=PA, gender=M], Person[firstName=Trenton, lastName=Roessler, salary=80435, state=MI, gender=M], Person[firstName=Fernando, lastName=Merced, salary=95566, state=MD, gender=M], Person[firstName=Jordan, lastName=Raine, salary=199796, state=NY, gender=M], Person[firstName=Phillip, lastName=Bostic, salary=90660, state=CO, gender=M], Person[firstName=Nestor, lastName=Gledhill, salary=52538, state=NC, gender=M], Person[firstName=Edgar, lastName=Phares, salary=125113, state=PA, gender=M], Person[firstName=Valentine, lastName=Fuentez, salary=159076, state=TX, gender=M], Person[firstName=Wesley, lastName=Downes, salary=52780, state=TN, gender=M], Person[firstName=Isidro, lastName=Estepp, salary=61945, state=MI, gender=M], Person[firstName=Chuck, lastName=Mcafee, salary=108741, state=TX, gender=M], Person[firstName=Jacques, lastName=Chick, salary=190846, state=PA, gender=M], Person[firstName=Florencio, lastName=Varnell, salary=67060, state=LA, gender=M], Person[firstName=Delmer, lastName=Tillman, salary=100827, state=IL, gender=M], Person[firstName=Carrol, lastName=Tinker, salary=122292, state=PA, gender=M], Person[firstName=Shayne, lastName=Shaffer, salary=119606, state=KS, gender=M], Person[firstName=Don, lastName=Thorne, salary=92536, state=OH, gender=M], Person[firstName=Thaddeus, lastName=Noggle, salary=163300, state=GA, gender=M], Person[firstName=Houston, lastName=Langston, salary=103402, state=CA, gender=M], Person[firstName=Lucius, lastName=Dengler, salary=158901, state=IL, gender=M], Person[firstName=Gustavo, lastName=Yedinak, salary=129474, state=AL, gender=M], Person[firstName=Rashad, lastName=Tipton, salary=195242, state=IL, gender=M], Person[firstName=Milford, lastName=Stegman, salary=58910, state=AR, gender=M], Person[firstName=Louis, lastName=Ison, salary=150160, state=LA, gender=M], Person[firstName=Wilber, lastName=Rodrigez, salary=185031, state=VA, gender=M], Person[firstName=Nick, lastName=Rosado, salary=127151, state=NY, gender=M], Person[firstName=Rich, lastName=Mei, salary=80739, state=CA, gender=M], Person[firstName=Joan, lastName=Mcauley, salary=197292, state=LA, gender=M], Person[firstName=Trenton, lastName=Lilly, salary=54582, state=NC, gender=M], Person[firstName=Jon, lastName=Mullinax, salary=125084, state=VA, gender=M], Person[firstName=Russell, lastName=Rawls, salary=93583, state=OH, gender=M], Person[firstName=Marion, lastName=Fermin, salary=73543, state=SD, gender=M], Person[firstName=Luigi, lastName=Corbett, salary=75186, state=AL, gender=M], Person[firstName=Woodrow, lastName=Tellis, salary=74095, state=MA, gender=M], Person[firstName=Scottie, lastName=Netto, salary=81359, state=TN, gender=M], Person[firstName=Austin, lastName=Vandeventer, salary=168047, state=CA, gender=M], Person[firstName=Rueben, lastName=Curren, salary=62402, state=NM, gender=M], Person[firstName=Trevor, lastName=Stidham, salary=102636, state=TX, gender=M], Person[firstName=Cedric, lastName=Cunningham, salary=113291, state=SD, gender=M], Person[firstName=Wyatt, lastName=Stacey, salary=177332, state=PA, gender=M], Person[firstName=Roberto, lastName=Klapper, salary=87190, state=WV, gender=M]], 
// true=[Person[firstName=Lizeth, lastName=Mccoll, salary=147446, state=OH, gender=F], Person[firstName=Argentina, lastName=Hern, salary=129174, state=DC, gender=F], Person[firstName=Imogene, lastName=Hagopian, salary=55761, state=TX, gender=F], Person[firstName=Jesusita, lastName=Hollie, salary=103839, state=CA, gender=F], Person[firstName=Vanda, lastName=Komar, salary=115639, state=OH, gender=F], Person[firstName=Destiny, lastName=Nicholson, salary=126048, state=NJ, gender=F], Person[firstName=Evie, lastName=Hamby, salary=193757, state=NJ, gender=F], Person[firstName=Bari, lastName=Troche, salary=182148, state=NM, gender=F], Person[firstName=Ying, lastName=Cerrone, salary=184946, state=MN, gender=F], Person[firstName=Bette, lastName=Copas, salary=103833, state=WY, gender=F], Person[firstName=Tien, lastName=Harkey, salary=103292, state=CA, gender=F], Person[firstName=Loma, lastName=Chesney, salary=66545, state=AL, gender=F], Person[firstName=Evelyn, lastName=Mclellan, salary=150529, state=NV, gender=F], Person[firstName=Meghann, lastName=Burtch, salary=53485, state=KS, gender=F], Person[firstName=Vivienne, lastName=Weikel, salary=178679, state=PA, gender=F], Person[firstName=Keely, lastName=Engleman, salary=142778, state=KY, gender=F], Person[firstName=Elida, lastName=Popa, salary=76017, state=OK, gender=F], Person[firstName=Muriel, lastName=Kasper, salary=69687, state=PA, gender=F], Person[firstName=Miriam, lastName=Rolfe, salary=178512, state=MI, gender=F], Person[firstName=Livia, lastName=Barefield, salary=178965, state=WA, gender=F], Person[firstName=Renea, lastName=Talavera, salary=144167, state=SC, gender=F], Person[firstName=Shelley, lastName=Matthes, salary=62618, state=WV, gender=F], Person[firstName=Suzy, lastName=Burge, salary=92436, state=VA, gender=F], Person[firstName=Valerie, lastName=Burge, salary=161154, state=WI, gender=F], Person[firstName=Fae, lastName=Jeppesen, salary=149128, state=RI, gender=F], Person[firstName=Octavia, lastName=Poitra, salary=123209, state=NC, gender=F], Person[firstName=Nelia, lastName=Saul, salary=199394, state=KS, gender=F], Person[firstName=Na, lastName=Minnick, salary=125558, state=TX, gender=F], Person[firstName=Breann, lastName=Alonso, salary=72913, state=KS, gender=F], Person[firstName=Jimmie, lastName=Pillar, salary=44786, state=AL, gender=F], Person[firstName=Shandi, lastName=Perrigo, salary=92387, state=NM, gender=F], Person[firstName=Theresia, lastName=Guerrette, salary=156772, state=NY, gender=F], Person[firstName=Claire, lastName=Deaton, salary=140064, state=GA, gender=F], Person[firstName=Ina, lastName=Becerra, salary=93382, state=TX, gender=F], Person[firstName=Ula, lastName=Quintero, salary=99643, state=OK, gender=F], Person[firstName=Joellen, lastName=Briceno, salary=171496, state=CO, gender=F], Person[firstName=Breana, lastName=Loughran, salary=102617, state=WI, gender=F], Person[firstName=Thomasine, lastName=Bode, salary=102809, state=CA, gender=F], Person[firstName=Doria, lastName=Bone, salary=141766, state=MN, gender=F], Person[firstName=Elenor, lastName=Mcarthur, salary=107412, state=KY, gender=F], Person[firstName=Kiersten, lastName=Losey, salary=147287, state=AL, gender=F], Person[firstName=Annika, lastName=Coolidge, salary=199546, state=TX, gender=F], Person[firstName=Sueann, lastName=Rawley, salary=86662, state=TX, gender=F], Person[firstName=Ilana, lastName=Hodgdon, salary=144914, state=MD, gender=F], Person[firstName=Tamra, lastName=Pauls, salary=48420, state=NY, gender=F], Person[firstName=Marylynn, lastName=Terrell, salary=96736, state=ME, gender=F], Person[firstName=Jaimie, lastName=Flack, salary=157961, state=CA, gender=F], Person[firstName=Mariella, lastName=Baade, salary=74732, state=GA, gender=F], Person[firstName=Tawanna, lastName=Milo, salary=90781, state=KY, gender=F], Person[firstName=Janyce, lastName=Babich, salary=113521, state=NC, gender=F]]}
// 2940
```
- if we don't want the list of people but instead we want to reduce them down to different values like count.

```java
public class BigData {
    // record generates getters, setters, etc automatically
    record Person(String firstName, String lastName, BigDecimal salary, String state, char gender) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
            Map<Boolean, Long> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .skip(1) // skip header row
                    .limit(100) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)))
                    .collect(partitioningBy(p -> p.gender()== 'F', counting())); // very simple to count

            long endTime = System.currentTimeMillis();
            System.out.println(result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// {false=50, true=50}
```
- we can use partitioning and grouping together, any collector function can be utilized in this 2nd spot.
- can nest layers of grouping and can do the same with partitioning too
- scenario: groupingBy People, by state and then by gender

```java
public class BigData {
    // record generates getters, setters, etc automatically
    record Person(String firstName, String lastName, BigDecimal salary, String state, char gender) {}

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
//            Map<String, String> result =
            // Map<String(state), Map<chat(gender), String(formatted-salary)>>
            // might be preferable to use more generic Map type rather than TreeMap
            Map<Boolean, Map<String, Long>> result = Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
                    .skip(1) // skip header row
                    .limit(100) // limit lines because file has 5 million records
                    .map(s -> s.split(","))
                    .map(a -> new Person(a[2], a[4], new BigDecimal(a[25]), a[32], a[5].strip().charAt(0)))
                    .collect(partitioningBy(p -> p.gender()== 'F',
                            groupingBy(Person::state, counting())));
            long endTime = System.currentTimeMillis();
//            System.out.printf("$%,d.00%n", result);
            System.out.println(result);
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// {false={IL=3, TX=3, KS=1, VA=2, NY=2, AL=3, CO=1, SD=2, AR=1, PA=6, WI=1, MA=1, NC=2, LA=4, MD=1, OH=4, GA=1, TN=2, MI=4, CA=4, WV=1, NM=1}, true={TX=5, NV=1, WA=1, NY=2, SC=1, WI=2, MD=1, ME=1, OH=2, GA=2, MI=1, OK=2, CA=4, WV=1, MN=2, WY=1, KS=3, VA=1, AL=3, CO=1, KY=3, PA=2, NC=2, RI=1, NJ=2, NM=2, DC=1}}

```
### Functional Interfaces
- In a lot of the streams api methods they take inputs that are referring to various interfaces like Predicates and Consumers. 
- a function in general is an interface for a method that takes an input and returns an output, usually and typically an input of one type and an output of potentially a different type though could be the same data type as well
- Suppliers: a function that doesn't take any input but returns an output, it's often used as something like a generator, giving you an opportunity to supply a fresh something to use, in this instance it gives the opportunity to suplly our own instance of a Map, a map factory
- BiConsumer: consumers are functions that take input but don't return anything, and the word Bi here means 2, so this returns 2 inputs and doesn't return anything.
- BiFunction: takes 2 inputs and return 1 output, inputs can be of 2 different types, and the output can be of a 3rd type, though they can all be the same
- BinaryOperator: very similar to a function, still takes an input and returns an output of the same type as the operands
- BiPredicate: we use a predicate for filtering for example. Predicates are functions that take an input and return a boolean, so they are used in filters for example to determine whether the items should be allowed to keep going through the stream for example
- Consumer: takes 1 input and returns nothing - forEach() method where we are usually taking input from items in the stream and calling sout which doesn't return anything, just prints to the screen, so that would be a consumer.
- DoubleBinaryOperator: takes 2 inputs of type double and then presumably it will return a double
- DoubleConsumer: somethign that returns nothing but takes 1 double
- DoubleFunction: a function that takes something of type double as an input and returns something of another type though it could be a double.
- DoublePredicate: input of a double and then returns true of false
- DoubleSupplier: takes no input but returns a double

---
### Functional Methods of Collections
- Looking at methods on the Collections classes or itnerfaces that pertain to doing streams and lambda expressions.
- result.forEach()
- result.replaceAll() - takes a BiFunction - takes 2 
- the replaceAll method takes as an input the BiFunction, BiFunction is an interface, so when we supply a lambda expression or a method reference what's really happening is that Java is going to take our lambda and plug it into an object that it's going to create for us and that objcet will look like whatever the functional interface is that is being expected, so for replaceAll it is going ot create an object that looks like a biFunction object and then it's going to take our lamdba expresion and plug that in as a method of that BiFunction object, and then in order to actuall call our supplied lambda expression it will be mapped to the .apply() method, and genreally speaking all of these functional interfaces have an apply method, so that's why we see here function.apply(), which we can take to mean call the lambda expression or method reference that we're supplying.
- replaceAll() - 

```java
result.replaceAll((k,v) -> k.startsWith("Z") ? v * 2 : v);

@Contract(mutates = "this")
public void replaceAll(     java. util. function. BiFunction<? super K, ? super V, ? extends V> function )
  
for (Map. Entry<K, V> entry : map. entrySet())     entry. setValue(function. apply(entry. getKey(), entry. getValue()))
```
- .compute() - Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping). For example, to either create or append a String msg to a value mapping:
- .compute() mehtod can be used for more than just tallying up values and summing up, you can use it maybe if the values we are tracking were words we might want to append something to the words

```java
public class StateCounter {
  public static void main(String[] args) throws IOException {
    Map<String, Long> stateCounts = new HashMap<>();
    Files.lines(Path.of("E:\\Java\\Hr5m.csv"))
            .skip(1)
            .limit(100)
            .map(l -> l.split(","))
            // equivalent
            .forEach(a -> stateCounts.compute(a[32], (k, v) -> v == null ? 1L : v + 1));
    System.out.println(stateCounts);

    // classic scenario for using compute method:
    Long curPopulation = stateCounts.get("CA");
    if (curPopulation == null) {
      stateCounts.put("CA", 1L);
    } else {
      stateCounts.put("CA", ++curPopulation);
    }
  }
}


V oldValue = map. get(key); 
V newValue = remappingFunction.apply(key, oldValue); 
if (newValue != null) {     
    map.put(key, newValue); 
} else if (oldValue != null || map.containsKey(key)) {     
    map.remove(key); 
} return newValue;
```