
# react-native-zr-map

## Getting started

`$ npm install react-native-zr-map --save`


### Manual installation


#### iOS



#### Android

//在AndroidManifest.xml文件里的application标签里添加
```
<meta-data
                android:name="com.amap.api.v2.apikey"
                android:value="${AMAP_APPKEY}"/>
```


//在 app/build.gradle 里添加
```
android {
    ...
    defaultConfig {
        ...
        manifestPlaceholders {
            AMAP_APPKEY:"myAppKey"
        }
    }
}

//在 AndroidManifest.xml 里添加
<application
    ...
    android:allowNativeHeapPointerTagging="false"
>
```


## Usage
```javascript
import { initMap, MapView, MapMarker } from 'react-native-zr-map'

  initMap(Platform.select({
    android: "myAppKey",
    ios: "myAppKey",
  }))

<MapView initialCoordinate={this.state.position} style={{ width:200, height: 200 }}>
    <MapMarker coordinate={this.state.position} title={'标题'} />
</MapView>



```
  