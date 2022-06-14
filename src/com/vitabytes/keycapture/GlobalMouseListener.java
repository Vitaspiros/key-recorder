package com.vitabytes.keycapture;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

public class GlobalMouseListener implements NativeMouseListener{
    @Override
    public void nativeMouseClicked(NativeMouseEvent event) {
        Data.clicks++;
    }
}
