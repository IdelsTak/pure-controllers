package purecontrollers.demo;

import javafx.beans.property.SimpleBooleanProperty;

public class SaveIO {

    private final String name;
    
    private final SimpleBooleanProperty complete;

    public SaveIO(String aName) {
        name = aName;
        complete = new SimpleBooleanProperty(false);
    }

    public String getName() {
        return name;
    }

    public SimpleBooleanProperty completeProperty() {
        return complete;
    }

    public void complete() {
        complete.set(true);
    }
    
    public boolean isComplete() {
        return complete.get();
    }
}
