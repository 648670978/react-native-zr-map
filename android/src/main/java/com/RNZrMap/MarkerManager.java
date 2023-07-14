package com.RNZrMap;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class MarkerManager extends ViewGroupManager <MarkerView> {

    ReactApplicationContext reactContext;
    public MarkerManager(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return "ZRMapMarker";
    }

    @NonNull
    @Override
    protected MarkerView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new MarkerView(themedReactContext);
    }

    @Override
    public void updateExtraData(@NonNull MarkerView view, Object o) {

    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(MarkerView marker,ReadableMap coordinate) {
        marker.setPosition(coordinate);
    }

    @ReactProp(name = "title")
    public void setTitle(MarkerView marker,String title) {
        marker.setTitle(title);
    }

    @ReactProp(name = "subtitle")
    public void setSubtitle(MarkerView marker, String subtitle) {
        marker.setSubtitle(subtitle);
    }

}
