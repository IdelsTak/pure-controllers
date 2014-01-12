package purecontrollers.demo;

import javafx.application.Platform;
import purecontrollers.Command;

public class SaveCommand implements Command<SaveIO> {
    @Override
    public void processIO(final SaveIO io) {
        
        new Thread() {
            @Override
            public void run() {
                sleepOnIt();
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        io.complete();
                    }});
            }

            private void sleepOnIt() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}