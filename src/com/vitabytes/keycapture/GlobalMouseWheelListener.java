package com.vitabytes.keycapture;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelListener;

public class GlobalMouseWheelListener implements NativeMouseWheelListener {
    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent event) {
        int rotation=event.getWheelRotation();
        if (rotation==1) {
            Data.wheelDown++;
        } else if (rotation==-1) {
            Data.wheelUp++;
        }
    }
}
