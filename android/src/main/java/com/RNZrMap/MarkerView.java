package com.RNZrMap;

import android.content.Context;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.views.view.ReactViewGroup;

public class MarkerView extends ReactViewGroup implements Overlay {

    Marker marker;
    MarkerOptions op;

    public String title;
    public String subtitle;
    public ReadableMap position;

    public void setTitle(String title) {
        this.title = title;
        op.title(title);
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        op.snippet(subtitle);
    }

    public void setPosition(ReadableMap position) {
        this.position = position;
        op.position(new LatLng(position.getDouble("latitude"),position.getDouble("longitude")));
    }

    public MarkerView(Context context) {
        super(context);
        op = new MarkerOptions();
        LatLng latLng = new LatLng(39.906901,116.397972);
        op.position(latLng);
    }

    @Override
    public void add(AMap map) {
        marker = map.addMarker(op);
    }

    @Override
    public void remove() {
        marker.destroy();
    }
}
