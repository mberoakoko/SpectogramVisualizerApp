package org.specVisualizer.ViewModel;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;



public class MainView extends VBox {
    private int width, height;
    public MainView(int height, int width){
        this.width = width;
        this.height = height;
        TopControl topControl = new TopControl();
        SideControl sideControl = new SideControl();
        ChartContainers chartContainers = new ChartContainers();


        GridPane mainContainer = new GridPane();
        mainContainer.add(topControl, 0, 0, 5,  1);
        mainContainer.add(chartContainers, 0, 1, 3, 6);
        mainContainer.add(sideControl, 7, 1, 2, 6);
        this.setPrefSize(10, height);
        mainContainer.setMinSize(width, height);
        mainContainer.setGridLinesVisible(true);

        this.getChildren().addAll(mainContainer);
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public void setHeight(int height) {
        this.height = height;
    }
}
