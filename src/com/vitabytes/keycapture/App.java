package com.vitabytes.keycapture;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class App extends Application {

    GridPane gp=new GridPane();
    Label clickLabel=new Label("Mouse clicked: "+Data.clicks);
    Label wheelDownLabel=new Label("Wheel moved down: "+Data.wheelDown);
    Label wheelUpLabel=new Label("Wheel moved up: "+Data.wheelUp);

    String order="Descending";

    BarChart<String,Number> bc;
    public void start(Stage stage) {
        clickLabel.getStyleClass().add("label");
        wheelDownLabel.getStyleClass().add("label");
        wheelUpLabel.getStyleClass().add("label");

        gp.getStyleClass().add("gp");


        stage.setTitle("Key Capture");

        Scene scene=new Scene(gp,640,600);
        HBox hBox=new HBox(20);


        // CSS
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());

        Button btn1=new Button("Show Chart");
        btn1.getStyleClass().add("btn");

        ChoiceBox<String> orderBox=new ChoiceBox<String>();
        orderBox.getStyleClass().add("btn"); // Yes, I know that this isn't a button
        orderBox.setValue("Descending");
        orderBox.getItems().addAll("Descending","Ascending");



        Button btn2=new Button("Update");
        btn2.getStyleClass().add("btn");
        btn1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                ChartScreen.showChart(orderBox.getValue());
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.keyLabel.setText("Keys pressed: "+Data.keysPressed);
                clickLabel.setText("Mouse clicked: "+Data.clicks);
                wheelDownLabel.setText("Wheel moved down: "+Data.wheelDown);
                wheelUpLabel.setText("Wheel moved up: "+Data.wheelUp);
            }
        });

        hBox.getChildren().add(btn2);
        hBox.getChildren().add(btn1);
        hBox.getChildren().add(orderBox);

        gp.add(hBox,2,5);

        gp.add(Data.keyLabel,0,0);
        gp.add(clickLabel,0,1);
        gp.add(wheelDownLabel,0,2);
        gp.add(wheelUpLabel,0,3);
        //gp.add(btn2,1,5);
        //gp.add(btn1,1,0);

        stage.setScene(scene);
        stage.show();
    }

    

    @Override
    public void stop() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException err) {
            err.printStackTrace();
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
        GlobalScreen.addNativeMouseListener(new GlobalMouseListener());
        GlobalScreen.addNativeMouseWheelListener(new GlobalMouseWheelListener());
        launch(args);
    }
}
