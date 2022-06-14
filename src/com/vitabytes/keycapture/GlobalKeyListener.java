package com.vitabytes.keycapture;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent event) {
        String key=NativeKeyEvent.getKeyText(event.getKeyCode());
        if (Data.keys.containsKey(key)) {
            Data.keys.replace(key,Data.keys.get(key)+1);
        } else {
            Data.keys.put(key,1);
        }
        Data.keysPressed++;
        //Data.keyLabel.setText("Keys pressed: "+Data.keysPressed);
    }
}