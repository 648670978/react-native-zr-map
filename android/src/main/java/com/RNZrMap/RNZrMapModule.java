
package com.RNZrMap;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNZrMapModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

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
  }

  @ReactMethod
  public void getVersion(Promise promise) {
    promise.resolve(MapsInitializer.getVersion());
  }

}