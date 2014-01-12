package purecontrollers.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import com.google.inject.Inject;

public class DemoView implements Initializable {
    
    @Inject
    private DemoController controller;
    
    @FXML
    public TextField nameField;
    @FXML
    public Button saveButton;
    @FXML
    public ListView<String> namesListView;
    @FXML
    private HBox controlPane;
    
    public final ProgressIndicator saveProgress = new ProgressIndicator(-1);

    public void initialize(URL url, ResourceBundle r) {
        assert nameField != null : "nameField is null!";
        
        nameField.textProperty().bindBidirectional(controller.nameProperty());
        nameField.disableProperty().bind(controller.processingProperty());
        
        saveButton.disableProperty().bind(controller.savingEnabledProperty().not());
        
        saveProgress.visibleProperty().bind(controller.processingProperty());
        saveProgress.setMaxWidth(24);
        saveProgress.setMaxHeight(24);
        
        Bindings.bindContentBidirectional(namesListView.getItems(), controller.getSavedNames());
        
        // dynamically add the progress indicator, when the FXML is loaded and
        // the indicator is not visible it is still allocated space in the HBox
        controller.processingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> o, Boolean oldv, Boolean newv) {
                if(newv && saveProgress.getParent() == null) {
                    controlPane.getChildren().add(saveProgress);
                    controller.processingProperty().removeListener(this);
                }
            }});
    }

    @FXML
    public void onSave() {
        controller.onSave();
    }
}
