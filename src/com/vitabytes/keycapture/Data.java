package com.vitabytes.keycapture;
import java.util.HashMap;

import javafx.scene.control.Label;

public class Data {
    public static HashMap<String,Integer> keys=new HashMap<>();
    public static int keysPressed=0;
    public static int clicks=0;

    public static Label keyLabel=new Label("Keys pressed: "+keysPressed);

    public static int wheelDown=0;
    public static int wheelUp=0;
}
