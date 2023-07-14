package com.RNZrMap;

import com.amap.api.maps.AMap;

interface Overlay {
    void add(AMap map);
    void remove();
}