package purecontrollers.demo;

import java.net.URL;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.cathive.fx.guice.GuiceApplication;
import com.cathive.fx.guice.GuiceFXMLLoader;
import com.cathive.fx.guice.GuiceFXMLLoader.Result;
import com.cathive.fx.guice.fxml.FXMLLoadingScope;
import com.google.inject.Module;

public class DemoApp extends GuiceApplication  {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = getClass().getResource("/purecontrollers/demo/pure-controllers-demo.fxml");
        GuiceFXMLLoader fxmlLoader = new GuiceFXMLLoader(getInjector(), new FXMLLoadingScope());
        Result result = fxmlLoader.load(resource);
        Parent p = result.getRoot();
        
        Scene scene = new Scene(p);
        
        stage.setTitle("Pure Controllers Demo");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    @Override
    public void init(List<Module> modules) throws Exception {
        modules.add(new DemoModule());
    }
}
