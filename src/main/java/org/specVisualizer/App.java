package org.specVisualizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.specVisualizer.ViewModel.MainView;
import org.specVisualizer.ViewModel.WindowDimensions;


/**
 * JavaFX App
 */
public class App extends Application {
WindowDimensions dimensions = WindowDimensions.FULLSCREEN;
    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView(dimensions.getHeight(), dimensions.getWidth());
        var scene = new Scene(mainView,
                WindowDimensions.FULLSCREEN.getWidth(), WindowDimensions.FULLSCREEN.getHeight());
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}