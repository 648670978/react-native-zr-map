
package com.RNZrMap;

import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.ServiceSettings;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

public class RNZrMapModule extends ReactContextBaseJavaModule implements GeocodeSearch.OnGeocodeSearchListener {

  private final ReactApplicationContext reactContext;
  private GeocodeSearch geocoderSearch;

  public RNZrMapModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNZrMap";
  }

  @ReactMethod
  public void initMap(String apiKey) {
    MapsInitializer.setApiKey(apiKey);
    MapsInitializer.updatePrivacyShow(reactContext,true,true);
    MapsInitializer.updatePrivacyAgree(reactContext,true);
    AMapLocationClient.updatePrivacyAgree(reactContext, true);
    AMapLocationClient.updatePrivacyShow(reactContext, true, true);
    ServiceSettings.updatePrivacyShow(reactContext,true,true);
    ServiceSettings.updatePrivacyAgree(reactContext,true);
  }

  @ReactMethod
  public void getVersion(Promise promise) {
    promise.resolve(MapsInitializer.getVersion());
  }

  GeocodeSearch getGeocoderSearch() {
    if (geocoderSearch == null) {
      try {
        geocoderSearch = new GeocodeSearch(reactContext);
        geocoderSearch.setOnGeocodeSearchListener(this);
      } catch (AMapException e) {
        throw new RuntimeException(e);
      }
    }
    return geocoderSearch;
  }


  private Callback geocodeSeatchCallback;
  @ReactMethod
  public void geocodeSeatch(String address,Callback callback) throws AMapException {
    GeocodeQuery query = new GeocodeQuery(address,"");
    getGeocoderSearch().getFromLocationNameAsyn(query);
    Log.e("zr", "geocodeSeatch: "+address );
    geocodeSeatchCallback = callback;
  }


  @Override
  public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
    Log.e("zr", "onRegeocodeSearched: "+regeocodeResult );
  }

  @Override
  public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
    LatLonPoint latlon = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint();
    WritableMap map = Arguments.createMap();
    map.putDouble("latitude",latlon.getLatitude());
    map.putDouble("longitude",latlon.getLongitude());
    geocodeSeatchCallback.invoke(map);
    Log.e("zr", "onGeocodeSearched: "+latlon);
  }
}