package purecontrollers;

import com.google.inject.Provider;

class CommandProvider<T> implements Command<T> {
    private final Provider<? extends Command<T>> provider;
    
    private Command<T> actualCommand;

    public CommandProvider(Provider<? extends Command<T>> aProvider) {
        provider = aProvider;
    }

    @Override
    public void processIO(T io) {
        if(actualCommand == null) {
            actualCommand = provider.get();
        }
        
        actualCommand.processIO(io);
    }
}