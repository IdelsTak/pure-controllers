
#Pure Controllers

Pure controllers brings pure functions\* and functional programming to JavaFX controllers.  Instead of making database calls and causing other side effects directly from event handler methods in a controller, the controller returns data describing the IO action that should take place and the pure Controllers library will route it to a command you provide. 

This makes unit testing very simple.  Instead of mocking services inside the controller, unit tests can assert the returned IO objects without actually executing them.

IO routing is performed using Guice.  Controllers returning IO must be created using a Guice injector.

##Getting Started

Lets setup the Guice `Module` that will route our IO requests, to commands.  The `Email` class is the IO that will describe the email and `EmailService` will send the email.

    public class DemoModule extends PureControllersModule {
        @Override
        protected void bindCommands(CommandHQ commandHQ) {
            commandHQ.addCommand(Email.class, new EmailService());
        }
    }

`CommandHQ` is a map of Class objects to commands.  It can apply an IO object to it's corresponding command.  Now we can define the FXML event handler which will get called when the user presses the button to send an email.

    @FXML
    @IOProvider
    public Email onSendEmailButtonClicked() {
        return new Email("kittyburrito@googlemail.com", "Hi There!");
    }

Methods annotated with `@IOProvider` must return data describing a command.  Pure Controllers will intercept this command using dependency injection and run it using a command registered to handle the `Email` class.

See the demo included in the source for more details on how to setup an application using pure controllers.

**\* The pure controllers library allows controllers to maintain state and refer to object fields to keep things simple. Functions are not completely pure, unless you want it to be!**

##License

The MIT License (MIT)

Copyright (c) 2014 Andy Till

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
