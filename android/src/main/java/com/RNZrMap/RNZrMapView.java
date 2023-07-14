package com.RNZrMap;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.facebook.react.bridge.ReadableMap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class RNZrMapView extends MapView {

    protected Context mContext;
    protected String TAG = "RNZrMapView";

    private MapView mMapView;
    private AMap aMap;
    private MyLocationStyle locationStyle;

    public RNZrMapView(Context context) {
        super(context);
        mContext = context;
        aMap = getMap();
    }


//    @Override
//    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
//        super.onLayout(b,i,i1,i2,i3);
//        int mViewGroupWidth  = this.getMeasuredWidth();  //当前ViewGroup的总宽度
//        int mViewGroupHeight  = this.getMeasuredHeight();  //当前ViewGroup的总宽度
////        mMapView.layout(0,0,mViewGroupWidth,mViewGroupHeight);
//        Log.e(TAG, "onLayout: "+mViewGroupWidth+"  "+mViewGroupHeight );
//    }

    public void add(View child) {
        Log.e(TAG, "add: "+child );
        if (child instanceof Overlay) {
            ((Overlay) child).add(aMap);
        }
    }

    public void remove(View child) {
        if (child instanceof Overlay) {
            ((Overlay) child).remove();
        }
    }

    public void setCameraPosition(ReadableMap position) {
        Log.e(TAG, "setCameraPosition: "+position );
        //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
//        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(position.getDouble("latitude"),position.getDouble("longitude")),30,0,0));
        CameraUpdate mCameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(position.getDouble("latitude"),position.getDouble("longitude")));
        aMap.moveCamera(mCameraUpdate);
    }


}
