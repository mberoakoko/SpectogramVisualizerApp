package org.specVisualizer.ViewModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TopControl extends HBox {
    Button start, stop;
    ComboBox<String> inputSource ;
    public TopControl(){
        start = new Button("Start");
        start.setPrefSize(70, 20);
        stop = new Button("Stop");
        stop.setPrefSize(70, 20);
        inputSource = new ComboBox<>();
        inputSource.getItems().addAll("Microphone", "Local Storage");
        inputSource.setPrefSize(140, 20);
        Pane horizontalSpacer = new Pane();
        horizontalSpacer.setMinSize(0, 0);
        horizontalSpacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setPrefSize(WindowDimensions.FULLSCREEN.getWidth()*0.7, 80);
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        //
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10, 10, 10, 10));
        VBox.setMargin(start, new Insets(5));
        this.getChildren().addAll(start, stop,horizontalSpacer,  inputSource);
    }
}
