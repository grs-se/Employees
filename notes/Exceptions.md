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