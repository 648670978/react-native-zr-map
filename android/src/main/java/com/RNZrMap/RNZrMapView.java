package com.RNZrMap;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.amap.api.maps.MapView;

import androidx.annotation.NonNull;

public class RNZrMapView extends FrameLayout {

    protected Context mContext;
    protected String TAG = "RNZrMapView";

    private MapView mMapView;

    public RNZrMapView(Context context) {
        super(context);
        mContext = context;
        mMapView = new MapView(context);
        this.addView(mMapView);
        Log.e(TAG, "RNZrMapView: " );
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int mViewGroupWidth  = this.getMeasuredWidth();  //当前ViewGroup的总宽度
        int mViewGroupHeight  = this.getMeasuredHeight();  //当前ViewGroup的总宽度
        mMapView.layout(0,0,mViewGroupWidth,mViewGroupHeight);
        Log.e(TAG, "onLayout: "+mViewGroupWidth+"  "+mViewGroupHeight );
    }


    public void onCreate(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
    }

    public void onPause() {
        // do any logic that should happen in an `onPause` method
        // e.g.: customView.onPause();
        mMapView.onPause();
    }

    public void onResume() {
        // do any logic that should happen in an `onResume` method
        // e.g.: customView.onResume();
        mMapView.onResume();
    }

    public void onDestroy() {
        // do any logic that should happen in an `onDestroy` method
        // e.g.: customView.onDestroy();
        mMapView.onDestroy();
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        mMapView.onSaveInstanceState(outState);
    }


}
