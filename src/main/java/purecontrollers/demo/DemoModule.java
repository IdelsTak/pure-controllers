package purecontrollers.demo;

import purecontrollers.CommandHQ;
import purecontrollers.PureControllersModule;

public class DemoModule extends PureControllersModule {
    @Override
    protected void bindCommands(CommandHQ aCommandHQ) {
        aCommandHQ
            .addCommand(SaveIO.class, provideCommand(SaveCommand.class));
    }
}
