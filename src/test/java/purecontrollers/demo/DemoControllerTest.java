package purecontrollers.demo;

import static org.junit.Assert.*;
import javafx.beans.property.SimpleStringProperty;

import org.junit.Before;
import org.junit.Test;

public class DemoControllerTest extends DemoController {

    private DemoController controller;
    
    private SimpleStringProperty nameProperty;
    
    private String name = "Andy";

    private SaveIO onSave;
    
    @Before
    public void before() {
        controller = new DemoController();
        
        nameProperty = new SimpleStringProperty();

        controller.nameProperty().bindBidirectional(nameProperty);
    }
    
    @Test
    public void valueOfNamePropertyIsSaved() {
        nameProperty.set(name);
        
        onSave = controller.onSave();
        
        assertEquals(name, onSave.getName());
    }

    @Test
    public void processingIsTrueWhileNotCompleted() {
        nameProperty.set(name);
        
        onSave = controller.onSave();
        
        assertEquals(true, controller.processingProperty().get());
    }
    
    @Test
    public void processingIsFalseWhenCompleted() {
        nameProperty.set(name);
        
        onSave = controller.onSave();
        onSave.complete();
        
        assertEquals(false, controller.processingProperty().get());
    }
    
    @Test
    public void nameIsClearedWhenSaveIsComplete() {
        nameProperty.set(name);
        
        onSave = controller.onSave();
        onSave.complete();
        
        assertEquals("", controller.nameProperty().get());
    }
    
    @Test
    public void saveSubsequentName() {
        nameProperty.set(name);
        
        onSave = controller.onSave();
        onSave.complete();

        String subsequentName = "Hamster";
        nameProperty.set(subsequentName);
        onSave = controller.onSave();
        assertEquals(subsequentName, onSave.getName());
    }
    
    @Test
    public void savedNameIsAddedToListOfSavedNamesWhenComplete() {
        nameProperty.set(name);
        
        onSave = controller.onSave();
        onSave.complete();

        assertTrue(controller.getSavedNames().contains(name));
    }

    @Test
    public void nullNamesCannotBeSaved() {
        nameProperty.set(null);
        
        onSave = controller.onSave();
        
        assertNull(onSave);
        assertFalse(controller.processingProperty().get());
        assertTrue(controller.getSavedNames().isEmpty());
    }

    @Test
    public void emptyNamesCannotBeSaved() {
        nameProperty.set("");
        
        onSave = controller.onSave();
        
        assertNull(onSave);
        assertFalse(controller.processingProperty().get());
        assertTrue(controller.getSavedNames().isEmpty());
    }
    
    @Test
    public void savingIsNotEnabledWhenTheNameIsEmpty() {
        nameProperty.set("");
        
        assertFalse(controller.savingEnabledProperty().get());
    }
    
    @Test
    public void savingIsEnabledWhenTheNameIsNotEmpty() {
        nameProperty.set("Hello");
        
        assertTrue(controller.savingEnabledProperty().get());
    }
}
