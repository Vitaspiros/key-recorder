package com.vitabytes.keycapture;

import java.util.*;
import java.util.Map.Entry;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

public class ChartScreen {

    static BarChart<String,Number> createChart(String order) {
        final CategoryAxis xAxis=new CategoryAxis();
        final NumberAxis yAxis=new NumberAxis();
        final BarChart<String,Number> bc=new BarChart<String,Number>(xAxis,yAxis);
        bc.getStyleClass().add("bc");
        xAxis.setLabel("Keys");
        yAxis.setLabel("Times");

        XYChart.Series<String,Number> series=new XYChart.Series<String,Number>();

        getElements(bc,series,order);
        return bc;
    }

    static void getElements(BarChart<String,Number> bc,XYChart.Series<String,Number> series,String order) {
        HashMap<String,Integer> keys=sortByValue(Data.keys,order);
        keys.forEach((key,value) -> {
            series.getData().add(new XYChart.Data<String,Number>(key,value));
        });

        bc.getData().add(series);
    }

    static void showChart(String order) {
        Stage stage=new Stage();
        stage.setTitle("Key Chart");
        
        
        Scene scene=new Scene(createChart(order),640,600);

        scene.getStylesheets().add(App.class.getResource("css/chart.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    // COPIED FROM STACK OVERFLOW

    public static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue(HashMap<K, V> map,String order) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        System.out.println("Order: "+order);
        if (order.equals("Descending")) {
            list.sort(Entry.comparingByValue(Collections.reverseOrder())); // CHANGED LINE
        } else if (order.equals("Ascending")) {
            list.sort(Entry.comparingByValue());
            System.out.println("ASCENDING ORDER");
        }

        HashMap<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
