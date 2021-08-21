package org.specVisualizer.ViewModel;

import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChartContainers extends VBox {
    HBox dummyContainer = new HBox();
    HBox dummyContainer2 = new HBox();

    public ChartContainers(){
        dummyContainer.setMinSize(1080, 300);
        dummyContainer2.setMinSize(1080, 300);
        this.getChildren().addAll(dummyContainer, dummyContainer2);
    }
}
