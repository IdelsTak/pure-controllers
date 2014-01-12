package purecontrollers.demo;

import purecontrollers.IOProvider;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DemoController {

    private final StringProperty name = new SimpleStringProperty();

    private final SimpleBooleanProperty processing = new SimpleBooleanProperty(false);

    private final SimpleBooleanProperty savingEnabled = new SimpleBooleanProperty(false);
    
    private final ObservableList<String> savedNames = FXCollections.observableArrayList();
    
    public DemoController() {
        savingEnabled.bind(name.length().greaterThan(0));
    }

    @IOProvider
    public SaveIO onSave() {
        String nameString = name.get();
        
        if(nameString == null || "".equals(nameString))
            return null;
        
        final SaveIO saveAction = new SaveIO(nameString);
        
        processing.bind(saveAction.completeProperty().not());
        
        saveAction.completeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                if(saveAction.isComplete()) {
                    savedNames.add(saveAction.getName());
                }
                processing.unbind();
            }});
        
        name.set("");
        
        return saveAction;
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    

    public SimpleBooleanProperty processingProperty() {
        return processing;
    }

    public ObservableList<String> getSavedNames() {
        return savedNames;
    }

    public SimpleBooleanProperty savingEnabledProperty() {
        return savingEnabled;
    }
}
