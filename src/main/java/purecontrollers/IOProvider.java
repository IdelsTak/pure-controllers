package purecontrollers;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@link IOProvider} is an annotation for methods that return objects to be
 * executed as a side effect.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface IOProvider {

}
