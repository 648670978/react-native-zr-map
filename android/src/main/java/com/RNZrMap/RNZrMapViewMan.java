package com.RNZrMap;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.amap.api.maps.MapView;
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
import androidx.fragment.app.FragmentActivity;

public class RNZrMapViewMan extends ViewGroupManager<FrameLayout> {

    ReactApplicationContext reactContext;
    public final int COMMAND_CREATE = 1;
    private int propWidth;
    private int propHeight;
    String TAG = "zr";

    public RNZrMapViewMan(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ZRMapView";
    }

    @Override
    public FrameLayout createViewInstance(ThemedReactContext reactContext) {
        FrameLayout view =  new FrameLayout(reactContext);
        return view;
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("create", COMMAND_CREATE);
    }

    @Override
    public void receiveCommand(
            @NonNull FrameLayout root,
            String commandId,
            @Nullable ReadableArray args
    ) {
        super.receiveCommand(root, commandId, args);
        int reactNativeViewId = args.getInt(0);
        int commandIdInt = Integer.parseInt(commandId);
        Log.e(TAG, "receiveCommand: "+commandIdInt );
        switch (commandIdInt) {
            case COMMAND_CREATE:
                createFragment(root, reactNativeViewId);
                break;
            default: {}
        }
    }

    public void createFragment(FrameLayout root, int reactNativeViewId) {
        ViewGroup parentView = (ViewGroup) root.findViewById(reactNativeViewId);
        Log.e(TAG, "createFragment: "+parentView+"\n"+reactNativeViewId );
        setupLayout(parentView);

        final RNZrMaoFragment myFragment = new RNZrMaoFragment();
        FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(reactNativeViewId, myFragment, String.valueOf(reactNativeViewId))
                .commit();
    }

    public void setupLayout(View view) {
        Log.e(TAG, "setupLayout: " );
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                Log.e(TAG, "doFrame: " );
                manuallyLayoutChildren(view);
                view.getViewTreeObserver().dispatchOnGlobalLayout();
//                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }

    public void manuallyLayoutChildren(View view) {
        // propWidth and propHeight coming from react-native props
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        Log.e("zr", "width: "+width );
        Log.e("zr", "height: "+height );

        view.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        view.layout(0, 0, width, height);
    }


    @ReactProp(name = "initialCameraPosition")
    public void setInitialCameraPosition(MapView view, ReadableMap position) {
//        view.setInitialCameraPosition(position);
    }



}
