/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View, Platform, NativeModules
} from 'react-native';
import { initMap, MapView, MapMarker } from 'react-native-zr-map'

const { RNZrMap } = NativeModules;

function App() {
  initMap(Platform.OS === 'ios' ? '924b2cc8518585a5275106e0a3f156a1' : '5c0ecbb9d1397a6cad5976ae4c183f9c')
  return (
    <View style={{ flex: 1 }}>
      <View style={{ width:300,height:500 }}>
        <MapView ref={(ref)=>{this.mapView=ref}} >
          <MapMarker />
        </MapView>
      </View>

      <Text style={{}} onPress={() => {
        RNZrMap.geocodeSeatch('北京市朝阳区阜荣街10号', (loc) => {
          console.log('坐标', loc);
        })
      }}>
        获取坐标
      </Text>
      <Text style={{}} onPress={() => {
        this.mapView.setCameraPosition()
      }}>
        设置中心点
      </Text>
      


    </View>
  );
}

export default App;
