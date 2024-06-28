# Optionals
- a lot of functionality of optionals is predicated on knowledge of lambdas and streams api
- Optionals are really just wrappers for other objets or for nothig at all 
- give us the option of representing either some object existing or no object existing in a particilar case
- no.1 use case is for null values: whenever we are dealing with objects in Java we store references to objects in variables and those variables may or may not actually be pointing back toa an object.
- NullPointerException - trying to reference the method of an object that doesn't exist
- Optionals let us wrapper variables in a box, and give us access to methods that let us detect whether or not we actually have any value int the box, and if we do then we can proceed to do things to that value

```java
public class OptionalTest {
    public static void main(String[] args) {
        String msg = "Hello";
        String msg2 = null;
        Optional<String> optMsg2 = Optional.ofNullable(msg2);
        // should now be impossible to receive a NullPointerException
        String finalOutput = optMsg2.orElse("alternative").toUpperCase();
        System.out.println(finalOutput);

//        Optional<String> optMsg = Optional.of("Hello");
//        System.out.println(optMsg);
    }
}
//ALTERNATIVE
```