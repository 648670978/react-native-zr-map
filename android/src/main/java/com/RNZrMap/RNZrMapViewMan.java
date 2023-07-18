package com.RNZrMap;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class RNZrMapViewMan extends ViewGroupManager<RNZrMapView> {

    ReactApplicationContext reactContext;
    public final int create_CREATE = 1;
    public final int setCameraPosition_CREATE = 2;

    String TAG = "zr";

    public RNZrMapViewMan(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ZRMapView";
    }

    @Override
    public RNZrMapView createViewInstance(ThemedReactContext reactContext) {
        return new RNZrMapView(reactContext);
    }

    @Override
    public void onDropViewInstance(RNZrMapView view) {
        super.onDropViewInstance(view);
        view.onDestroy();
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("create", create_CREATE,"setCameraPosition",setCameraPosition_CREATE);
    }

    @Override
    public void receiveCommand(
            @NonNull RNZrMapView root,
            String commandId,
            @Nullable ReadableArray args
    ) {
        super.receiveCommand(root, commandId, args);
        int commandIdInt = Integer.parseInt(commandId);
        int reactNativeViewId = args.getInt(0);
        Log.e(TAG, "receiveCommand: "+commandIdInt );
        Log.e(TAG, "reactNativeViewId: "+reactNativeViewId );
        switch (commandIdInt) {
            case create_CREATE:
            {
//                createFragment(root, reactNativeViewId);
                root.onCreate(null);
            }
                break;
            case setCameraPosition_CREATE:
                ReadableMap map = args.getMap(1);
                root.setCameraPosition(map);
                break;
            default: {}
        }
    }

    @Override
    public void addView(RNZrMapView parent, View child, int index) {
        parent.add(child);
        super.addView(parent,child,index);
    }
    @Override
    public void removeViewAt(RNZrMapView parent, int index) {
        parent.remove(parent.getChildAt(index));
        super.removeViewAt(parent, index);
    }

    @ReactProp(name = "initialCoordinate")
    public void setInitialCoordinate(RNZrMapView parent,ReadableMap coordinate) {
        parent.setCameraPosition(coordinate);
    }

    @ReactProp(name = "mapType")
    public void setMapType(RNZrMapView parent,int mapType) {
        parent.getMap().setMapType(mapType+1);
    }

    @ReactProp(name = "zoomEnabled")
    public void setZoomEnabled(RNZrMapView parent,boolean zoomEnabled) {
        UiSettings ui = parent.getMap().getUiSettings();
        ui.setZoomGesturesEnabled(zoomEnabled);
    }

    @ReactProp(name = "scrollEnabled")
    public void setScrollEnabled(RNZrMapView parent,boolean scrollEnabled) {
        UiSettings ui = parent.getMap().getUiSettings();
        ui.setScrollGesturesEnabled(scrollEnabled);
    }

    @ReactProp(name = "rotateEnabled")
    public void setRotateEnabled(RNZrMapView parent,boolean rotateEnabled) {
        UiSettings ui = parent.getMap().getUiSettings();
        ui.setRotateGesturesEnabled(rotateEnabled);
    }

}
