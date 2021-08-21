package org.specVisualizer.ViewModel;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SideControl extends VBox {

    ComboBox<String> sampleRate, FFTSize, Scaling;
    Button spectogram , audioSignal , wavelets;

    public SideControl(){
        sampleRate  = new ComboBox<>();
        sampleRate.getItems().addAll("Sample Rate 1", "Sample Rate 2");
        FFTSize = new ComboBox<>();
        FFTSize.getItems().addAll("FFT Size");
        Scaling = new ComboBox<>();
        Scaling.getItems().addAll("Linear", "Logarithmic");
        spectogram = new Button("Spectogram");
        audioSignal = new Button("Audio Signal");
        wavelets = new Button("Wavelets");
        this.setHeight(800);
        this.setPadding(new Insets(30));
        List<Node> nodes = new ArrayList<>(List.of(sampleRate, FFTSize, Scaling, spectogram, audioSignal, wavelets));
        nodes.forEach(node -> {
            VBox.setMargin(node, new Insets(2, 5, 2, 5));
            this.getChildren().add(node);
        });
        //this.getChildren().addAll(sampleRate, FFTSize, Scaling, spectogram, audioSignal, wavelets);
    }
}
