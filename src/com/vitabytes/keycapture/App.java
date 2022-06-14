package com.vitabytes.keycapture;
import java.util.*;
import java.util.Map.Entry;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class App extends Application {

    GridPane gp=new GridPane();
    Label clickLabel=new Label("Mouse clicked: "+Data.clicks);
    Label wheelDownLabel=new Label("Wheel moved down: "+Data.wheelDown);
    Label wheelUpLabel=new Label("Wheel moved up: "+Data.wheelUp);
    public void start(Stage stage) {

        stage.setTitle("Key Capture");

        Scene scene=new Scene(gp,640,600);
        Button btn1=new Button("Show Chart");
        Button btn2=new Button("Update");
        btn1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                showChart();
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.keyLabel.setText("Keys pressed: "+Data.keysPressed);
                clickLabel.setText("Mouse clicked"+Data.clicks);
                wheelDownLabel.setText("Wheel moved down: "+Data.wheelDown);
                wheelUpLabel.setText("Wheel moved up: "+Data.wheelUp);
            }
        });
        gp.add(Data.keyLabel,0,0);
        gp.add(clickLabel,0,1);
        gp.add(wheelDownLabel,0,2);
        gp.add(wheelUpLabel,0,3);
        gp.add(btn2,2,1);
        gp.add(btn1,1,0);

        stage.setScene(scene);
        stage.show();
    }

    void showChart() {
        Stage stage=new Stage();
        stage.setTitle("Key Chart");
        // Create chart
        final CategoryAxis xAxis=new CategoryAxis();
        final NumberAxis yAxis=new NumberAxis();
        final BarChart<String,Number> bc=new BarChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("Keys");
        yAxis.setLabel("Times");

        XYChart.Series<String,Number> series=new XYChart.Series<String,Number>();

        HashMap<String,Integer> keys=sortByValue(Data.keys);
        keys.forEach((key,value) -> {
            series.getData().add(new XYChart.Data<String,Number>(key,value));
        });

        bc.getData().add(series);
        
        Scene scene=new Scene(bc,640,600);
        stage.setScene(scene);
        stage.show();
    }

    // COPIED FROM STACK OVERFLOW

    public static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue(HashMap<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue(Collections.reverseOrder())); // CHANGED LINE

        HashMap<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
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
