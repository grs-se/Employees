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