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
    private static void employeeStreamPractice(String peopleText) {
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