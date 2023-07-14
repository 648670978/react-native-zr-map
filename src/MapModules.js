import { NativeModules } from "react-native";

const { RNZrMap } = NativeModules;

export function initMap(apiKey) {
    RNZrMap.initMap(apiKey)
}

export function geocodeSeatch(address) {
    return new Promise((resolve, reject) => {
        RNZrMap.geocodeSeatch(address, (loc) => {
            console.log('坐标', loc);
            if (loc) {
                resolve(loc)
            } else {
                reject('转换地址失败')
            }
        })
    })
}
