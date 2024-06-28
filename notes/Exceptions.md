# Exceptions
- 3 types of exceptions - checked, runtime (unchecked), and errors 
- an exception can be thrown when anything goes wrong
- can't really do much about errors
- main 2 types are checked exceptions and unchecked exceptions
- checked exceptions sitatuions occuring programmatically that the programmer might bea belt o do somethignabout - i.e. 
- IOException - input output problems. 
- try catch block
- exception is an object that is being 'thrown'

```java
public class ExceptionTests {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three"};
        int num = 0;
        try {
            System.out.println(array.length / num);
        } catch (Exception e) {
           e.printStackTrace();
        }
        System.out.println(array[2]);
    }
}
```
- often the best thing to do might to let the programme die, which is the default behaviour of the JVM anyway, instead of allowing the programme to proceed with the problems 
- Checked exceptions and runtime exceptions both extend from same super class which is Exception - and Exception itself extends from Throwable
- with catch blocks we are ablet to say what kind of exceptipn we are expeted to catch
- in general you do ant to catch the most specific type exception
- however you can always catch the more generic data exception type, that can be a good thing, but also bad if you're not aware that can be happneing. 
- can specify multiple catches

```java
public class ExceptionTests {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three"};
        int num = 0;
        try {
            System.out.println(array.length / num);
            System.out.println(array[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The array index blah blah was throw here");
        } catch (ArithmeticException e) {
            System.out.println("This is due to the arithmetic exception being thrown");
        }
        System.out.println("You made it to the end");
    }
}

```
- because we handling these exceptions with an explicit catch the programme isn't dying and is proceeding onto the next line outside of the try catch block.
- 'Multi-catch': 
```java

public class ExceptionTests {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three"};
        int num = 0;
        try {
            System.out.println(array.length / num);
            System.out.println(array[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Something went terribly wrong");
        } catch (ArithmeticException e) {
            System.out.println("Something went terribly wrong");
        }
        System.out.println("You made it to the end");
    }
}
// same as  
public class ExceptionTests {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three"};
        int num = 0;
        try {
            System.out.println(array.length / num);
            System.out.println(array[3]);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
            System.out.println("Something went terribly wrong");
        }
        System.out.println("You made it to the end");
    }
}
```
- the exceptions are objects and have methods and properties on them

```java
public class ExceptionTests {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three"};
        int num = 1;
        try {
            System.out.println(array.length / num);
            System.out.println(array[3]);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException exception) {
            System.out.println("Something went terribly wrong: " + exception.getMessage());
        }
        System.out.println("You made it to the end");
    }
}

```
- stack trace shows tracing that the thread took.
- just know that the way the stack traces are generated and the timing and order of the thread stuff you should not expect that to print out in the exact order that you intuitively think it would
- the most recently called method can come first so it seems backwards, 
- typically with a stack trace, the line of the code where the exception occured will be at the top, and that makes sense because that's where you want to start, that's where the problem begins
- runtime errors don't actually have to be caught - java doesn't force a try catch, the ide or java don't know these calls could result in an exception before runtime.

---
### Checked Exception
- we have to do something with it
- can use try catch or add exception to method signature
```java
    public static void main(String[] args) {
        try {
            Files.lines(Path.of("blahblahblah")).parallel()
                    .limit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    // be careful with this:
        public static void main(String[] args) throws IOException {
            Files.lines(Path.of("blahblahblah")).parallel()
                    .limit();
        }

```
- method takes particular exception if it is thrown and rethrows it back to whoever called it, so whatever code calls the main method it will have to hadle this exception now.
- passing the buck on, which in this case is the jvm itself so the jvm will blow up all the same
- most well written programmes wont just die, instead the user will be sent a message, and then the user can try aqain
- with stack trace go down until you encounter code that you wrote as digging into java standard library code just cimplicates things as you cant change any of that.

---

- sometimes you may need to clean up or finish up some things befoe the programme compeltely dies
- in some examples where we have been opening files up to read then we dont want to leave those files in a strange state, we want to ensure that even if something went wrong when we were reading data from somewhere, we still close that connection or file gracefully and we can do that in a finally block
- finally block is strongly attempted to be executed by JVM regardless of what went wrong in try block. 
- however, avoid trying to do things in finally block that can throw exceptions because that can screw up what happens in finally block, though there are some cases when you may want to tdo that.
- hurry up get everything out of your house because its on fire.
- no matter how bad things are going we get one last shot to try some final clear up.

```java
 public static void main(String[] args) {
        try {
            Files.lines(Path.of("blahblahblah"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("We were unable to open the file.");
        } finally {
            System.out.println("Make sure this runs no matter what...");
        }
    }
```
- try with resources
- can create own Exception classes - not that hard - just extend from Exception class - or RuntimeException
- when declaring methods you can declare that they will throw certain exceptions
- get familiar with java standard library of exceptions
- Exceptions are just information, not meant to do any processing, just data holders to hold on to pertinant information that might be helpful for someone to analuyze the programme to figure out what went wrong.