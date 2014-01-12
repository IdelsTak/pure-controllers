package purecontrollers;

/**
 * A {@link Command} is able to process IO, where IO is a specification of what
 * the command should do.
 * 
 * @param <T>
 *            The IO type this command can process.
 */
public interface Command<T> {
    public void processIO(T io);
}
