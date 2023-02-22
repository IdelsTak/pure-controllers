package purecontrollers;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import com.google.inject.AbstractModule;

public class PureControllersModule extends AbstractModule {

    private final CommandHQ commandHQ;
    
    private final IOInterceptor ioInterceptor;

    public PureControllersModule() {
        commandHQ = new CommandHQ();
        
        ioInterceptor = new IOInterceptor(commandHQ);
    }

    @Override
    protected final void configure() {
        bind(CommandHQ.class).toInstance(commandHQ);
        
        bindInterceptor(any(), annotatedWith(IOProvider.class), ioInterceptor);
        
        bindCommands(commandHQ);
    }

    /**
     * Hook for super classes to override and add their own commands to
     * {@link CommandHQ}.
     */
    protected void bindCommands(CommandHQ aCommandHQ) {
        // override me!
    }

    /**
     * Provide a command that will be created at runtime, after the injector has
     * been injected. This allows the command to have injected fields.
     * 
     * @param CommandClass
     *            The {@link Command} implementation class.
     * 
     * @return 
     */
    protected <T> Command<T> provideCommand(final Class<? extends Command<T>> CommandClass) {
        return new CommandProvider<>(getProvider(CommandClass));
    }
}
