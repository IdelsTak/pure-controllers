package purecontrollers;

import java.util.concurrent.ConcurrentHashMap;

public class CommandHQ {
    
    private final ConcurrentHashMap<Class<?>, Command<?>> commands = new ConcurrentHashMap<Class<?>, Command<?>>();
    
    @SuppressWarnings("unchecked")
    <T> void processIO(T io) {
        assert io != null : "io cannot be null";

        Command<T> command = (Command<T>) commands.get(io.getClass());
        
        if(command == null) 
            throw new RuntimeException("No command exists for IO " + io);
        
        command.processIO(io);
    }
    
    public <T> CommandHQ addCommand(Class<T> clazz, Command<T> command) {
        Command<?> previous = commands.put(clazz, command);
        
        if(previous != null) 
            throw new RuntimeException("IO " + clazz + " already has command " + previous);
        
        return this;
    }
}
